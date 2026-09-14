package isa.task;

/**
 * Stores and provides access to the user's tasks.
 */
public class TaskList {
    private static final int MAX_TASK_COUNT = 100;

    private final Task[] tasks = new Task[MAX_TASK_COUNT];
    private int taskCount = 0;

    /**
     * Adds a task to the end of the list.
     *
     * @param task Task to add.
     */
    public void add(Task task) {
        tasks[taskCount] = task;
        taskCount++;
    }

    /**
     * Returns the task at a zero-based index.
     *
     * @param index Zero-based task index.
     * @return Task at the specified index.
     */
    public Task get(int index) {

        return tasks[index];
    }

    /**
     * Returns the number of stored tasks.
     *
     * @return Number of stored tasks.
     */
    public int size() {

        return taskCount;
    }

    /**
     * Removes and returns the task at a zero-based index.
     *
     * @param index Zero-based index of the task to remove.
     * @return Removed task.
     */
    public Task remove(int index) {
        Task removedTask = tasks[index];

        for (int i = index; i < taskCount - 1; i++) {
            tasks[i] = tasks[i + 1];
        }

        tasks[taskCount - 1] = null;
        taskCount--;
        return removedTask;
    }
}
