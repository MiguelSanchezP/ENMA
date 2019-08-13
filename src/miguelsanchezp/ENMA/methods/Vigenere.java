package miguelsanchezp.ENMA.methods;

import miguelsanchezp.ENMA.definitions.Configuration;
import miguelsanchezp.ENMA.resources.Utilities;

import java.util.ArrayList;

import static miguelsanchezp.ENMA.resources.Utilities.*;

public class Vigenere {

    public static String cypher (String message, String key, boolean autokey) {
        ArrayList<Character> finalmessage = prepareWithTreatment(message, Configuration.getSpacesTreatment());
        String finalmessagestr = Utilities.toString(finalmessage);
        String fixedKey;
        if (!autokey) {
            fixedKey = adjustKeyLength (key, finalmessagestr.length());
        }else{
            fixedKey = adjustKeyLength (key, noSpaces(finalmessagestr, Configuration.getAlphabet()), finalmessagestr);
        }
        int round = 0;
        StringBuilder cyphered = new StringBuilder();
        for (char c : finalmessage) {
            StringBuilder sbM = new StringBuilder();
            StringBuilder sbK = new StringBuilder();
            sbM.append(c);
            sbK.append(fixedKey.charAt(round));
            if (Configuration.getAlphabet().contains(sbM) && Configuration.getAlphabet().contains(sbK)) {
                cyphered.append(Configuration.getAlphabet().charAt((Configuration.getAlphabet().indexOf(c)+Configuration.getAlphabet().indexOf(fixedKey.charAt(round)))%Configuration.getAlphabet().length()));
                round++;
            }else{
                cyphered.append(c);
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
                if (Configuration.getAlphabet().contains(sbM) && Configuration.getAlphabet().contains(sbK)) {
                    int pos = Configuration.getAlphabet().indexOf(c)-Configuration.getAlphabet().indexOf(fixedKey.charAt(round));
                    if (pos < 0) {
                        pos+=Configuration.getAlphabet().length();
                    }
                    decyphered.append(Configuration.getAlphabet().charAt(pos));
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
                if (Configuration.getAlphabet().contains(sbM) && Configuration.getAlphabet().contains(sbK)) {
                    int pos = Configuration.getAlphabet().indexOf(c)-Configuration.getAlphabet().indexOf(sbKF.charAt(round));
                    if (pos < 0) {
                        pos += Configuration.getAlphabet().length();
                    }
                    decyphered.append(Configuration.getAlphabet().charAt(pos));
                    sbKF.append(Configuration.getAlphabet().charAt(pos));
                    round++;
                }else{
                    decyphered.append(c);
                }
            }
        }
        return decyphered.toString();
    }
}