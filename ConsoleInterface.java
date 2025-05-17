package personalFinanceManager.src;

import java.util.Scanner;

// a console-based interface.
public class ConsoleInterface {
    int option, money;
    String month, fMonth, sMonth;
    Scanner console = new Scanner(System.in);

    // method, which launches the application.
    public void launchApp() {
        displayInterface();

        option = askOptions();
        doOptions(option);

    }

    // method, which displays the interface of the app.
    private void displayInterface() {
        System.out.println("1. Add transaction");
        System.out.println("2. Show the history of transactions");
        System.out.println("3. Show the spendings");
        System.out.println("4. Exit");
    }

    // method, which asks to choose an option.
    private int askOptions() {
        System.out.print("Choose: ");

        // check, if next input is an integer.
        if (console.hasNextInt()) {
            option = console.nextInt();
            console.nextLine();
            return option;
        }

        System.out.println("Write an integer value!");
        return -1;
    }

    // method, which processes the option, based on choice.
    private void doOptions(int what) {
        System.out.println();

        switch (what) {
            case 1:
                month = askDate();
                money = askMoney();
                TransactionWriter.writeData(month, money);
                break;
            case 2:
                TransactionReader.showData();
                break;
            case 3:
                askMonths();
                TransactionReader.showRange(fMonth, sMonth);
                break;
            case 4:
                System.out.println("Terminating the program.");
                break;
            default:
                System.out.println("Invalid  input option.Please write an integer value (1-4)!");
                break;
        }
    }

    // method, which asks for a month.
    private String askDate() {
        System.out.print("Write a date(eg 02.12.2024): ");
        return console.nextLine();

    }

    // method, which asks for a month.
    private int askMoney() {
        System.out.print("Write a total value spended: ");
        if (console.hasNextInt()) {
            int money = console.nextInt();
            console.nextLine();
            return money;
        }

        return -1;
    }

    // method which asks for a period of time spending.
    private void askMonths() {
        fMonth = askFrirstDate();
        sMonth = askSecondDate();
        return;
    }

    // method, which asks for first information of time spending.
    private String askFrirstDate() {
        System.out.print("Enter the first date: ");
        return console.nextLine();
    }

    // method, which asks for second information of time spending.
    private String askSecondDate() {
        System.out.print("Enter the second date: ");
        return console.nextLine();
    }

}
