package personalFinanceManager.src;

import java.io.*;
import java.util.ArrayList;

public class TransactionReader {
    static int index;
    static String data, date, money;
    static String lineArray;
    static ArrayList<String> lists = new ArrayList<>();
    static ArrayList<String> rangeLists = new ArrayList<>();

    // method, which reads the data from a file.
    public static void readToArray() {
        lists.clear();

        try (BufferedReader in = new BufferedReader(
                new FileReader("personalFinanceManager/data/transactions.txt"))) {
            while ((data = in.readLine()) != null) {
                lists.add(data);
            }
        } catch (IOException exc) {
            System.out.println("Unexpected behavour has happaned in readData method: " + exc);
        }
    }

    // method, which shows the data from given range.
    public static void showRange(String firstMonth, String secondMonth) {
        rewriteArray(firstMonth, secondMonth);
        displayDates(rangeLists);
    }

    // method, which writes the data from given range to array.
    private static void rewriteArray(String firstMonth, String secondMonth) {

        if (monthExits(firstMonth) && monthExits(secondMonth)) {
            int start = getPossition(firstMonth); // get the position of first month
            int end = getPossition(secondMonth) + 1; // get the position of second month

            // examine through lists array in a particular range.
            for (int i = start; i <= end; i++)
                rangeLists.add(lists.get(i));

            return;
        }
    }

    // method, which returns the index of first month.
    private static int getPossition(String month) {
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).startsWith("date: ") && lists.get(i).substring(6).equals(month))
                return i;
        }

        return -1;
    }

    // method, which shows the data from transactions.txt.
    public static void showData() {
        readToArray();
        displayDates(lists);
    }

    // method, which displays the array, given from this class.
    private static void displayDates(ArrayList<String> array) {

        // examine through array.
        for (String list : array) {
            if (list.startsWith("date: ")) {
                date = list.substring(6);
            } else if (list.startsWith("money: ")) {
                money = list.substring(7);
                System.out.println(date + ": $" + money); // because money is located after date, display the
                                                          // information.
            }
        }
    }

    // method, which cheks if particular month is already written in our database.
    private static boolean monthExits(String month) {
        readToArray();

        // examine through array and check if particular date exists in our
        // database(lists array).
        for (String list : lists) {
            if (list.startsWith("date: ") && list.substring(6).equals(month)) {
                return true;
            }
        }

        System.out.println("Invalid date: " + month);
        return false;
    }
}
