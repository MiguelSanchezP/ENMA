package miguelsanchezp.ENMA.methods;

import miguelsanchezp.ENMA.resources.Utilities;

import java.util.ArrayList;

import static miguelsanchezp.ENMA.resources.Utilities.adjustKeyLength;
import static miguelsanchezp.ENMA.resources.Utilities.prepareWithTreatment;

public class Vigenere {
    private static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String cypher (String message, String key, boolean autokey, String treatment) {
        ArrayList<Character> finalmessage = prepareWithTreatment(message, treatment);
        String finalmessagestr = Utilities.toString(finalmessage);
        String fixedKey;
        if (!autokey) {
            fixedKey = adjustKeyLength (key, finalmessagestr.length());
        }else{
            fixedKey = adjustKeyLength (key, finalmessagestr.length(), finalmessagestr);
        }
        int round = 0;
        StringBuilder cyphered = new StringBuilder();
        for (char c : finalmessage) {
            StringBuilder sbM = new StringBuilder();
            StringBuilder sbK = new StringBuilder();
            sbM.append(c);
            sbK.append(fixedKey.charAt(round));
            if (alphabet.contains(sbM) && alphabet.contains(sbK)) {
                cyphered.append(alphabet.charAt((alphabet.indexOf(c)+alphabet.indexOf(fixedKey.charAt(round)))%alphabet.length()));
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
                StringBuilder sb = new StringBuilder();
                sb.append(c);
                StringBuilder sb2 = new StringBuilder();
                sb2.append(fixedKey.charAt(round));
                if (alphabet.contains(sb) && alphabet.contains(sb2)) {
                    int pos = alphabet.indexOf(c)-alphabet.indexOf(fixedKey.charAt(round));
                    if (pos < 0) {
                        pos+=alphabet.length();
                    }
                    decyphered.append(alphabet.charAt(pos));
                    round++;
                }else{
                    decyphered.append(c);
                }
            }
        }
        return decyphered.toString();
    }
}
