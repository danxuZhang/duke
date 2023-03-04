package duke.command;

import duke.data.TaskList;

/**
 * Represents an executable command object.
 */
public abstract class Command {
    protected TaskList taskList;

    public Command() {}

    /**
     * Prepare data for the command.
     * @param taskList task list to work on.
     */
    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Executes the command and returns execution result.
     * @return execution result.
     */
    public CommandResult execute() {
        throw new UnsupportedOperationException("This method is to be implemented by child classes.");
    }
}