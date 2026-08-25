import java.util.Scanner;

public class Isa {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] tasks = new String[100];
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
            } /*else if (command.equals("list")) {
                for (int i = 0; i < taskCount; ++i) {
                    System.out.println(" " + (i + 1) + ". " + tasks[i]);
                }
            } else {
                tasks[taskCount] = command;
                taskCount++;
                System.out.println(" added: " + command);
            }*/

            System.out.println(" " + command);
            System.out.println("____________________________________________________________");
        }
        scanner.close();
    }
}