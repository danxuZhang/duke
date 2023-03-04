package duke.data.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents tasks that need to be done before a specific date/time.
 */
public final class Deadline extends Task {
    private static final String TO_STR_FORMAT = "[D]%s (by: %s)";
    private static final String TO_CSV_FORMAT = "deadline,%s,\"%s\"";
    private LocalDate byDate;

    /**
     * Constructor initializing the content and deadline of the Deadline task.
     * The task is unmarked by default.
     * @param content content of the Deadline task.
     * @param byDate deadline of the Deadline task.
     */
    public Deadline(String content, LocalDate byDate) {
        this(content, byDate, false);
    }

    public Deadline(String content, LocalDate byDate, boolean isMarked) {
        super(content, isMarked);
        this.byDate = byDate;
    }

    public LocalDate getByDate() {
        return byDate;
    }

    public void setByDate(LocalDate by) {
        this.byDate = by;
    }


    /**
     * Converts the task to a csv line with label, mark and deadline.
     * e.g. For a marked Deadline: deadline,1,"do something","2023-01-01".
     * e.g. For an unmarked Deadline: deadline,0,"do nothing","2024-01-01".
     * @return an CSV line.
     */
    public String toCSV() {
        return String.format(TO_CSV_FORMAT, super.toCSV(), byDate);
    }
    /**
     * Converts the task to a string with label, mark status and deadline.
     * e.g. [D][ ] do something (by Apr 1 2023)
     * e.g. [D][X] do something else (by Jul 31 2023)
     * @return a string containing the task's label, mark status and deadline.
     */
    @Override
    public String toString() {
        String by = byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format(TO_STR_FORMAT, super.toString(), by);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else {
            return super.equals(obj)
                    && (obj instanceof Deadline)
                    && (((Deadline) obj).byDate.equals(this.byDate));
        }
    }
}
