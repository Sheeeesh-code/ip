package amadeus;
import java.util.Scanner;


public class Amadeus {
    public static void main(String[] args) {
        String logo =
                "╔══════════════════════════════════════════════════════════════╗\n"
                        + "║                                                              ║\n"
                        + "║  █████╗ ███╗   ███╗ █████╗ ██████╗ ███████╗██╗   ██╗███████╗ ║\n"
                        + "║ ██╔══██╗████╗ ████║██╔══██╗██╔══██╗██╔════╝██║   ██║██╔════╝ ║\n"
                        + "║ ███████║██╔████╔██║███████║██║  ██║█████╗  ██║   ██║███████╗ ║\n"
                        + "║ ██╔══██║██║╚██╔╝██║██╔══██║██║  ██║██╔══╝  ██║   ██║╚════██║ ║\n"
                        + "║ ██║  ██║██║ ╚═╝ ██║██║  ██║██████╔╝███████╗╚██████╔╝███████║ ║\n"
                        + "║ ╚═╝  ╚═╝╚═╝     ╚═╝╚═╝  ╚═╝╚═════╝ ╚══════╝ ╚═════╝ ╚══════╝ ║\n"
                        + "║                                                              ║\n"
                        + "╚══════════════════════════════════════════════════════════════╝\n";


        System.out.println("Hello from\n" + logo);

        System.out.println("────────────────────────────────────────────────────────────────");
        System.out.println("\uD83D\uDC4B Greetings. I am Amadeus, your cognitive assistant.");
        System.out.println("I monitor divergences in world lines… and sometimes your mistakes.");
        System.out.println("What knowledge do you seek today?");
        System.out.println();
        System.out.println("Here are the different mode : 1. echo");
        System.out.println("                              2. D-mail");
        System.out.print("Write the name of the command you wish use : ");

        Scanner scanner = new Scanner(System.in);
        while(true){
            String answer = scanner.nextLine();
            System.out.println();
            if(answer.equals("bye")){
                System.out.println("────────────────────────────────────────────────────────────────");
                System.out.println("System shutting down... awaiting next transmission.");
                System.out.println("El Psy Kongroo.");
                System.out.println("────────────────────────────────────────────────────────────────");
                break;
            }
            else if(answer.equals("echo")){
                System.out.println("The Mad Scientist chose the option Echo");
                while(true) {
                    String echo = scanner.nextLine();
                    if (echo.equals("esc")) {
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

            }
            else if (answer.equals("D-mail")) {
                System.out.println("The Mad Scientist chose to send a D-mail");
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
                        System.out.println("📧 Message received in world line 1.130205%: "+echo);
                        System.out.println("────────────────────────────────────────────────────────────────");
                    }
                }
            }
        }
        scanner.close();
    }
}