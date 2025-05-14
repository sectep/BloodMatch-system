package BloodMatch.src.bloodmatch;

import static BloodMatch.src.bloodmatch.Patient.*;
import static BloodMatch.src.bloodmatch.Donor.*;
import java.util.Scanner;

// a class, which manages the console input data.
public class ConsoleInterface {
    String id, name, email, city, bloodType, donorId;
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
            System.out.println("Invalid input type!Write an integer.");
            return;
        }

        System.out.println();

        // do specific task.
        switch (what) {
            case 1:
                id = askId();
                name = askName();
                email = askEmail();
                city = askCity();
                bloodType = askBloodType();
                addDonor(id, name, email, city, bloodType);
                break;
            case 2:
                id = askId();
                name = askName();
                city = askCity();
                bloodType = askBloodType();
                priority = askPriority();
                addPatient(id, name, city, bloodType, priority);
                break;
            case 3:
                DataManager.showDonors();
                break;
            case 4:
                DataManager.showPatients();
                break;
            case 5:
                System.out.println("To quit the buffer, enter 'exit'");
                do {
                    id = askId();
                    if (id.equalsIgnoreCase("exit"))
                        break;

                    // if patient's criteria matches with donor's, create a queue.
                    if (matchWithDonor(id))
                        getAndCreate(id);
                } while (!(id.equalsIgnoreCase(bloodType)));

                Scheduler.manageFLow();
                removePatient(id);
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
        System.out.println("2. Add patient");
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

    // method, which gets priority and creates a thread for patient.
    private void getAndCreate(String id) {
        priority = Patient.getUrgency(id);
        donorId = Patient.matchForId(id);

        // create if thread's priority is valid.
        if (priority != -1) {
            PatientThread.createThread(id, donorId, priority);
            return;
        }

        System.out.println("Priority isn't valid.");
        return;

    }
}
