package duke.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import duke.common.CommandReply;
import duke.data.exception.DuplicateMarkException;

/**
 * Unmarks a task to be not done yet using its index.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String COMMAND_USAGE = COMMAND_WORD
            + ": unmark task(s)";
    private final List<Integer> toUnmarkList = new ArrayList<>();

    public UnmarkCommand(int... toUnmarkIndex) {
        for (int toMark : toUnmarkIndex) {
            toUnmarkList.add(toMark);
        }
    }

    public UnmarkCommand(Collection<Integer> toUnmarkIndex) {
        toUnmarkList.addAll(toUnmarkIndex);
    }

    @Override
    public CommandResult execute() {
        List<String> results = new ArrayList<>();
        for (int toUnmark : toUnmarkList) {
            try {
                taskList.unmarkTask(toUnmark);
                results.add(CommandReply.UNMARK_TASK_SUCCESS);
                results.add(taskList.getTask(toUnmark) + "\n");
            } catch (IndexOutOfBoundsException e) {
                results.add(String.format(CommandReply.TASK_INDEX_OUT_OF_BOUND_F, toUnmark));
            } catch (DuplicateMarkException e) {
                results.add(String.format(CommandReply.UNMARK_UNMARKED_TASK_F, toUnmark));
            }
        }
        return new CommandResult(results);
    }
}
