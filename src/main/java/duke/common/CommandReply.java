package duke.common;

/**
 * Keeps some common strings and formatted strings for replaying commands.
 */
public class CommandReply {
    public static final String ADD_TASK_HEADER = "Got it. I've added this task: \n";
    public static final String ADD_TASK_TAIL_F = "Now you have %d tasks in the list.";
    public static final String ADD_TASK_DUPLICATE = "This task is already added in the list.";

    public static final String TASK_INDEX_OUT_OF_BOUND_F = "The input task index %d is out of bound!";

    public static final String MARK_TASK_SUCCESS = "Nice! I've marked this task as done:\n";
    public static final String MARK_MARKED_TASK_F = "Task no. %d is already marked!";
    public static final String UNMARK_TASK_SUCCESS = "OK, I've marked this task as not done yet:\n";
    public static final String UNMARK_UNMARKED_TASK_F = "Task no. %d is already not marked!";

    public static final String DELETE_TASK_HEADER  = "Noted. I've removed this task:\n";
    public static final String DELETE_TASK_TAIL_F = "Now you have %d tasks in the list.";

    public static final String LIST_ITEM_TEMPLATE = "- %d. %s";
}

