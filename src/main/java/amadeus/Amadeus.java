package amadeus;

import java.util.Scanner;
import java.io.IOException;

public class Amadeus {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Amadeus(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.showLogo();
        ui.greet();

        while (true) {
            String command = scanner.nextLine().trim();
            System.out.println();

            if (command.equalsIgnoreCase(Commands.DISCONNECT)) {
                ui.showShutdown();
                break;
            }

            try {
                Parser.parse(command, scanner, taskList, storage, ui);
            } catch (AmadeusException | IOException e) {
                ui.printSeparator();
                System.out.println(e.getMessage());
                ui.printSeparator();
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        new Amadeus("./data/amadeus.txt").run();
    }
}
