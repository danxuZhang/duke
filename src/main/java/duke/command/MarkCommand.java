package duke.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import duke.common.CommandReply;
import duke.data.exception.DuplicateMarkException;

/**
 * Marks a task to be done using its index.
 */
public class MarkCommand extends Command{
    public static final String COMMAND_WORD = "mark";
    public static final String COMMAND_USAGE = COMMAND_WORD
            + ": mark task(s) to be done";
    private final List<Integer> toMarkList = new ArrayList<>();

    public MarkCommand(int... toMarkIndex) {
        for (int toMark : toMarkIndex) {
            toMarkList.add(toMark);
        }
    }

    public MarkCommand(Collection<Integer> toMarkIndex) {
        toMarkList.addAll(toMarkIndex);
    }

    @Override
    public CommandResult execute() {
        List<String> results = new ArrayList<>();
        for (int toMark : toMarkList) {
            try {
                taskList.markTask(toMark);
                results.add(CommandReply.MARK_TASK_SUCCESS);
                results.add(taskList.getTask(toMark) + "\n");
            } catch (IndexOutOfBoundsException e) {
                results.add(String.format(CommandReply.TASK_INDEX_OUT_OF_BOUND_F, toMark));
            } catch (DuplicateMarkException e) {
                results.add(String.format(CommandReply.MARK_MARKED_TASK_F, toMark));
            }
        }
        return new CommandResult(results);
    }
}
