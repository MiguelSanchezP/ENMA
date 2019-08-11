package miguelsanchezp.ENMA;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import miguelsanchezp.ENMA.methods.*;

import java.util.ArrayList;

public class Controller {
    @FXML
    private RadioMenuItem RMIROT, RMICaesar, RMIVigenere, RMITransposition;
    @FXML
    private Menu MMethods;
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

    private ArrayList<String> CypheringIds = new ArrayList<>();
    private String CurrentlySelectedID;
    private String CurrentSpaceTreatment;

    public void initialize () {
        CypheringIds.add(RMIROT.getId());
        CypheringIds.add(RMICaesar.getId());
        CypheringIds.add(RMIVigenere.getId());
        CypheringIds.add(RMITransposition.getId());
        RMIROT.setSelected(true);
        CBSpaces.setValue("Keep");
        CurrentSpaceTreatment=CBSpaces.getValue();
        RBAutoKey.setSelected(true);
        handleROT();
    }

    private void handleMethodRMI (String id) {
        CurrentlySelectedID=id;
        for (int i= 0; i<CypheringIds.size(); i++) {
            if (!MMethods.getItems().get(i).getId().equals(id)) {
                RadioMenuItem rmi = (RadioMenuItem) MMethods.getItems().get(i);
                rmi.setSelected(false);
            }
        }
    }

    @FXML
    private void handleROT () {
        handleMethodRMI(RMIROT.getId());
        numberRequired(true);
        keyRequired(false);
    }

    @FXML
    private void handleCaesar() {
        handleMethodRMI(RMICaesar.getId());
        numberRequired(false);
        keyRequired(false);
    }

    @FXML
    private void handleVigenere () {
        handleMethodRMI(RMIVigenere.getId());
        numberRequired(false);
        keyRequired(true);
    }
    @FXML
    private void handleTransposition () {
        handleMethodRMI(RMITransposition.getId());
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
        if (method.equals("Transposition")) {
            cypherTransposition();
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
    }

    @FXML
    private void handleCBSpaces () {
        if (CBSpaces.getValue()!=null) {
            CurrentSpaceTreatment = CBSpaces.getValue();
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
                TAOutput.setText(ROT.cypher(TAInput.getText(), (int) SNumber.getValue(), CurrentSpaceTreatment));
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
                TAOutput.setText(ROT.cypher(TAInput.getText(), 3, CurrentSpaceTreatment));
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
            TAOutput.setText(Vigenere.cypher(TAInput.getText(), TFKey.getText(), RBAutoKey.isSelected(), CurrentSpaceTreatment));
        }
    }

    private void decypherVigenere () {
        if (revised("alphabetical")) {
            TAOutput.setText(Vigenere.decypher(TAInput.getText(), TFKey.getText(), RBAutoKey.isSelected()));
        }
    }

    private void cypherTransposition () {
        if (revised("numerical")) {
//            System.out.println("hey there :)");
            TAOutput.setText(Transposition.cypher(TAInput.getText(), TFKey.getText()));
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
}
