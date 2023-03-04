package duke.command;

import duke.common.CommandReply;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Deletes a task from the task list using its index.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String COMMAND_USAGE = COMMAND_WORD
            + ": delete a task from the task list "
            + "Example: delete 1 2 3";

    private final List<Integer> toDeleteList = new ArrayList<>();

    public DeleteCommand(int... toDeleteIndex) {
        for (int toDelete : toDeleteIndex) {
            toDeleteList.add(toDelete);
        }
    }

    public DeleteCommand(Collection<Integer> toDeleteIndex) {
        toDeleteList.addAll(toDeleteIndex);
    }

    @Override
    public CommandResult execute() {
        List<String> results = new ArrayList<>();
        for (int toDelete : toDeleteList) {
            try {
                String deletedTask = taskList.getTask(toDelete).toString();
                taskList.deleteTask(toDelete);
                results.add(CommandReply.DELETE_TASK_HEADER);
                results.add(deletedTask + "\n");
                results.add(String.format(CommandReply.DELETE_TASK_TAIL_F, taskList.size()));
            } catch (IndexOutOfBoundsException e) {
                results.add(String.format(CommandReply.TASK_INDEX_OUT_OF_BOUND_F, toDelete));
            }
        }
        return new CommandResult(results);
    }
}
