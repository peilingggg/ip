// Isa.java
import java.util.Scanner;

public class Isa {
    private static final int MAX_TASK_COUNT = 100;
    private static final String DIVIDER =
            "____________________________________________________________";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[MAX_TASK_COUNT];
        int taskCount = 0;

        System.out.println("Helloo! I'm Isa");
        System.out.println("How can I help you?");
        System.out.println(DIVIDER);

        while (true) {
            String command = scanner.nextLine();
            System.out.println(DIVIDER);

            if (command.equals("bye")) {
                System.out.println(" Bye. Hope you have a nice day!");
                System.out.println(DIVIDER);
                break;
            } else if (command.equals("list")) {
                System.out.println(" Here are the tasks in your list:");

                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + "." + tasks[i]);
                }
            } else if (command.startsWith("mark ")) {
                int taskIndex =
                        Integer.parseInt(command.substring(5)) - 1;

                tasks[taskIndex].markAsDone();

                System.out.println(
                        " Nice! I've marked this task as done:");
                System.out.println("   " + tasks[taskIndex]);
            } else if (command.startsWith("unmark ")) {
                int taskIndex =
                        Integer.parseInt(command.substring(7)) - 1;

                tasks[taskIndex].markAsNotDone();

                System.out.println(
                        " OK, I've marked this task as not done yet:");
                System.out.println("   " + tasks[taskIndex]);
            } else if (command.startsWith("todo ")) {
                String description = command.substring(5);

                tasks[taskCount] = new Task(description);
                taskCount++;

                printTaskAdded(tasks[taskCount - 1], taskCount);
            } else if (command.startsWith("deadline ")) {
                String details = command.substring(9);
                String[] parts = details.split(" /by ", 2);

                String description = parts[0];
                String by = parts[1];

                tasks[taskCount] = new Task(description, by);
                taskCount++;

                printTaskAdded(tasks[taskCount - 1], taskCount);
            } else if (command.startsWith("event ")) {
                String details = command.substring(6);

                int fromPosition = details.indexOf(" /from ");
                int toPosition = details.indexOf(" /to ");

                String description =
                        details.substring(0, fromPosition);
                String from = details.substring(
                        fromPosition + 7, toPosition);
                String to = details.substring(toPosition + 5);

                tasks[taskCount] = new Task(description, from, to);
                taskCount++;

                printTaskAdded(tasks[taskCount - 1], taskCount);
            }

            System.out.println(DIVIDER);
        }

        scanner.close();
    }

    private static void printTaskAdded(Task task, int taskCount) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(
                " Now you have " + taskCount + " tasks in the list.");
    }
}