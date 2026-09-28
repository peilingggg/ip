package isa.ui;

import isa.exception.IsaException;
import isa.exception.StorageException;
import isa.parser.Parser;
import isa.storage.LoadResult;
import isa.storage.Storage;
import isa.task.Deadline;
import isa.task.Event;
import isa.task.Task;
import isa.task.TaskList;
import isa.task.Todo;

/**
 * Runs the Isa task manager.
 */
public class Isa {
    private static final String DATA_FILE_PATH = "./data/isa.txt";
    private static final String COMMAND_DEADLINE = "deadline ";
    private static final String COMMAND_EVENT = "event ";
    private static final String COMMAND_MARK = "mark ";
    private static final String COMMAND_UNMARK = "unmark ";
    private static final String COMMAND_DELETE = "delete ";
    private static final String DEADLINE_SEPARATOR = " /by ";
    private static final String EVENT_FROM_SEPARATOR = " /from ";
    private static final String EVENT_TO_SEPARATOR = " /to ";

    private final Ui ui = new Ui();
    private final Parser parser = new Parser();
    private final Storage storage = new Storage(DATA_FILE_PATH);
    private TaskList taskList = new TaskList();

    /**
     * Starts Isa and processes commands until the user exits.
     *
     * @param args Command-line arguments; not used.
     */
    public static void main(String[] args) {
        new Isa().run();
    }

    /**
     * Runs the command-reading loop.
     */
    private void run() {
        ui.showGreeting();
        loadTasks();

        while (true) {
            String command = ui.readCommand();
            ui.showDivider();

            try {
                if (!executeCommand(command)) {
                    ui.showDivider();
                    break;
                }
            } catch (IsaException e) {
                ui.showError(e.getMessage());
            }

            ui.showDivider();
        }

        ui.close();
    }

    /**
     * Executes a command and indicates whether Isa should continue running.
     *
     * @param command Command entered by the user.
     * @return {@code false} when Isa should exit, or {@code true} otherwise.
     */
    private boolean executeCommand(String command) throws IsaException {
        switch (parser.parseCommand(command)) {
        case BYE:
            ui.showGoodbye();
            return false;
        case LIST:
            ui.showTasks(taskList);
            break;
        case MARK:
            markTaskAsDone(command);
            break;
        case UNMARK:
            markTaskAsNotDone(command);
            break;
        case TODO:
            addTodo(command);
            break;
        case DEADLINE:
            addDeadline(command);
            break;
        case EVENT:
            addEvent(command);
            break;
        case DELETE:
            deleteTask(command);
            break;
        default:
            throw new AssertionError("Unexpected command type");
        }

        return true;
    }

    /**
     * Marks the task specified by a command as done.
     *
     * @param command Mark command entered by the user.
     */
    private void markTaskAsDone(String command) {
        int taskIndex = parseTaskIndex(command, COMMAND_MARK);
        Task task = taskList.get(taskIndex);
        task.markAsDone();
        saveTasks();

        ui.showMarked(task);
    }

    /**
     * Marks the task specified by a command as not done.
     *
     * @param command Unmark command entered by the user.
     */
    private void markTaskAsNotDone(String command) {
        int taskIndex = parseTaskIndex(command, COMMAND_UNMARK);
        Task task = taskList.get(taskIndex);
        task.markAsNotDone();
        saveTasks();

        ui.showUnmarked(task);
    }

    /**
     * Converts a task number in a command to an array index.
     *
     * @param command Command containing the task number.
     * @param commandPrefix Prefix before the task number.
     * @return Zero-based task index.
     */
    private int parseTaskIndex(String command, String commandPrefix) {
        return Integer.parseInt(command.substring(commandPrefix.length())) - 1;
    }

    /**
     * Adds a todo from the supplied command.
     *
     * @param command Todo command entered by the user.
     */
    private void addTodo(String command) throws IsaException {
        String description = command.substring("todo".length()).trim();

        if (description.isEmpty()) {
            throw new IsaException("please enter a todo!");
        }

        addTask(new Todo(description));
    }

    /**
     * Adds a deadline from the supplied command.
     *
     * @param command Deadline command entered by the user.
     */
    private void addDeadline(String command) {
        String details = command.substring(COMMAND_DEADLINE.length());
        String[] parts = details.split(DEADLINE_SEPARATOR, 2);
        String description = parts[0];
        String dueDate = parts[1];

        addTask(new Deadline(description, dueDate));
    }

    /**
     * Adds an event from the supplied command.
     *
     * @param command Event command entered by the user.
     */
    private void addEvent(String command) {
        String details = command.substring(COMMAND_EVENT.length());
        int fromPosition = details.indexOf(EVENT_FROM_SEPARATOR);
        int toPosition = details.indexOf(EVENT_TO_SEPARATOR);
        String description = details.substring(0, fromPosition);
        String startTime = details.substring(
                fromPosition + EVENT_FROM_SEPARATOR.length(), toPosition);
        String endTime = details.substring(
                toPosition + EVENT_TO_SEPARATOR.length());

        addTask(new Event(description, startTime, endTime));
    }

    /**
     * Stores and acknowledges a newly created task.
     *
     * @param task Task to add.
     */
    private void addTask(Task task) {
        taskList.add(task);
        saveTasks();

        ui.showAdded(task, taskList.size());
    }

    /**
     * Deletes the task specified by a command.
     *
     * @param command Delete command entered by the user.
     * @throws IsaException If the task number is missing, invalid, or out of range.
     */
    private void deleteTask(String command) throws IsaException {
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

        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            throw new IsaException("that task number does not exist!");
        }

        Task removedTask = taskList.remove(taskIndex);
        saveTasks();

        ui.showDeleted(removedTask, taskList.size());
    }

    private void loadTasks() {
        try {
            LoadResult loadResult = storage.load();
            taskList = loadResult.getTaskList();

            for (String warning : loadResult.getWarnings()) {
                ui.showLoadWarning(warning);
            }
        } catch (StorageException e) {
            ui.showLoadError(e.getMessage());
        }
    }

    /*
     * Saves all tasks and reports write failures without stopping Isa.
     */
    private void saveTasks() {
        try {
            storage.save(taskList);
        } catch (StorageException e) {
            ui.showSaveWarning(e.getMessage());
        }
    }
}
