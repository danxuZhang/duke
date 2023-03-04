package duke.data.exception;

/**
 * Signals an error caused by marking a marked task or unmarking an unmarked task.
 */
public class DuplicateMarkException extends Exception {
    public DuplicateMarkException() {
        super();
    }

    public DuplicateMarkException(String msg) {
        super(msg);
    }
}
