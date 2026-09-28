package isa.parser;

import isa.exception.IsaException;

/**
 * Identifies the type of a command entered by the user.
 */
public class Parser {
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
        } else if (command.startsWith("mark ")) {
            return CommandType.MARK;
        } else if (command.startsWith("unmark ")) {
            return CommandType.UNMARK;
        } else if (command.equals("todo") || command.startsWith("todo ")) {
            return CommandType.TODO;
        } else if (command.startsWith("deadline ")) {
            return CommandType.DEADLINE;
        } else if (command.startsWith("event ")) {
            return CommandType.EVENT;
        } else if (command.equals("delete") || command.startsWith("delete ")) {
            return CommandType.DELETE;
        } else {
            throw new IsaException("i don't understand :((");
        }
    }
}
