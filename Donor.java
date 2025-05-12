package BloodMatch.src.bloodmatch;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// class, which manages the donor information.
public class Donor {
    static List<String> list = new ArrayList<>();
    static String line, dID, dBlood, dCity;

    // method, which adds donor's information to data/requests.txt
    public static void addDonor(String id, String name, String email, String city, String bloodType) {
        writeDonor(id, name, email, city, bloodType);
        System.out.println(name + " with id " + id + " has been" +
                " written to out database successfully.");
    }

    // method, which find the suitable donor for request.
    public static boolean matchBlood(String id, String blood, String city) {
        readToArray();

        // iterate through list.
        for (int i = 0; i < list.size(); i++) {
            // check, if
            if (list.get(i).startsWith("Id: ")) {
                dID = list.get(i).substring(4);

                // information of the donor.
                dBlood = list.get(i + 4).substring(11);
                dCity = list.get(i + 3).substring(6);

                if (dBlood.equalsIgnoreCase(blood) && dCity.equalsIgnoreCase(city)) {
                    System.out.println(dID + " is suitable for " + id +
                            " with " + dBlood);
                    return true;
                }
            }
        }
        return false;
    }

    // method, which reads the file to array.
    public static void readToArray() {
        list.clear();

        try (BufferedReader in = new BufferedReader(new FileReader("BloodMatch\\data\\donors.txt"))) {
            while ((line = in.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException exc) {
            System.out.println("It seems that exception has been ocurred: " + exc);
        }
    }

    // method, which removes donor from the database.
    public static void removeDonor(String id) {
        if (idExists(id)) {
            // iterate through list.
            for (int i = 0; i < list.size(); i++) {

                if (list.get(i).startsWith("Id: ") && list.get(i).equals(id)) {
                    for (int j = 0; j < 6; j++) {
                        list.remove(i);
                    }
                }
            }

        }
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
            System.out.println("It seems, exception has been occurred: " + exc);
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
            System.out.println("It seems, exception has been occurred: " + exc);
        }

        return false;
    }
}
