package miguelsanchezp.ENMA.definitions;

public class Configuration {
    private static String spacesTreatment;
    private static String outputBlocks;
    private static String alphabet;
    private static String outputSize;
    private static String method;

    public Configuration () {
        spacesTreatment="Keep";
        outputBlocks ="Plain";
        alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        outputSize="Uppercase";
        method="ROT";
    }

    public void setMethod (String s) {
        method = s;
    }

    public String getMethod () {
        return method;
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

    public String getOutputBlocks() {
        return outputBlocks;
    }

    public void setOutputDisplay (String s) {
        outputBlocks = s;
    }

    public void setOutputSize (String s) {
        outputSize = s;
    }

    public String getOutputSize () {
        return outputSize;
    }
}
