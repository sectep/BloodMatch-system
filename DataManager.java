package BloodMatch.src.bloodmatch;

// a class, which manipulates with donor's and patient's database
public class DataManager {

    // method, which shows all donors in our database.
    public static void showDonors() {
        Donor.readToArray();
        System.out.println("===Donors===");
        for (String l : Donor.list) {
            System.out.println(l);
        }
    }

    // method, which shows all patients in our database.
    public static void showPatients() {
        Patient.readToArray();
        System.out.println("===Patients===");
        for (String l : Patient.list) {
            System.out.println(l);
        }
    }

}
