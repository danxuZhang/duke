package duke.data.exception;

/**
 * Signals an error when there are duplicate tasks in the task list.
 */
public class DuplicateTaskException extends Exception {
    public DuplicateTaskException() {
        super("Duplicate task(es)!");
    }
    public DuplicateTaskException(String msg) {
        super(msg);
    }
}
