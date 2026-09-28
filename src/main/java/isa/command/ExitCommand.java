package isa.command;

import isa.storage.Storage;
import isa.task.TaskList;
import isa.ui.Ui;

/**
 * Ends the application after showing a farewell message.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
