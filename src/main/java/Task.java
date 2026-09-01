// Task.java
public class Task {
    private final String description;
    private final char type;
    private final String by;
    private final String from;
    private final String to;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.type = 'T';
        this.by = null;
        this.from = null;
        this.to = null;
        this.isDone = false;
    }

    public Task(String description, String by) {
        this.description = description;
        this.type = 'D';
        this.by = by;
        this.from = null;
        this.to = null;
        this.isDone = false;
    }

    public Task(String description, String from, String to) {
        this.description = description;
        this.type = 'E';
        this.by = null;
        this.from = from;
        this.to = to;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        String taskDetails = "[" + type + "][" + getStatusIcon()
                + "] " + description;

        if (type == 'D') {
            taskDetails += " (by: " + by + ")";
        } else if (type == 'E') {
            taskDetails += " (from: " + from + " to: " + to + ")";
        }

        return taskDetails;
    }
}