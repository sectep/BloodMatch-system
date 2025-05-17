package personalFinanceManager.src;

import java.io.*;

// a class, which writes a file into a database
public class TransactionWriter {
    static String newline = System.getProperty("line.seperator");
    static String line;

    // method, which writes encrypted data into data/transactions.dat
    public static void writeData(String month, int money) {

        // first check, if the month already exists in out database.
        if (!monthExits(month) && month != null && money != -1) {

            // write data into a file.
            try (BufferedWriter out = new BufferedWriter(
                    new FileWriter("personalFinanceManager/data/transactions.txt", true))) {
                out.write("date: " + month + "\n");
                out.write("money: " + money + "\n");
                return;
            } catch (IOException exc) {
                System.out.println("Unexpected behavour has happaned in writeData method: " + exc);
            }
        }

        System.out.println(month + " is already written in our database.");
    }

    // method, which cheks if particular month is already written in our database.
    private static boolean monthExits(String month) {
        try (BufferedReader in = new BufferedReader(
                new FileReader("personalFinanceManager/data/transactions.txt"))) {

            // read until the end.
            while ((line = in.readLine()) != null) {
                if (line.startsWith("date: ") && line.substring(6).equals(month)) {
                    return true;
                }
            }
        } catch (IOException exc) {
            System.out.println("Unexpected behavour has happaned in monthExists method: " + exc);
        }
        return false;
    }
}
