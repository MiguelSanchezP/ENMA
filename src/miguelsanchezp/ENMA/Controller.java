package miguelsanchezp.ENMA;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import miguelsanchezp.ENMA.definitions.Configuration;
import miguelsanchezp.ENMA.methods.*;

public class Controller {
    @FXML
    private RadioMenuItem RMIROT, RMICaesar, RMIVigenere, RMIHorizontalTransposition, RMIVerticalTransposition;
    @FXML
    private RadioMenuItem RMI5Block, RMIPlain;
    @FXML
    private RadioMenuItem RMIUppercase, RMILowercase;
    @FXML
    private Menu MMethods, MMessage, MTransposition, MSize;
    @FXML
    private TextArea TAInput, TAOutput;
    @FXML
    private Spinner SNumber;
    @FXML
    private Label LNumber, LKey, LInformation;
    @FXML
    private ComboBox<String> CBSpaces;
    @FXML
    private TextField TFKey;
    @FXML
    private RadioButton RBAutoKey;

    private String CurrentlySelectedID;

    public static Configuration conf = new Configuration();

    public void initialize () {
        RMIROT.setSelected(true);
        RMIPlain.setSelected(true);
        RMIUppercase.setSelected(true);
        CBSpaces.setValue("Keep");
        conf.setSpacesTreatment(CBSpaces.getValue());
        RBAutoKey.setSelected(true);
        handleROT();
        handleRMIPlain();
        handleRMIUppercase();
    }

    private void handleMethodRMI (String id, Menu menu) {
        for (int i= 0; i<menu.getItems().size(); i++) {
            if (!menu.getItems().get(i).getId().equals(id)) {
                if (menu.getItems().get(i).getId().startsWith("RMI")) {
                    RadioMenuItem rmi = (RadioMenuItem) menu.getItems().get(i);
                    rmi.setSelected(false);
                }else{
                    clearMenu (menu, i);
                }
            }
        }
    }

    @FXML
    public void handleRMI5Block () {
        handleMethodRMI(RMI5Block.getId(), MMessage);
        conf.setOutputDisplay("5Block");
    }

    @FXML
    public void handleRMIPlain () {
        handleMethodRMI(RMIPlain.getId(), MMessage);
        conf.setOutputDisplay("Plain");
    }

    @FXML
    public void handleRMIUppercase () {
        handleMethodRMI(RMIUppercase.getId(), MSize);
        conf.setOutputSize("Uppercase");
    }

    @FXML
    public void handleRMILowercase () {
        handleMethodRMI(RMILowercase.getId(), MSize);
        conf.setOutputSize("Lowercase");
    }

    @FXML
    private void handleROT () {
        conf.setMethod("ROT");
        handleMethodRMI(RMIROT.getId(), MMethods);
        numberRequired(true);
        keyRequired(false);
    }

    @FXML
    private void handleCaesar() {
        conf.setMethod("Caesar");
        handleMethodRMI(RMICaesar.getId(), MMethods);
        numberRequired(false);
        keyRequired(false);
    }

    @FXML
    private void handleVigenere () {
        conf.setMethod("Vigenere");
        handleMethodRMI(RMIVigenere.getId(), MMethods);
        numberRequired(false);
        keyRequired(true);
    }
    @FXML
    private void handleHorizontalTransposition () {
        conf.setMethod("HorizontalTransposition");
        handleMethodRMI(RMIHorizontalTransposition.getId(), MTransposition);
        selectedMenu(MTransposition, MMethods);
        numberRequired(false);
        keyRequired(true);
        RBAutoKey.setDisable(true);
    }

    @FXML
    private void handleVerticalTransposition () {
        conf.setMethod("VerticalTransposition");
        handleMethodRMI(RMIVerticalTransposition.getId(), MTransposition);
        selectedMenu(MTransposition, MMethods);
        numberRequired(false);
        keyRequired(true);
        RBAutoKey.setDisable(true);
    }

    @FXML
    private void handleButton () {
        String method = getMethod();
        if (method.equals("ROT")) {
            cypherROT();
        }
        if (method.equals("Caesar")){
            cypherCaesar();
        }
        if (method.equals("Vigenere")) {
            cypherVigenere();
        }
        if (method.equals("HorizontalTransposition")) {
            cypherHorizontalTransposition();
        }
        if (method.equals("VerticalTransposition")) {
            cypherVerticalTransposition();
        }
    }

    @FXML
    private void handleButtonD () {
         String method = getMethod();
         if (method.equals("ROT")) {
             decypherROT();
         }
         if (method.equals("Caesar")) {
             decypherCaesar();
         }
         if (method.equals("Vigenere")) {
             decypherVigenere();
         }
         if (method.equals("HorizontalTransposition")) {
             decypherHorizontalTransposition();
         }
         if (method.equals("VerticalTransposition")) {
             decypherVerticalTransposition();
         }
    }

    @FXML
    private void handleCBSpaces () {
        if (CBSpaces.getValue()!=null) {
            conf.setSpacesTreatment(CBSpaces.getValue());
        }
    }

    private void selectedMenu (Menu children, Menu parent) {
        for (int i = 0; i<parent.getItems().size(); i++) {
            if (parent.getItems().get(i).getId().startsWith("RMI")) {
                RadioMenuItem rmi = (RadioMenuItem) parent.getItems().get(i);
                rmi.setSelected(false);
            }else{
                clearMenu (parent, i);
            }
        }
        String s = "✓ " + children.getText();
        children.setText(s);
    }

    private void clearMenu (Menu menu, int i) {
        if (menu.getItems().get(i).getText().startsWith("✓ ")) {
            StringBuilder sb = new StringBuilder();
            sb.append(menu.getItems().get(i).getText());
            sb.delete(0, 1);
            menu.getItems().get(i).setText(sb.toString());
        }

    }

    private void numberRequired (boolean b) {
        SNumber.setDisable(!b);
        LNumber.setDisable(!b);
    }

    private void keyRequired (boolean b) {
        LKey.setDisable(!b);
        TFKey.setDisable(!b);
        RBAutoKey.setDisable(!b);
    }

    private void cypherROT () {
        if (TAInput.getText() != null) {
            if (TAInput.getText().isEmpty()) {
                notifyError("emptyInput", 0);
            }else {
                TAOutput.setText(formatOutput(ROT.cypher(TAInput.getText(), (int) SNumber.getValue())));
                notifyError("complete", 1);
            }
        }else{
            notifyError("nullInput", 0);
        }
    }

    private void decypherROT () {
        if (TAInput.getText()!=null) {
            if (TAInput.getText().isEmpty()) {
                notifyError("emptyInput", 0);
            }else {
                TAOutput.setText(ROT.decypher(TAInput.getText(), (int) SNumber.getValue()));
                notifyError("complete", 1);
            }
        }else{
            notifyError("nullInput", 0);
        }
    }

    private void cypherCaesar() {
        if (TAInput.getText() != null) {
            if (TAInput.getText().isEmpty()){
                notifyError("emptyInput", 0);
            }else {
                TAOutput.setText(formatOutput(ROT.cypher(TAInput.getText(), 3)));
                notifyError("complete", 1);
            }
        }else{
            notifyError("nullInput", 0);
        }
    }

    private void decypherCaesar() {
        if (TAInput.getText() != null) {
            if (TAInput.getText().isEmpty()) {
                notifyError("emptyInput", 0);
            }else {
                TAOutput.setText(ROT.decypher(TAInput.getText(), 3));
                notifyError("complete", 1);
            }
        }else{
            notifyError("nullInput", 0);
        }
    }

    private void cypherVigenere () {
        if (revised("alphabetical")) {
            TAOutput.setText(formatOutput(Vigenere.cypher(TAInput.getText(), TFKey.getText(), RBAutoKey.isSelected())));
        }
    }

    private void decypherVigenere () {
        if (revised("alphabetical")) {
            TAOutput.setText(Vigenere.decypher(TAInput.getText(), TFKey.getText(), RBAutoKey.isSelected()));
        }
    }

    private void cypherHorizontalTransposition() {
        if (revised("numerical")) {
            TAOutput.setText(formatOutput(Transposition.cypherHorizontal(TAInput.getText(), TFKey.getText())));
        }
    }

    private void decypherHorizontalTransposition () {
        if (revised("numerical")) {
            TAOutput.setText(Transposition.decypherHorizontal(TAInput.getText(), TFKey.getText()));
        }
    }

    private void cypherVerticalTransposition () {
        if (revised("numerical")) {
            TAOutput.setText(formatOutput(Transposition.cypherVertical(TAInput.getText(), TFKey.getText())));
        }
    }

    private void decypherVerticalTransposition () {
        if (revised("numerical")) {
            System.out.println("Acceso");
            TAOutput.setText(Transposition.decypherVertical(TAInput.getText(), TFKey.getText()));
        }
    }

    private String getMethod () {
        String s = CurrentlySelectedID;
        StringBuilder sb = new StringBuilder ();
        for (int i = 3; i<s.length(); i++) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    @FXML
    private void notifyError (String s, int a) {
        if (a == 0) {
            LInformation.setStyle("-fx-text-fill: red; -fx-font-weight: bold");
        }else{
            LInformation.setStyle("-fx-text-fill: green; -fx-font-weight: bold");
        }
        switch (s) {
            case "notAlphabeticalKey":
                LInformation.setText("THE KEY IS NOT ALPHABETICALLY PURE");
                break;
            case "nullKey":
                LInformation.setText("THE KEY IS NULL");
                break;
            case "nullInput":
                LInformation.setText("THE INPUT IS NULL");
                break;
            case "emptyKey":
                LInformation.setText("THE KEY IS EMPTY");
                break;
            case "emptyInput":
                LInformation.setText("THE INPUT IS EMPTY");
                break;
            case "complete":
                LInformation.setText("THE INPUT HAS BEEN SUCCESSFULLY CYPHERED :)");
                break;
            case "notNumericalKey":
                LInformation.setText("THE KEY IS NOT NUMERICALLY PURE");
                break;
            default:
                LInformation.setStyle("-fx-text-fill: red");
                LInformation.setText("THE ERROR WAS UNHANDLED, PLEASE REPORT");
                break;
        }
    }

    private boolean isAlphabetic(String s) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i<s.length(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(Character.toUpperCase(s.charAt(i)));
            if (!alphabet.contains(sb)) {
                return false;
            }
        }
        return true;
    }

    private boolean isNumeric (String s) {
        String nums = "0123456789";
        for (int i = 0; i<s.length(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(s.charAt(i));
            if (!nums.contains(sb)) {
                return false;
            }
        }
        return true;
    }

    private boolean revised (String s) {
        if (TAInput.getText() != null) {
            if(!TAInput.getText().isEmpty()) {
                if (TFKey.getText()!=null) {
                    if (!TFKey.getText().isEmpty()) {
                        if (s.equals("alphabetical")) {
                            if (isAlphabetic(TFKey.getText())) {
                                notifyError("complete", 1);
                                return true;
                            } else {
                                notifyError("notAlphabeticalKey", 0);
                            }
                        }
                        if (s.equals("numerical")) {
                            if (isNumeric(TFKey.getText())) {
                                notifyError ("complete", 1);
                                return true;
                            }else{
                                notifyError("notNumericalKey", 0);
                            }
                        }
                    } else {
                        notifyError("emptyKey", 0);
                    }
                }else{
                    notifyError("nullKey", 0);
                }
            }else{
                notifyError("emptyInput", 0);
            }
        }else{
            notifyError("nullInput", 0);
        }
        return false;
    }

    private String formatOutput (String s) {
        StringBuilder sb = new StringBuilder();
        switch (conf.getOutputBlocks()) {
            case "Plain":
                sb.append(s);
                break;
            case "5Block":
                for (int i = 1; i<=s.length(); i++) {
                    sb.append(s.charAt(i-1));
                    if (i%5 == 0) {
                        sb.append(' ');
                    }
                }
                break;
        }
        switch (conf.getOutputSize()) {
            case "Lowercase":
                return sb.toString().toLowerCase();
            case "Uppercase":
                return sb.toString().toUpperCase();
        }
        return s;
    }
}
