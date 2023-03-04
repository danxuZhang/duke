package duke.data.exception;

/**
 * Signals an error trying to access or modify a task that is not in the task list.
 */
public class TaskNotFoundException extends IllegalArgumentException {
    public TaskNotFoundException() {
        super("Task not found!");
    }

    public TaskNotFoundException(String msg) {
        super(msg);
    }

}
