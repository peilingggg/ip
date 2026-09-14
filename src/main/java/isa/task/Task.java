package isa.task;

// Task.java
public class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
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

    /**
     * Returns this task in the format used by the data file.
     *
     * @return File representation of this task.
     */
    public String toDataString() {
        return getBaseDataString("T");
    }

    /**
     * Returns the shared file data for a particular task type.
     *
     * @param taskType Letter representing the task type.
     * @return Shared file representation of the task.
     */
    protected String getBaseDataString(String taskType) {
        String status = isDone ? "1" : "0";
        return taskType + " | " + status + " | " + escapeDataField(description);
    }

    /**
     * Escapes characters that have a special meaning in the data file.
     *
     * @param value Field value to escape.
     * @return Escaped field value.
     */
    protected String escapeDataField(String value) {
        return value
                .replace("\\", "\\\\")
                .replace("|", "\\|")
                .replace("\n", "\\n")
                .replace("\r", "\\r");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
