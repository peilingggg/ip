/**
 * Represents a task that occurs over a time range.
 */
public class Event extends Task {
    private final String startTime;
    private final String endTime;

    /**
     * Creates an event with its description and time range.
     *
     * @param description Description of the event.
     * @param startTime Time at which the event starts.
     * @param endTime Time at which the event ends.
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + startTime + " to: " + endTime + ")";
    }
}
