package isa.ui;

import isa.command.Command;
import isa.command.ExitCommand;
import isa.command.ListCommand;
import isa.exception.IsaException;
import isa.exception.StorageException;
import isa.parser.Parser;
import isa.storage.LoadResult;
import isa.storage.Storage;
import isa.task.Task;
import isa.task.TaskList;

/**
 * Runs the Isa task manager.
 */
public class Isa {
    private static final String DATA_FILE_PATH = "./data/isa.txt";
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
            Command exitCommand = new ExitCommand();
            exitCommand.execute(taskList, ui, storage);
            return !exitCommand.isExit();
        case LIST:
            Command listCommand = new ListCommand();
            listCommand.execute(taskList, ui, storage);
            return !listCommand.isExit();
        case MARK:
            markTaskAsDone(command);
            break;
        case UNMARK:
            markTaskAsNotDone(command);
            break;
        case TODO:
            addTask(parser.parseTodo(command));
            break;
        case DEADLINE:
            addTask(parser.parseDeadline(command));
            break;
        case EVENT:
            addTask(parser.parseEvent(command));
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
        int taskIndex = parser.parseMarkIndex(command);
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
        int taskIndex = parser.parseUnmarkIndex(command);
        Task task = taskList.get(taskIndex);
        task.markAsNotDone();
        saveTasks();

        ui.showUnmarked(task);
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
        int taskIndex = parser.parseDeleteIndex(command, taskList.size());
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
