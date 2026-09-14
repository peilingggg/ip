package isa.storage;

import isa.task.TaskList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Saves task data to the hard disk.
 */
public class Storage {
    private final Path filePath;

    /**
     * Creates storage that writes to the specified file.
     *
     * @param filePath Path of the data file.
     */
    public Storage(String filePath) {
        this.filePath = Path.of(filePath);
    }

    /**
     * Saves all tasks to the data file.
     *
     * @param taskList Tasks to save.
     * @throws IOException If the file cannot be written.
     */
    public void save(TaskList taskList) throws IOException {
        Path parentDirectory = filePath.getParent();

        if (parentDirectory != null) {
            Files.createDirectories(parentDirectory);
        }

        List<String> taskData = new ArrayList<>();

        for (int i = 0; i < taskList.size(); i++) {
            taskData.add(taskList.get(i).toDataString());
        }

        Files.write(filePath, taskData);
    }
}
