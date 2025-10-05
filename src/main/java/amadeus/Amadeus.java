package amadeus;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;

public class Amadeus {
    public static void main(String[] args) {
        Storage storage = new Storage("./data/amadeus.txt");
        ArrayList<Task> tasks = new ArrayList<>(storage.load());


        String logo =
                """
                        ╔══════════════════════════════════════════════════════════════╗
                        ║                                                              ║
                        ║  █████╗ ███╗   ███╗ █████╗ ██████╗ ███████╗██╗   ██╗███████╗ ║
                        ║ ██╔══██╗████╗ ████║██╔══██╗██╔══██╗██╔════╝██║   ██║██╔════╝ ║
                        ║ ███████║██╔████╔██║███████║██║  ██║█████╗  ██║   ██║███████╗ ║
                        ║ ██╔══██║██║╚██╔╝██║██╔══██║██║  ██║██╔══╝  ██║   ██║╚════██║ ║
                        ║ ██║  ██║██║ ╚═╝ ██║██║  ██║██████╔╝███████╗╚██████╔╝███████║ ║
                        ║ ╚═╝  ╚═╝╚═╝     ╚═╝╚═╝  ╚═╝╚═════╝ ╚══════╝ ╚═════╝ ╚══════╝ ║
                        ║                                                              ║
                        ╚══════════════════════════════════════════════════════════════╝
                        """;

        System.out.println("Hello from\n" + logo);

        System.out.println("────────────────────────────────────────────────────────────────");
        System.out.println("\uD83D\uDC4B Greetings. I am Amadeus, your cognitive assistant.");
        System.out.println("I monitor divergences in world lines… and sometimes your mistakes.");
        System.out.println("What knowledge do you seek today?");
        System.out.println();
        System.out.println("Here are the different mode : 1. Echo");
        System.out.println("                              2. D-mail");
        System.out.println("                              3. List");
        System.out.print("Write the name of the command you wish use : ");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String answer = scanner.nextLine();
            System.out.println();

            if (answer.equals("Disconnect")) {
                System.out.println("────────────────────────────────────────────────────────────────");
                System.out.println("System shutting down... awaiting next transmission.");
                System.out.println("El Psy Kongroo.");
                System.out.println("────────────────────────────────────────────────────────────────");
                break;
            } else if (answer.equals("Echo")) {
                System.out.println("The Mad Scientist chose the option Echo");
                System.out.println("Echo mode activated. Type 'Esc' to exit.");
                while (true) {
                    String echo = scanner.nextLine();
                    if (echo.equals("Esc")) {
                        System.out.println("────────────────────────────────────────────────────────────────");
                        System.out.println("System shutting down... awaiting next transmission.");
                        System.out.println("El Psy Kongroo.");
                        System.out.println("────────────────────────────────────────────────────────────────");
                        break;
                    } else {
                        System.out.println("────────────────────────────────────────────────────────────────");
                        System.out.println("You just said : " + echo);
                        System.out.println("────────────────────────────────────────────────────────────────");
                    }

                }
            } else if (answer.equals("D-mail")) {
                System.out.println("The Mad Scientist chose to send a D-mail");
                System.out.println("D-mail mode activated. Type 'El Psy Kongroo' to exit.");
                while (true) {
                    String echo = scanner.nextLine();
                    if (echo.equals("El Psy Kongroo")) {
                        System.out.println("────────────────────────────────────────────────────────────────");
                        System.out.println("The SERN is spying us we need to disconnect ...");
                        System.out.println("⚠️ Your position has been compromised. flee immediately.");
                        System.out.println("El Psy Kongroo.");
                        System.out.println("World line shift imminent.");
                        System.out.println("────────────────────────────────────────────────────────────────");
                        break;
                    } else {
                        System.out.println("────────────────────────────────────────────────────────────────");
                        System.out.println("\uD83D\uDCF1 D-mail is sending to the past ...");
                        System.out.println("⚡ Time transmission in progress ...");
                        System.out.println("📧 Message received in world line 1.130205%: " + echo);
                        System.out.println("────────────────────────────────────────────────────────────────");
                    }
                }
            } else if (answer.equals("List")) {
                System.out.println("List mode activated. Type 'Bye' to exit.");
                while (true) {
                    String echo = scanner.nextLine();
                    try {
                        if (echo.equals("Bye")) {
                            System.out.println("The list printing is finished");
                            break;
                        } else if (echo.equals("list")) {
                            for (int i = 0; i < tasks.size(); i++) {
                                System.out.println((i + 1) + ". " + tasks.get(i));
                            }
                        } else if (echo.toLowerCase().startsWith("mark")) {
                            int idx = Integer.parseInt(echo.split(" ")[1]) - 1;
                            tasks.get(idx).markAsDone();
                            storage.save(tasks);
                            System.out.println("────────────────────────────────────────────────────────────────");
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println("  " + tasks.get(idx));
                            System.out.println("────────────────────────────────────────────────────────────────");
                        } else if (echo.toLowerCase().startsWith("unmark")) {
                            int idx = Integer.parseInt(echo.split(" ")[1]) - 1;
                            tasks.get(idx).markAsUndone();
                            storage.save(tasks);
                            System.out.println("────────────────────────────────────────────────────────────────");
                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.println("  " + tasks.get(idx));
                            System.out.println("────────────────────────────────────────────────────────────────");
                        } else if (echo.toLowerCase().startsWith("todo")) {
                            String desc = echo.substring(5).trim();
                            if (desc.isEmpty()) {
                                throw new AmadeusException("The description of a todo cannot be empty.");
                            }
                            tasks.add(tasks.size(), new ToDo(desc));
                            if (desc.isEmpty()) throw new AmadeusException("The description of a todo cannot be empty.");
                            storage.save(tasks);
                            System.out.println("────────────────────────────────────────────────────────────────");
                            System.out.println("Got it. I've added this task:");
                            System.out.println("  " + tasks.get(tasks.size() - 1));
                            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                            System.out.println("────────────────────────────────────────────────────────────────");
                        } else if (echo.toLowerCase().startsWith("deadline")) {
                            String[] parts = echo.substring(9).split("/by");
                            String desc = parts[0].trim();
                            String by = parts.length > 1 ? parts[1].trim() : "unspecified";
                            tasks.add(tasks.size(), new Deadline(desc, by));
                            storage.save(tasks);
                            System.out.println("────────────────────────────────────────────────────────────────");
                            System.out.println("Got it. I've added this task:");
                            System.out.println("  " + tasks.get(tasks.size() - 1));
                            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                            System.out.println("────────────────────────────────────────────────────────────────");
                        } else if (echo.toLowerCase().startsWith("event")) {
                            String[] parts = echo.substring(6).split("/from");
                            String desc = parts[0].trim();
                            String from = "", to = "";
                            if (parts.length > 1) {
                                String[] timeParts = parts[1].split("/to");
                                from = timeParts[0].trim();
                                if (timeParts.length > 1) to = timeParts[1].trim();
                            }
                            tasks.add(tasks.size(), new Event(desc, from, to));
                            storage.save(tasks);
                            System.out.println("────────────────────────────────────────────────────────────────");
                            System.out.println("Got it. I've added this task:");
                            System.out.println("  " + tasks.get(tasks.size() - 1));
                            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                            System.out.println("────────────────────────────────────────────────────────────────");
                        } else if (echo.toLowerCase().startsWith("delete")) {
                            try {
                                int idx = Integer.parseInt(echo.split(" ")[1]) - 1;
                                Task removed = tasks.remove(idx);
                                System.out.println("────────────────────────────────────────────────────────────────");
                                System.out.println("Noted. I've removed this task:");
                                System.out.println("  " + removed);
                                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                                System.out.println("────────────────────────────────────────────────────────────────");
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println("Invalid task number!");
                            }
                        } else {
                            throw new AmadeusException("Sorry, I don't know that command.");
                        }
                    } catch (AmadeusException | IOException e) {
                        System.out.println("────────────────────────────────────────────────────────────────");
                        System.out.println(e.getMessage());
                        System.out.println("────────────────────────────────────────────────────────────────");
                    }
                }
            }
        }
        scanner.close();
    }
}
