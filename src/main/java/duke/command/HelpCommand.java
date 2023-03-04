package duke.command;

import java.util.ArrayList;
import java.util.List;

/**
 * Displays help information.
 */
public class HelpCommand extends Command{
    public static final String COMMAND_WORD = "help";
    public static final String COMMAND_USAGE = COMMAND_WORD
            + ": Show program usage instructions";

    private static final String HEADER = "Here are some sample usages: \n";

    @Override
    public CommandResult execute() {
        List<String> usages = new ArrayList<>();
        usages.add(HEADER);
        usages.add(TodoCommand.COMMAND_USAGE);
        usages.add(DeadlineCommand.COMMAND_USAGE);
        usages.add(EventCommand.COMMAND_USAGE);
        usages.add(ListCommand.COMMAND_USAGE);
        usages.add(FindCommand.COMMAND_USAGE);
        usages.add(MarkCommand.COMMAND_USAGE);
        usages.add(UnmarkCommand.COMMAND_USAGE);
        usages.add(DeleteCommand.COMMAND_USAGE);
        usages.add(HelpCommand.COMMAND_USAGE);
        usages.add(ExitCommand.COMMAND_USAGE);
        return new CommandResult(usages);
    }
}
