package miguelsanchezp.ENMA.methods;

import miguelsanchezp.ENMA.resources.Utilities;

import java.util.ArrayList;

import static miguelsanchezp.ENMA.Controller.conf;
import static miguelsanchezp.ENMA.resources.Utilities.*;

public class ROT {

    public static String cypher (String message, int number) {
        String messagePrep = InputPreparation(message);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i<messagePrep.length(); i++) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(messagePrep.charAt(i));
            if (conf.getAlphabet().contains(sb2)) {
                int pos = Character.toUpperCase(conf.getAlphabet().indexOf(messagePrep.charAt(i)));
                pos += number;
                if (pos > 25) {
                    pos -= 26;
                }
                sb.append(conf.getAlphabet().charAt(pos));
            } else {
                sb.append(messagePrep.charAt(i));
            }
        }
        return sb.toString();
    }

    public static String decypher (String message, int number) {
        ArrayList<Character> message2 = toArray(message);
        StringBuilder sb2 = new StringBuilder();
        for (char c : message2) {
            StringBuilder sb = new StringBuilder();
            char C = Character.toUpperCase(c);
            sb.append(C);
            if (conf.getAlphabet().contains(sb)) {
                int pos = conf.getAlphabet().indexOf(C);
                pos -= number;
                if (pos<0) {
                    pos+=26;
                }
                sb2.append(conf.getAlphabet().charAt(pos));
            }else{
                sb2.append(C);
            }
        }
        return sb2.toString();
    }
}
