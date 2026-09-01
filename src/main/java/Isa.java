import java.util.Scanner;

/**
 * Runs the Isa task manager.
 */
public class Isa {
    private static final String DIVIDER =
            "____________________________________________________________";
    private static final String COMMAND_TODO = "todo ";
    private static final String COMMAND_DEADLINE = "deadline ";
    private static final String COMMAND_EVENT = "event ";
    private static final String COMMAND_MARK = "mark ";
    private static final String COMMAND_UNMARK = "unmark ";
    private static final String DEADLINE_SEPARATOR = " /by ";
    private static final String EVENT_FROM_SEPARATOR = " /from ";
    private static final String EVENT_TO_SEPARATOR = " /to ";

    private final Scanner scanner = new Scanner(System.in);
    private final TaskList taskList = new TaskList();

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
        printGreeting();

        while (true) {
            String command = scanner.nextLine();
            System.out.println(DIVIDER);

            if (!executeCommand(command)) {
                System.out.println(DIVIDER);
                break;
            }

            System.out.println(DIVIDER);
        }

        scanner.close();
    }

    /**
     * Executes a command and indicates whether Isa should continue running.
     *
     * @param command Command entered by the user.
     * @return {@code false} when Isa should exit, or {@code true} otherwise.
     */
    private boolean executeCommand(String command) {
        if (command.equals("bye")) {
            System.out.println("Bye. Hope you have a nice day!");
            return false;
        } else if (command.equals("list")) {
            printTasks();
        } else if (command.startsWith(COMMAND_MARK)) {
            markTaskAsDone(command);
        } else if (command.startsWith(COMMAND_UNMARK)) {
            markTaskAsNotDone(command);
        } else if (command.startsWith(COMMAND_TODO)) {
            addTodo(command);
        } else if (command.startsWith(COMMAND_DEADLINE)) {
            addDeadline(command);
        } else if (command.startsWith(COMMAND_EVENT)) {
            addEvent(command);
        }

        return true;
    }

    /**
     * Prints Isa's greeting.
     */
    private void printGreeting() {
        System.out.println("Helloo! I'm Isa");
        System.out.println("How can I help you?");
        System.out.println(DIVIDER);
    }

    /**
     * Prints all tasks in their current order.
     */
    private void printTasks() {
        System.out.println(" Here are the tasks in your list:");

        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(" " + (i + 1) + "." + taskList.get(i));
        }
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

        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task);
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

        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task);
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
    private void addTodo(String command) {
        String description = command.substring(COMMAND_TODO.length());
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

        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(
                " Now you have " + taskList.size() + " tasks in the list.");
    }
}
