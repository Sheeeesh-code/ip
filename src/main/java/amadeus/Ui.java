package amadeus;

public class Ui {
    public static void showLogo() {
        String logo = """
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
    }

    public static void greet() {
        printSeparator();
        System.out.println("👋 Greetings. I am Amadeus, your cognitive assistant.");
        System.out.println("I monitor divergences in world lines… and sometimes your mistakes.");
        System.out.println("What knowledge do you seek today?");
        System.out.println();
        System.out.println("Here are the different modes : 1. Echo");
        System.out.println("                               2. D-mail");
        System.out.println("                               3. List");
        System.out.print("Write the name of the command you wish to use : ");
    }

    public static void printSeparator() {
        System.out.println("────────────────────────────────────────────────────────────────");
    }

    public static void showShutdown() {
        printSeparator();
        System.out.println("System shutting down... awaiting next transmission.");
        System.out.println("El Psy Kongroo.");
        printSeparator();
    }

    public static void taskUpdated(String msg, Task task) {
        printSeparator();
        System.out.println(msg);
        System.out.println("  " + task);
        printSeparator();
    }

    public static void taskAdded(java.util.List<Task> tasks) {
        printSeparator();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        printSeparator();
    }

    public static void taskDeleted(Task removed, int newSize) {
        printSeparator();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + removed);
        System.out.println("Now you have " + newSize + " tasks in the list.");
        printSeparator();
    }
}
