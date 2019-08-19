package miguelsanchezp.ENMA.methods;
import miguelsanchezp.ENMA.resources.Utilities;
import java.util.ArrayList;
import static miguelsanchezp.ENMA.Controller.conf;
import static miguelsanchezp.ENMA.resources.Utilities.*;

public class Vigenere {

    public static String cypher (String message, String key, boolean autokey) {
        String messagePrep = InputPreparation(message);
        String fixedKey;
        if (!autokey) {
            fixedKey = adjustKeyLength (key, messagePrep.length());
        }else{
            fixedKey = adjustKeyLength (key, noSpaces(messagePrep), messagePrep);
        }
        int round = 0;
        StringBuilder cyphered = new StringBuilder();
        for (int i = 0; i<messagePrep.length(); i++) {
            StringBuilder sbM = new StringBuilder();
            StringBuilder sbK = new StringBuilder();
            sbM.append(messagePrep.charAt(i));
            sbK.append(fixedKey.charAt(round));
            if (conf.getAlphabet().contains(sbM) && conf.getAlphabet().contains(sbK)) {
                cyphered.append(conf.getAlphabet().charAt((conf.getAlphabet().indexOf(messagePrep.charAt(i))+conf.getAlphabet().indexOf(fixedKey.charAt(round)))%conf.getAlphabet().length()));
                round++;
            }else{
                cyphered.append(messagePrep.charAt(i));
            }
        }
        return cyphered.toString();
    }

    public static String decypher (String message, String key, boolean autokey) {
        ArrayList<Character> message2 = Utilities.toArray(message);
        String fixedKey;
        StringBuilder decyphered = new StringBuilder();
        if (!autokey) {
            fixedKey = adjustKeyLength(key, message.length());
            int round = 0;
            for (char c : message2) {
                StringBuilder sbM = new StringBuilder();
                sbM.append(c);
                StringBuilder sbK = new StringBuilder();
                sbK.append(fixedKey.charAt(round));
                if (conf.getAlphabet().contains(sbM) && conf.getAlphabet().contains(sbK)) {
                    int pos = conf.getAlphabet().indexOf(c)-conf.getAlphabet().indexOf(fixedKey.charAt(round));
                    if (pos < 0) {
                        pos+=conf.getAlphabet().length();
                    }
                    decyphered.append(conf.getAlphabet().charAt(pos));
                    round++;
                }else{
                    decyphered.append(c);
                }
            }
        }else{
            StringBuilder sbKF = new StringBuilder();
            sbKF.append(key.toUpperCase());
            int round = 0;
            for (char c : message2) {
                StringBuilder sbM = new StringBuilder();
                sbM.append(c);
                StringBuilder sbK = new StringBuilder();
                sbK.append(Character.toUpperCase(sbKF.charAt(round)));
                if (conf.getAlphabet().contains(sbM) && conf.getAlphabet().contains(sbK)) {
                    int pos = conf.getAlphabet().indexOf(c)-conf.getAlphabet().indexOf(sbKF.charAt(round));
                    if (pos < 0) {
                        pos += conf.getAlphabet().length();
                    }
                    decyphered.append(conf.getAlphabet().charAt(pos));
                    sbKF.append(conf.getAlphabet().charAt(pos));
                    round++;
                }else{
                    decyphered.append(c);
                }
            }
        }
        return decyphered.toString();
    }
}