package duke.command;

import duke.data.task.Task;
import duke.common.CommandReply;

import java.util.List;
import java.util.ArrayList;

/**
 * Finds tasks that contains a keyword from the task list.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String COMMAND_USAGE =  COMMAND_WORD
            + ": find a task by searching for a keyword "
            + "Example: find book";
    public static final String FIND_HEADER = "Here are the matching tasks in your list:\n";

    private final String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public CommandResult execute() {
        List<String> results = new ArrayList<>();
        results.add(FIND_HEADER);
        for (int i = 0; i < taskList.size(); ++i) {
            Task task = taskList.getTask(i+1);
            if (task.getContent().contains(keyword)) {
                results.add(String.format(CommandReply.LIST_ITEM_TEMPLATE, i+1, task));
            }
        }
        return new CommandResult(results);
    }
}
