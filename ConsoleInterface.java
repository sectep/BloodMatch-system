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

        // do specific task.
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
                name = askName();
                city = askCity();
                bloodType = askBloodType();
                priority = askPriority();
                Patient.addPatient(id, name, city, bloodType, priority);
                break;
            case 3:
                DataManager.showDonors();
                break;
            case 4:
                DataManager.showPatients();
                break;
            case 5:
                id = askId();
                if (Patient.matchWithDonor(id)) {
                    priority = Patient.getUrgency(id);
                    PatientThread.createThread(id, priority);
                    return;
                }
                System.out.println("No matches found in " + id);
                break;
            case 6:
                System.out.println("Terminating the program.");
                break;
            default:
                System.out.println("We only have options from 1 to 6.");
                break;
        }
    }

    // method, which dispkays the interface.
    private void display() {
        System.out.println("===BLOOD MATCH SYSTEM===");
        System.out.println("1. Add donor");
        System.out.println("2. Add request");
        System.out.println("3. Show all donors");
        System.out.println("4. Show all requests");
        System.out.println("5. Match donors with requests");
        System.out.println("6. Exit");
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
        System.out.print("Enter id: ");
        return console.nextLine();
    }

    // method, which asks for name.
    private String askName() {
        System.out.print("Enter name: ");
        return console.nextLine();
    }

    // method, which asks for email.
    private String askEmail() {
        System.out.print("Enter email: ");
        return console.nextLine();
    }

    // method, which asks for city.
    private String askCity() {
        System.out.print("Enter city: ");
        return console.nextLine();
    }

    // method, which asks for priority.
    private int askPriority() {
        System.out.print("Enter urgency(1-10): ");
        if (console.hasNextInt()) {
            priority = console.nextInt();
            console.nextLine();
            return priority;
        }
        return -1;
    }

    // method, which asks for blood typE.
    private String askBloodType() {
        System.out.print("Enter blood type: ");

        bloodType = console.nextLine();
        return BloodType.parseType(bloodType);
    }
}
