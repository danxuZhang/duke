package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;
import duke.command.FindCommand;

/**
 * Parses user input to command.
 */
public final class CommandParser {
    private static final String OOPS = "☹ OOPS!!! ";

    public CommandParser() {}

    /**
     * Parses raw user input text command to an executable command object.
     * @param userInputCommand raw text of user input.
     * @return an executable command object.
     * @throws IllegalArgumentException if the user input is not valid.
     */
    public Command parseCommand(String userInputCommand) throws IllegalArgumentException {
        String[] args = userInputCommand.split(" ");

        switch (args[0]) {
        case TodoCommand.COMMAND_WORD:
            return parseTodo(args);
        case DeadlineCommand.COMMAND_WORD:
            return parseDeadline(args);
        case EventCommand.COMMAND_WORD:
            return parseEvent(args);
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case FindCommand.COMMAND_WORD:
            return parseFind(args);
        case MarkCommand.COMMAND_WORD:
            return parseMark(args);
        case UnmarkCommand.COMMAND_WORD:
            return parseUnmark(args);
        case DeleteCommand.COMMAND_WORD:
            return parseDelete(args);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case HelpCommand.COMMAND_WORD: //fall through
        default:
            return new HelpCommand();
        }
    }

    /**
     * Parses user input text arguments to an executable TodoCommand.
     * @param args text arguments to create the TodoCommand with description.
     * @return an executable TodoCommand.
     * @throws IllegalArgumentException if the description is empty.
     */
    private Command parseTodo(String[] args) throws  IllegalArgumentException {
        assert args[0].equals(TodoCommand.COMMAND_WORD);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < args.length; ++i) {
            sb.append(args[i]).append(" ");
        }
        if (sb.toString().isEmpty()) {
            throw new IllegalArgumentException(OOPS + "The description of a todo cannot be empty.");
        }
        String content = sb.toString().trim();
        return new TodoCommand(content);
    }

    /**
     * Parses user input text arguments to an executable DeadlineCommand.
     * @param args text arguments to create the DeadlineCommand with description and by date.
     * @return an executable DeadlineCommand.
     * @throws IllegalArgumentException if the description or by date is empty, or invalid date format.
     */
    private Command parseDeadline(String[] args) throws IllegalArgumentException {
        assert args[0].equals(DeadlineCommand.COMMAND_WORD);
        StringBuilder contentSb = new StringBuilder();
        StringBuilder bySb = new StringBuilder();

        int byIndex = -1;
        for (int i = 0; i < args.length; ++i) {
            if (args[i].equals("/by")) {
                byIndex = i;
            }
        }

        if (byIndex == -1) {
            throw new IllegalArgumentException(OOPS + "Cannot find the by time of the event.");
        }

        for (int i = 1; i < args.length; ++i) {
            if (i == byIndex) {
                continue;
            }
            if (i < byIndex) {
                contentSb.append(args[i]).append(" ");
            } else {
                bySb.append(args[i]).append(" ");
            }
        }

        if (contentSb.toString().isEmpty()) {
            throw new IllegalArgumentException(OOPS + "The description of a deadline cannot be empty.");
        } else if (bySb.toString().isEmpty()) {
            throw new IllegalArgumentException(OOPS + "The by time of a deadline cannot be empty.");
        }

        String content = contentSb.toString().trim();
        String by = bySb.toString().trim();

        try {
            LocalDate byDate = LocalDate.parse(by, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            return new DeadlineCommand(content, byDate);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(OOPS + "Invalid date time. Format: dd/mm/yyyy");
        }
    }

    /**
     * Parses user input text arguments to an executable EventCommand.
     * @param args text arguments to create the EventCommand with description, from and to dates.
     * @return an executable EventCommand.
     * @throws IllegalArgumentException if the description/from/to are empty, or invalid date format.
     */
    private Command parseEvent(String[] args) throws IllegalArgumentException{
        assert args[0].equals(EventCommand.COMMAND_WORD);
        StringBuilder contentSb = new StringBuilder();
        StringBuilder fromSb = new StringBuilder();
        StringBuilder toSb = new StringBuilder();

        int fromIndex = -1;
        int toIndex = -1;
        for (int i = 0; i < args.length; ++i) {
            if (args[i].equals("/from")) {
                fromIndex = i;
            } else if (args[i].equals("/to")) {
                toIndex = i;
            }
        }

        if (fromIndex == -1 || toIndex == -1) {
            throw new IllegalArgumentException(OOPS + "Cannot find from or to time of the event!");
        }

        for (int i = 1; i < args.length; ++i) {
            if (i < fromIndex) {
                contentSb.append(args[i]).append(" ");
            } else if (i > fromIndex && i < toIndex) {
                fromSb.append(args[i]).append(" ");
            } else if (i > toIndex) {
                toSb.append(args[i]).append(" ");
            }
        }

        if (contentSb.toString().isEmpty() || fromSb.toString().isEmpty() || toSb.toString().isEmpty()) {
            throw new IllegalArgumentException(OOPS + "The content/from/to of an event cannot be empty!");
        }

        String content = contentSb.toString().trim();
        String from = fromSb.toString().trim();
        String to = toSb.toString().trim();

        try {
            LocalDate fromDate = LocalDate.parse(from, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalDate toDate = LocalDate.parse(to, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            return new EventCommand(content, fromDate, toDate);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(OOPS + "Invalid date time. Format: dd/mm/yyyy");
        }

    }

    /**
     * Parses user input text arguments to an executable FindCommand.
     * @param args keyword to find.
     * @return an executable FindCommand.
     * @throws IllegalArgumentException when there is no keyword or more than one keywords.
     */
    private Command parseFind(String[] args) throws IllegalArgumentException {
        assert args[0].equals(FindCommand.COMMAND_WORD);
        if (args.length == 1) {
            throw new IllegalArgumentException(OOPS + "Cannot find keyword to search.");
        } else if (args.length > 2) {
            throw new IllegalArgumentException(OOPS + "Can only search for one keyword.");
        } else { //args.length == 2
            String keyword = args[1];
            return new FindCommand(keyword);
        }
    }

    /**
     * Parses user input text arguments to an executable MarkCommand.
     * @param args index(es) of the task(s) to mark.
     * @return an executable MarkCommand.
     * @throws IllegalArgumentException if no task to mark or input id is not a number.
     */
    private Command parseMark(String[] args) throws IllegalArgumentException {
        assert args[0].equals(MarkCommand.COMMAND_WORD);
        if (args.length == 1) {
            throw new IllegalArgumentException(OOPS + "Cannot find task to mark.");
        }
        if (args.length == 2) {
            try {
                return new MarkCommand(Integer.parseInt(args[1]));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(OOPS + args[1] + " is not a valid number!");
            }
        } else {
            return new MarkCommand(parseArgsToInt(args, 1));
        }
    }

    /**
     * Parses user input text arguments to an executable UnmarkCommand.
     * @param args index(es) of the task(s) to unmark.
     * @return an executable UnmarkCommand.
     * @throws IllegalArgumentException if no task to unmark or input id is not a number.
     */
    private Command parseUnmark(String[] args) throws IllegalArgumentException {
        assert args[0].equals(UnmarkCommand.COMMAND_WORD);
        if (args.length == 1) {
            throw new IllegalArgumentException(OOPS + "Cannot find task to mark.");
        }
        if (args.length == 2) {
            try {
                return new UnmarkCommand(Integer.parseInt(args[1]));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(OOPS + args[1] + " is not a valid number!");
            }
        } else {
            return new UnmarkCommand(parseArgsToInt(args, 1));
        }
    }

    /**
     * Parses user input text arguments to an executable UnmarkCommand.
     * @param args index(es) of the task(s) to delete.
     * @return an executable DeleteCommand.
     * @throws IllegalArgumentException if no task to delete or input id is not a number.
     */
    private Command parseDelete(String[] args) throws IllegalArgumentException {
        assert args[0].equals(DeleteCommand.COMMAND_WORD);
        if (args.length == 1) {
            throw new IllegalArgumentException(OOPS + "Cannot find task to delete.");
        }
        if (args.length == 2) {
            try {
                return new DeleteCommand(Integer.parseInt(args[1]));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(OOPS + args[1] + " is not a valid number!");
            }
        } else {
            return new UnmarkCommand(parseArgsToInt(args, 1));
        }
    }

    /**
     * A helper method to parse string arguments of numbers to a list of integers.
     * @param args string arguments of numbers.
     * @param startId starting id to parse.
     * @return parsing result.
     */
    private List<Integer> parseArgsToInt(String[] args, int startId) {
        List<Integer> toParse = new ArrayList<>();
        for (int i = startId; i < args.length; ++i) {
            try {
                int id = Integer.parseInt(args[i]);
                toParse.add(id);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(OOPS + args[i] + " is not a valid number!");
            }
        }
        return toParse;
    }
}
