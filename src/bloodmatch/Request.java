package BloodMatch.src.bloodmatch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Request {
    static String line;

    // method, which adds patients's information to data/requests.txt
    public static void addRequest(String id, String city, String bloodType, int priority) {
        writeRequest(id, city, bloodType, priority);
        System.out.println(id + " has been written to" +
                "our database successfully.");
        return;
    }

    // method, which writes information to the database.
    private static void writeRequest(String id, String city, String bloodType, int priority) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter("BloodMatch\\data\\donors.txt", true))) {
            if (!idExists(id)) {
                out.write("Id: " + id + "\n");
                out.write("City: " + city + "\n");
                out.write("BloodType: " + bloodType + "\n");
                out.write("Priority: " + id);
                out.write("---\n");
                return;
            }
        } catch (IOException exc) {
            System.out.println("It seems, exception has been occurred: " + exc.getStackTrace());
        }

        System.out.println("The donor already exists in our database!");
    }

    // method, which checks if donor's id exists.
    private static boolean idExists(String id) {
        try (BufferedReader in = new BufferedReader(new FileReader("BloodMatch\\data\\donors.txt"))) {

            // read to the end of the file.
            while (((line = in.readLine()) != null)) {
                // start comparring id's if line starts with "Id: ".
                if (line.startsWith("Id: ")) {
                    if (line.substring(4).equals(id)) {
                        return true;
                    }
                }
            }
        } catch (IOException exc) {
            System.out.println("It seems, exception has been occurred: " + exc.getStackTrace());
        }

        return false;
    }
}
