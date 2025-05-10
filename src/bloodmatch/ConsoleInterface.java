package BloodMatch.src.bloodmatch;

import java.util.Scanner;

public class ConsoleInterface {
    String id, name, email, city, bloodType;
    int priority, option;
    Scanner console = new Scanner(System.in);

    // method, which launches the application.
    public void launch() {
        display();
        option = askOption();
        doOption(option);
    }

    // method, which interracts with application, based on the choosen option.
    private void doOption(int what) {
        if (what == -1) {
            System.out.println("Invalid type!");
            return;
        }

        // do specific task
        switch (what) {
            case 1:
                id = askId();
                name = askName();
                email = askEmail();
                city = askCity();
                bloodType = askBloodType();
                Donor.addDonor(id, name, email, city, bloodType);
                break;
            case 2:
                id = askId();
                city = askCity();
                bloodType = askBloodType();
                priority = askPriority();
                Request.addRequest(id, city, bloodType, priority);
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                System.out.println("Terminating the program.");
                break;

            default:
                break;
        }
    }

    // method, which dispkays the interface.
    private void display() {
        System.out.println("===DONOR MATCH SYSTEM===");
        System.out.println("1. Add donor");
        System.out.println("2. Add request");
        System.out.println("3. Match patients");
        System.out.println("4. Show database");
        System.out.println("5. Exit");
    }

    // method, which asks for option.
    private int askOption() {
        System.out.print("Choose: ");

        if (console.hasNextInt()) {
            option = console.nextInt();
            console.nextLine();
            return option;
        }
        return -1;
    }

    // method, which asks for id.
    private String askId() {
        System.out.print("Write id: ");
        return console.nextLine();
    }

    // method, which asks for name.
    private String askName() {
        System.out.print("Write name: ");
        return console.nextLine();
    }

    // method, which asks for email.
    private String askEmail() {
        System.out.print("Write email: ");
        return console.nextLine();
    }

    // method, which asks for city.
    private String askCity() {
        System.out.print("Write city: ");
        return console.nextLine();
    }

    // method, which asks for priority.
    private int askPriority() {
        System.out.print("Write priority(1-10): ");
        if (console.hasNextInt()) {
            priority = console.nextInt();
            console.nextLine();
            return priority;
        }
        return -1;
    }

    // method, which asks for blood typE.
    private String askBloodType() {
        System.out.print("Write blood type: ");

        bloodType = console.nextLine();
        return BloodType.parseType(bloodType);
    }
}
