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

    public void setSpacesTreatment(String spacesTreatment1) {
        spacesTreatment = spacesTreatment1;
    }

    public String getSpacesTreatment () {
        return spacesTreatment;
    }

    public String getAlphabet () {
        return alphabet;
    }

    public String getOutputDisplay () {
        return outputDisplay;
    }

    public void setOutputDisplay (String s) {
        outputDisplay = s;
    }
}
