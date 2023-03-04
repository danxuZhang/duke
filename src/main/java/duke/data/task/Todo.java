package duke.data.task;

/**
 * Represents tasks without any date/time attached to it.
 * e.g., visit new theme park.
 */
public final class Todo extends Task {
    private static final String TO_STR_FORMAT = "[T]%s";
    private static final String TO_CSV_FORMAT = "todo,%s";

    public Todo(String content) {
        this(content, false);
    }

    public Todo(String content, boolean isMarked) {
        super(content, isMarked);
    }

    /**
     * Converts the task to a csv line.
     * e.g. todo,0,"do something"
     * e.g. todo,1,"do something else "
     * @return a csv line.
     */
    @Override
    public String toCSV() {
        return String.format(TO_CSV_FORMAT, super.toCSV());
    }

    /**
     * Converts the class to a string with label and marked status.
     * e.g. [T][ ] do something.
     * e.g. [T][X] do something else.
     * @return a string containing the task's label and marked status.
     */
    @Override
    public String toString() {
        return String.format(TO_STR_FORMAT, super.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else {
            return super.equals(obj);
        }
    }
}
