package isa.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents a task that must be completed by a specific date.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter DISPLAY_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);

    private final LocalDate dueDate;

    /**
     * Creates a deadline with its description and due date.
     *
     * @param description Description of the deadline.
     * @param dueDate Date by which the task must be completed.
     */
    public Deadline(String description, LocalDate dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String toDataString() {
        return getBaseDataString("D") + " | " + dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + dueDate.format(DISPLAY_FORMAT) + ")";
    }
}
