package miguelsanchezp.ENMA;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import miguelsanchezp.ENMA.methods.*;

import java.util.ArrayList;

public class Controller {
    @FXML
    private RadioMenuItem RMIROT, RMICaesar, RMIVigenere;
    @FXML
    private Menu MMethods;
    @FXML
    private TextArea TAInput, TAOutput;
    @FXML
    private Spinner SNumber;
    @FXML
    private Label LNumber, LKey;
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
            TAOutput.setText(ROT.cypher(TAInput.getText(), (int)SNumber.getValue(), CurrentSpaceTreatment));
        }else{
            TAOutput.setText("The entered message was a null");
        }
    }

    private void decypherROT () {
        if (TAInput.getText()!=null) {
            TAOutput.setText(ROT.decypher(TAInput.getText(), (int) SNumber.getValue()));
        }else{
            TAOutput.setText("The entered message was a null");
        }
    }

    private void cypherCaesar() {
        if (TAInput.getText() != null) {
            TAOutput.setText(ROT.cypher(TAInput.getText(), 3, CurrentSpaceTreatment));
        }else{
            TAOutput.setText("THe entered message was a null");
        }
    }

    private void decypherCaesar() {
        if (TAInput.getText() != null) {
            TAOutput.setText(ROT.decypher(TAInput.getText(), 3));
        }else{
            TAOutput.setText("The entered message was a null");
        }
    }

    private void cypherVigenere () {
        if ((TAInput.getText() != null) && (TFKey.getText()!=null)) {
            TAOutput.setText(Vigenere.cypher(TAInput.getText(), TFKey.getText(), RBAutoKey.isSelected(), CurrentSpaceTreatment));
        }else{
            TAOutput.setText("Either the message or the key was as null");
        }
    }

    private void decypherVigenere () {
        if ((TAInput.getText()!=null) && (TFKey.getText()!=null)) {
            TAOutput.setText(Vigenere.decypher(TAInput.getText(), TFKey.getText(), RBAutoKey.isSelected()));
        }else{
            TAOutput.setText("Either the message or the key was a null");
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
}
