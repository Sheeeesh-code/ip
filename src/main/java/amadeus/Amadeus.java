package amadeus;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class Amadeus {

    public static void main(String[] args) {
        Storage storage = new Storage("./data/amadeus.txt");
        ArrayList<Task> tasks = new ArrayList<>(storage.load());
        Scanner scanner = new Scanner(System.in);

        Ui.showLogo();
        Ui.greet();

        while (true) {
            String command = scanner.nextLine().trim();
            System.out.println();

            switch (command) {
            case Commands.DISCONNECT -> {
                Ui.showShutdown();
                scanner.close();
                return;
            }
            case Commands.ECHO -> runEchoMode(scanner);
            case Commands.DMAIL -> runDmailMode(scanner);
            case Commands.LIST -> runListMode(scanner, tasks, storage);
            default -> System.out.println("⚠️ Unknown command. Try again.");
            }
        }
    }

    private static void runEchoMode(Scanner scanner) {
        System.out.println("The Mad Scientist chose the option Echo");
        System.out.println("Echo mode activated. Type 'Esc' to exit.");
        while (true) {
            String input = scanner.nextLine();
            if ("Esc".equalsIgnoreCase(input)) {
                Ui.showShutdown();
                break;
            }
            Ui.printSeparator();
            System.out.println("You just said : " + input);
            Ui.printSeparator();
        }
    }

    private static void runDmailMode(Scanner scanner) {
        System.out.println("The Mad Scientist chose to send a D-mail");
        System.out.println("D-mail mode activated. Type 'El Psy Kongroo' to exit.");
        while (true) {
            String input = scanner.nextLine();
            if ("El Psy Kongroo".equalsIgnoreCase(input)) {
                Ui.printSeparator();
                System.out.println("The SERN is spying us, we need to disconnect...");
                System.out.println("⚠️ Your position has been compromised. Flee immediately.");
                System.out.println("El Psy Kongroo.");
                System.out.println("World line shift imminent.");
                Ui.printSeparator();
                break;
            }
            Ui.printSeparator();
            System.out.println("📱 D-mail is sending to the past ...");
            System.out.println("⚡ Time transmission in progress ...");
            System.out.println("📧 Message received in world line 1.130205%: " + input);
            Ui.printSeparator();
        }
    }

    private static void runListMode(Scanner scanner, ArrayList<Task> tasks, Storage storage) {
        System.out.println("List mode activated. Type 'Bye' to exit.");
        while (true) {
            String input = scanner.nextLine();
            try {
                if ("Bye".equalsIgnoreCase(input)) {
                    System.out.println("The list printing is finished");
                    break;
                } else if ("list".equalsIgnoreCase(input)) {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                } else if (input.toLowerCase().startsWith("mark")) {
                    int idx = Integer.parseInt(input.split(" ")[1]) - 1;
                    tasks.get(idx).markAsDone();
                    storage.save(tasks);
                    Ui.taskUpdated("Nice! I've marked this task as done:", tasks.get(idx));
                } else if (input.toLowerCase().startsWith("unmark")) {
                    int idx = Integer.parseInt(input.split(" ")[1]) - 1;
                    tasks.get(idx).markAsUndone();
                    storage.save(tasks);
                    Ui.taskUpdated("OK, I've marked this task as not done yet:", tasks.get(idx));
                } else if (input.toLowerCase().startsWith("todo")) {
                    String desc = input.substring(5).trim();
                    if (desc.isEmpty()) throw new AmadeusException("The description of a todo cannot be empty.");
                    tasks.add(new ToDo(desc));
                    storage.save(tasks);
                    Ui.taskAdded(tasks);
                } else if (input.toLowerCase().startsWith("deadline")) {
                    String[] parts = input.substring(9).split("/by");
                    String desc = parts[0].trim();
                    String by = parts.length > 1 ? parts[1].trim() : "unspecified";
                    tasks.add(new Deadline(desc, by));
                    storage.save(tasks);
                    Ui.taskAdded(tasks);
                } else if (input.toLowerCase().startsWith("event")) {
                    String[] parts = input.substring(6).split("/from");
                    String desc = parts[0].trim();
                    String from = "", to = "";
                    if (parts.length > 1) {
                        String[] timeParts = parts[1].split("/to");
                        from = timeParts[0].trim();
                        if (timeParts.length > 1) to = timeParts[1].trim();
                    }
                    tasks.add(new Event(desc, from, to));
                    storage.save(tasks);
                    Ui.taskAdded(tasks);
                } else if (input.toLowerCase().startsWith("delete")) {
                    int idx = Integer.parseInt(input.split(" ")[1]) - 1;
                    Task removed = tasks.remove(idx);
                    Ui.taskDeleted(removed, tasks.size());
                } else {
                    throw new AmadeusException("Sorry, I don't know that command.");
                }
            } catch (AmadeusException | IOException e) {
                Ui.printSeparator();
                System.out.println(e.getMessage());
                Ui.printSeparator();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("⚠️ Invalid task number!");
            }
        }
    }
}
