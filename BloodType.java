package BloodMatch.src.bloodmatch;

enum BloodType {
    A_POS, A_NEG, B_POS, B_NEG, AB_POS, AB_NEG, O_POS, O_NEG;

    private static String name;

    // method, which returns specific blood type.
    public static String parseType(String type) {
        if (typeExists(type)) {
            return type.toUpperCase();
        }
        return "UNDEFINED";
    }

    // method, which checks if blood type mathches in enuminator.
    public static boolean typeExists(String type) {
        for (BloodType bloodType : BloodType.values()) {
            name = bloodType.toString();
            if (name.equalsIgnoreCase(type)) {
                return true;
            }
        }

        return false;
    }
}
