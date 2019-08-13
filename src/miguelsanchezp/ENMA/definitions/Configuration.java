package miguelsanchezp.ENMA.definitions;

public class Configuration {
    private static String spacesTreatment;
    private static String outputDisplay;
    private static String alphabet;

    public Configuration () {
        spacesTreatment="Keep";
        outputDisplay="Plain";
        alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    }

    public static void setSpacesTreatment(String spacesTreatment1) {
        spacesTreatment = spacesTreatment1;
    }

    public static String getSpacesTreatment () {
        return spacesTreatment;
    }

    public static String getAlphabet () {
        return alphabet;
    }
}
