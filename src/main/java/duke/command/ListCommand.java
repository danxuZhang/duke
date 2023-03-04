package duke.command;

import duke.common.CommandReply;
import duke.data.task.Task;

import java.util.ArrayList;

/**
 * Lists all tasks in the task list.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String COMMAND_USAGE = COMMAND_WORD
            + ": Display all tasks in the list with their indices";

    private static final String LIST_HEADER = "Here are the tasks in your list:\n";

    public ListCommand() {}

    @Override
    public CommandResult execute() {
        if (taskList.size() == 0) {
            return new CommandResult("You don't have any task in the list!");
        }
        ArrayList<String> replayMessages = new ArrayList<>();
        replayMessages.add(LIST_HEADER);
        int cnt = 0;
        for (Task task : taskList) {
            cnt += 1;
            replayMessages.add(String.format(CommandReply.LIST_ITEM_TEMPLATE, cnt, task.toString()));
        }
        return new CommandResult(replayMessages);
    }
}
