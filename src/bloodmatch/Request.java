package BloodMatch.src.bloodmatch;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Request {
    static List<String> list = new ArrayList<>();
    static String line, blood, city;

    // method, which adds patients's information to data/requests.txt
    public static void addRequest(String id, String city, String bloodType, int priority) {
        writeRequest(id, city, bloodType, priority);
        System.out.println(id + " has been written to " +
                "our database successfully.");
        return;
    }

    // method, which reads the file to array.
    public static void readToArray() {
        list.clear();

        try (BufferedReader in = new BufferedReader(new FileReader("data/requests.txt"))) {
            while ((line = in.readLine()) != null) {
                list.add(line + "\n");
            }
        } catch (IOException exc) {
            System.out.println("It seems that exception has been ocurred: " + exc.getStackTrace());
        }
    }

    // method, which matches the patiens id and city.
    public static void matchBlood(String id) {
        // check, if patient exists in out database.
        if (idExists(id)) {
            blood = getBlood(id);
            city = getCity(id);
            Donor.matchBlood(id, blood, city);
            return;
        }
        System.out.println(id + " doesn't exists in out database.");
    }

    // method, which returns the city of the patient.
    private static String getCity(String id) {
        readToArray();

        // iterate through arraylist.
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).startsWith("Id: ") && list.get(i).substring(4).equals(id)) {
                return list.get(i++).substring(6);
            }
        }
        return null;
    }

    // method, which returns the bloodType of the patient.
    private static String getBlood(String id) {
        readToArray();

        // iterate through arraylist.
        for (int i = 0; i < list.size(); i++) {

            // check, if array's id matches the patient's
            if (list.get(i).startsWith("Id: ") && list.get(i).substring(4).equals(id)) {
                return list.get(i + 2).substring(11); // return formater bloodtype.
            }
        }
        return null;
    }

    // method, which writes information to the database.
    private static void writeRequest(String id, String city, String bloodType, int priority) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter("data/requests.txt", true))) {
            if (!idExists(id)) {
                out.write("Id: " + id + "\n");
                out.write("City: " + city + "\n");
                out.write("BloodType: " + bloodType + "\n");
                out.write("Priority: " + priority + "\n");
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
        try (BufferedReader in = new BufferedReader(new FileReader("BloodMatch\\data\\requests.txt"))) {

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
