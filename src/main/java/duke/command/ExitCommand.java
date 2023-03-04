package duke.command;

/**
 * Exits the application.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String COMMAND_USAGE = COMMAND_WORD
            + ": show available commands and their usages";
    public static final String EXIT_MSG = "Exiting the application as requested...";

    public ExitCommand() {}

    @Override
    public CommandResult execute() {
        return new CommandResult(EXIT_MSG);
    }

    /**
     * Checks whether a command is an exit command or not.
     * @param toCheck command to check.
     * @return true if is an instance of ExitCommand else false.
     */
    public static boolean isExitCommand(Command toCheck) {
        return toCheck instanceof ExitCommand;
    }
}
