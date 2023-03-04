package duke.command;

import duke.data.exception.DuplicateTaskException;
import duke.data.task.Task;
import duke.data.task.Todo;
import duke.common.CommandReply;

/**
 * Creates a todo task to the task list.
 */
public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    public static final String COMMAND_USAGE = COMMAND_WORD
            + ": add a todo task";

    private final String content;

    public TodoCommand(String content) {
        this.content = content;
    }

    @Override
    public CommandResult execute() {
        Task task = new Todo(content);
        try {
            taskList.addTask(task);
        } catch (DuplicateTaskException e) {
            return new CommandResult("This task is already added in the list.");
        }
        return new CommandResult(
                CommandReply.ADD_TASK_HEADER,
                task + "\n",
                String.format(CommandReply.ADD_TASK_TAIL_F, taskList.size())
        );
    }
}
