import java.util.Scanner;

public class Isa {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] tasks = new String[100];
        boolean[] isDone = new boolean[100];
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
                for (int i = 0; i < taskCount; ++i) {
                    String status = isDone[i] ? "[X]" : "[ ]";
                    System.out.println(" " + (i + 1) + "." + status + " " + tasks[i]);
                }
            } else if (command.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(command.substring(5));
                int taskIndex = taskNumber - 1;

                isDone[taskIndex] = true;

                System.out.println(" Nice! I've marked this task as done:");
                System.out.println("   [X] " + tasks[taskIndex]);
            } else {
                tasks[taskCount] = command;
                isDone[taskCount] = false;
                taskCount++;
                System.out.println(" added: " + command);
            }
            System.out.println("____________________________________________________________");
        }
        scanner.close();
    }
}