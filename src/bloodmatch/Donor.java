package BloodMatch.src.bloodmatch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// class, which manages the donor information.
public class Donor {
    static String line;

    // method, which adds donor's information to data/requests.txt
    public static void addDonor(String id, String name, String email, String city, String bloodType) {
        writeDonor(id, name, email, city, bloodType);
        System.out.println(name + " with id " + id + " has been" +
                "written to out database successfully.");
    }

    // method, which writes information to the database.
    private static void writeDonor(String id, String name, String email, String city, String bloodType) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter("BloodMatch\\data\\donors.txt", true))) {
            if (!idExists(id)) {
                out.write("Id: " + id + "\n");
                out.write("Name: " + name + "\n");
                out.write("Email: " + email + "\n");
                out.write("City: " + city + "\n");
                out.write("BloodType: " + bloodType + "\n");
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
