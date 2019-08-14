package miguelsanchezp.ENMA.methods;

import miguelsanchezp.ENMA.definitions.Configuration;
import miguelsanchezp.ENMA.resources.Utilities;

import java.util.ArrayList;

import static miguelsanchezp.ENMA.Controller.conf;
import static miguelsanchezp.ENMA.resources.Utilities.*;

public class ROT {

    public static String cypher (String message, int number) {
        ArrayList<Character> finalmessage = prepareWithTreatment(message, conf.getSpacesTreatment());
        StringBuilder sb = new StringBuilder();
        for (char c : finalmessage) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(c);
            if (conf.getAlphabet().contains(sb2)) {
                int pos = conf.getAlphabet().indexOf(c);
                pos += number;
                if (pos > 25) {
                    pos -= 26;
                }
                sb.append(conf.getAlphabet().charAt(pos));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String decypher (String message, int number) {
        ArrayList<Character> message2 = Utilities.toArray(message);
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
