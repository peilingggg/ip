/**
 * Represents a task that must be completed by a specific time.
 */
public class Deadline extends Task {
    private final String dueDate;

    /**
     * Creates a deadline with its description and due date.
     *
     * @param description Description of the deadline.
     * @param dueDate Date or time by which the task must be completed.
     */
    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate + ")";
    }
}
