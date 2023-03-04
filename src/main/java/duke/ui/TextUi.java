package duke.ui;

import java.util.Scanner;
import java.util.List;

import duke.command.CommandResult;
import duke.common.Messages;

/**
 * Text UI of this command line application.
 */
public final class TextUi {
    private static final Scanner scanner = new Scanner(System.in);
    public TextUi() {
    }

    public void printDivider() {
        System.out.println(Messages.DIVIDER);
    }

    /**
     * Prints a welcome message to the user with current application version.
     * @param version current application version.
     */
    public void printWelcomeMsg(String version) {
        System.out.println(Messages.SPACER + Messages.MSG_GREETINGS);
        System.out.println(Messages.SPACER + "Version: " + version);
    }

    /**
     * Prints a message introducing the application.
     */
    public void printIntroMsg() {
        System.out.println(Messages.SPACER + Messages.MSG_INTRODUCTION);
    }

    /**
     * Prints a goodbye message to the user.
     */
    public void printGoodByeMsg() {
        System.out.println(Messages.SPACER + Messages.MSG_GOODBYE);
    }

    /**
     * Print a message string to the user.
     * @param msg message to be shown.
     */
    public void printMessage(String msg) {
        System.out.println(Messages.SPACER + msg);
    }

    /**
     * Print multiple messages to the user.
     * @param messages messages to be shown.
     */
    public void printMultiMessage(String... messages) {
        for (String msg : messages) {
            System.out.println(Messages.SPACER + msg);
        }
    }

    /**
     * Prints the execution result to the user.
     * @param result command execution result.
     */
    public void printResult(CommandResult result) {
        List<String> resultMessages = result.getResultMessages();
        for (String msg : resultMessages) {
            System.out.println(Messages.SPACER + msg);
        }
    }

    /**
     * Gets raw user input command and loops until the user input is not empty.
     * @return a raw text string of user input command.
     */
    public String getUserCommand() {
        System.out.println(Messages.SPACER + "Enter command: ");
        String input = scanner.nextLine().trim();
        while (input.isEmpty()) {
            System.out.println(Messages.SPACER + "Input is empty!");
            input = scanner.nextLine().trim();
        }
        return input;
    }
}
