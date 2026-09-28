package isa.parser;

import isa.exception.IsaException;
import isa.task.Deadline;
import isa.task.Event;
import isa.task.Todo;

/**
 * Identifies commands and extracts their task arguments.
 */
public class Parser {
    private static final String COMMAND_MARK = "mark ";
    private static final String COMMAND_UNMARK = "unmark ";
    private static final String COMMAND_DEADLINE = "deadline ";
    private static final String COMMAND_EVENT = "event ";
    private static final String DEADLINE_SEPARATOR = " /by ";
    private static final String EVENT_FROM_SEPARATOR = " /from ";
    private static final String EVENT_TO_SEPARATOR = " /to ";

    /**
     * Command types recognized by Isa.
     */
    public enum CommandType {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE
    }

    /**
     * Returns the type of a command without changing its arguments.
     *
     * @param command Full command entered by the user.
     * @return Type of the recognized command.
     * @throws IsaException If the command is not recognized.
     */
    public CommandType parseCommand(String command) throws IsaException {
        if (command.equals("bye")) {
            return CommandType.BYE;
        } else if (command.equals("list")) {
            return CommandType.LIST;
        } else if (command.startsWith(COMMAND_MARK)) {
            return CommandType.MARK;
        } else if (command.startsWith(COMMAND_UNMARK)) {
            return CommandType.UNMARK;
        } else if (command.equals("todo") || command.startsWith("todo ")) {
            return CommandType.TODO;
        } else if (command.startsWith(COMMAND_DEADLINE)) {
            return CommandType.DEADLINE;
        } else if (command.startsWith(COMMAND_EVENT)) {
            return CommandType.EVENT;
        } else if (command.equals("delete") || command.startsWith("delete ")) {
            return CommandType.DELETE;
        } else {
            throw new IsaException("i don't understand :((");
        }
    }

    /**
     * Parses the task number in a mark command as a zero-based index.
     *
     * @param command Mark command entered by the user.
     * @return Zero-based task index.
     */
    public int parseMarkIndex(String command) {
        return Integer.parseInt(command.substring(COMMAND_MARK.length())) - 1;
    }

    /**
     * Parses the task number in an unmark command as a zero-based index.
     *
     * @param command Unmark command entered by the user.
     * @return Zero-based task index.
     */
    public int parseUnmarkIndex(String command) {
        return Integer.parseInt(command.substring(COMMAND_UNMARK.length())) - 1;
    }

    /**
     * Parses a todo command into a task.
     *
     * @param command Todo command entered by the user.
     * @return Parsed todo task.
     * @throws IsaException If the description is empty.
     */
    public Todo parseTodo(String command) throws IsaException {
        String description = command.substring("todo".length()).trim();

        if (description.isEmpty()) {
            throw new IsaException("please enter a todo!");
        }

        return new Todo(description);
    }

    /**
     * Parses a deadline command into a task.
     *
     * @param command Deadline command entered by the user.
     * @return Parsed deadline task.
     */
    public Deadline parseDeadline(String command) {
        String details = command.substring(COMMAND_DEADLINE.length());
        String[] parts = details.split(DEADLINE_SEPARATOR, 2);
        String description = parts[0];
        String dueDate = parts[1];

        return new Deadline(description, dueDate);
    }

    /**
     * Parses an event command into a task.
     *
     * @param command Event command entered by the user.
     * @return Parsed event task.
     */
    public Event parseEvent(String command) {
        String details = command.substring(COMMAND_EVENT.length());
        int fromPosition = details.indexOf(EVENT_FROM_SEPARATOR);
        int toPosition = details.indexOf(EVENT_TO_SEPARATOR);
        String description = details.substring(0, fromPosition);
        String startTime = details.substring(
                fromPosition + EVENT_FROM_SEPARATOR.length(), toPosition);
        String endTime = details.substring(
                toPosition + EVENT_TO_SEPARATOR.length());

        return new Event(description, startTime, endTime);
    }

    /**
     * Parses and validates the task number in a delete command.
     *
     * @param command Delete command entered by the user.
     * @param taskCount Number of tasks available for deletion.
     * @return Zero-based task index.
     * @throws IsaException If the task number is missing, invalid, or out of range.
     */
    public int parseDeleteIndex(String command, int taskCount) throws IsaException {
        String taskNumber = command.substring("delete".length()).trim();

        if (taskNumber.isEmpty()) {
            throw new IsaException("please enter the number of the task to delete!");
        }

        int taskIndex;

        try {
            taskIndex = Integer.parseInt(taskNumber) - 1;
        } catch (NumberFormatException e) {
            throw new IsaException("please enter a valid task number!");
        }

        if (taskIndex < 0 || taskIndex >= taskCount) {
            throw new IsaException("that task number does not exist!");
        }

        return taskIndex;
    }
}
