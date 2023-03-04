package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

import duke.command.Command;
import duke.command.CommandResult;
import duke.command.ExitCommand;
import duke.parser.CommandParser;
import duke.storage.StorageFile;
import duke.data.TaskList;
import duke.ui.TextUi;

/**
 * Entry point of the Duke Application.
 * Initializes the application and interacts with the user.
 */
public class Main {
    private static final String VERSION = "0.2";
    private TextUi ui;
    private TaskList taskList;
    private StorageFile storageFile;

    public static void main(String... args) {
        new Main().run(args);
    }

    /**
     * Runs the program until exits.
     * @param args: init arguments
     */
    private void run(String... args) {
        init();
        loopUntilExit();
        finish();
    }

    /**
     * Initializes the required objects, loads data from the disk,
     * and print welcome messages
     * @param args: init arguments
     */
    private void init(String... args) {
        try {
            this.ui = new TextUi();
            this.taskList = new TaskList();
            this.storageFile = new StorageFile();

            try {
                int lines = storageFile.loadCsv(taskList);
                ui.printMessage(String.format("Load %d task(s) from file '%s'", lines, storageFile.getPath()));
            } catch (FileNotFoundException e) {
                storageFile.initCsv();
                ui.printMessage(String.format("Create new data file '%s'", storageFile.getPath()));
            } finally {
                ui.printWelcomeMsg(VERSION);
                ui.printDivider();
                ui.printIntroMsg();
                ui.printDivider();
            }
        } catch (Exception e) {
            ui.printMessage("init failed!");
            throw new RuntimeException();
        }
    }

    /**
     * Reads user input command and executes it until user inputs an exit command.
     */
    private void loopUntilExit() {
        Command command;
        do {
            String userInput = ui.getUserCommand();
            try {
                command = new CommandParser().parseCommand(userInput);
                ui.printDivider();
                CommandResult result = executeCommand(command);
                ui.printResult(result);
            } catch (Exception e) {
                command = null;
                ui.printMessage(e.getMessage());
            }
            ui.printDivider();
        } while (!ExitCommand.isExitCommand(command));
    }

    /**
     * Prints a goodbye message and writes data to the disk.
     */
    private void finish() {
        try {
            storageFile.writeCsv(taskList);
            ui.printMessage(String.format("Data has been written to '%s'", storageFile.getPath()));
        } catch (IOException e) {
            ui.printMultiMessage("Failed to save task list!", e.getMessage());
        } finally {
            ui.printGoodByeMsg();
        }
        System.exit(0);
    }

    /**
     * Prepares data that will be used by the command.
     * @param command command to prepare data for.
     */
    private void prepareData(Command command) {
        try {
            command.setTaskList(taskList);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * Executes the command.
     * @param command command to be executed.
     * @return command execution result.
     */
    private CommandResult executeCommand(Command command) {
        try {
            prepareData(command);
            return command.execute();
        } catch (Exception e) {
            ui.printMessage(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
