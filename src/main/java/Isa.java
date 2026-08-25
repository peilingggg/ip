import java.util.Scanner;

public class Isa {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Task[] tasks = new Task[100];
        int taskCount = 0;

        System.out.println("Helloo! I'm Isa");
        System.out.println("How can I help you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String command = scanner.nextLine();
            System.out.println("____________________________________________________________");

            if (command.equals("bye")) {
                System.out.println("Bye. Hope you have a nice day!");
                System.out.println("____________________________________________________________");
                break;
            } else if (command.equals("list")) {
                System.out.println(" Here are the tasks in your list:");

                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + "." + tasks[i]);
                }
            } else if (command.startsWith("mark ")) {
                int taskIndex = Integer.parseInt(command.substring(5)) - 1;
                tasks[taskIndex].markAsDone();

                System.out.println(" Nice! I've marked this task as done:");
                System.out.println("   " + tasks[taskIndex]);
            } else if (command.startsWith("unmark ")) {
                int taskIndex = Integer.parseInt(command.substring(7)) - 1;
                tasks[taskIndex].markAsNotDone();

                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println("   " + tasks[taskIndex]);
            } else {
                tasks[taskCount] = new Task(command);
                System.out.println(" added: " + tasks[taskCount]);
                taskCount++;
            }
            System.out.println("____________________________________________________________");
        }
        scanner.close();
    }
}