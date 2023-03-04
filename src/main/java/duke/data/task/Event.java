package duke.data.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * tasks that start at a specific date/time and ends at a specific date/time.
 * e.g., (a) team project meeting 2/10/2019 2-4pm.
 *      (b) orientation week 4/10/2019 to 11/10/2019.
 */
public final class Event extends Task {
    private static final String TO_STR_FORMAT = "[E]%s (from: %s, to: %s)";
    private static final String TO_CSV_FORMAT = "event,%s,\"%s\",\"%s\"";
    private LocalDate fromDate;
    private LocalDate toDate;

    /**
     * Constructor initializing the content, start time, end time of the Event task.
     * The task is unmarked by default.
     * @param content content of the Event.
     * @param fromDate starting time of the Event.
     * @param toDate ending time of the Event.
     */
    public Event(String content, LocalDate fromDate, LocalDate toDate) {
        this(content, fromDate, toDate, false);
    }

    public Event(String content, LocalDate fromDate, LocalDate toDate, boolean isMarked) {
        super(content, isMarked);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    /**
     * Converts the task to a csv line with label, mark and deadline.
     * e.g. For a marked Event: event,1,"do something","2023-01-03","2023-01-04"
     * e.g. For an unmarked Event: event,0,"do nothing","2023-01-05","2023-01-06.
     * @return an CSV line.
     */
    public String toCSV() {
        return String.format(TO_CSV_FORMAT, super.toCSV(), fromDate, toDate);
    }

    /**
     * Converts the task to a string with label, marked status, starting and ending time.
     * e.g. [E][ ] do something (from: Jan 3 2023 to: Jan 4 2023)
     * e.g. [E][ ] do something else (from: Jan 5 2023 to: Jan 6 2023)
     * @return a string containing the task's label, marked status, starting and ending time.
     */
    @Override
    public String toString() {
        String from = fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String to = toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format(TO_STR_FORMAT, super.toString(), from, to);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else {
            return super.equals(obj)
                    && (obj instanceof Event)
                    && (((Event) obj).fromDate.equals(this.fromDate))
                    && (((Event) obj).toDate.equals(this.toDate));
        }
    }
}
