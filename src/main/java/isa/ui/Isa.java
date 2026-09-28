package isa.ui;

import isa.command.Command;
import isa.exception.IsaException;
import isa.exception.StorageException;
import isa.parser.Parser;
import isa.storage.LoadResult;
import isa.storage.Storage;
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
        Command parsedCommand = parser.createCommand(parser.parseCommand(command), command);
        parsedCommand.execute(taskList, ui, storage);
        return !parsedCommand.isExit();
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
}
