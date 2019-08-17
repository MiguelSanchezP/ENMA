package miguelsanchezp.ENMA.methods;

import miguelsanchezp.ENMA.resources.Utilities;

import java.util.ArrayList;

public class Transposition {
    public static String cypherHorizontal (String message, String key) {
        int pos = 0;
        StringBuilder cyphered = new StringBuilder();
        int vals = message.length();
        while (vals>0) {
            int cypherChar = ((pos/key.length())*key.length());
            int character = Character.getNumericValue(key.charAt(pos%key.length()));
            if (!((cypherChar+character) >= message.length())) {
                cyphered.append(message.charAt(cypherChar+character));
                vals--;
            }else{
                cyphered.append('X');
            }
            pos++;
        }
        return cyphered.toString();
    }

    public static String decypherHorizontal (String message, String key) {
        ArrayList<Character> decypheredTemp = new ArrayList<>();
        for (int i = 0; i<message.length(); i++) {
            decypheredTemp.add('X');
        }
        int round = 0;
        int lap = 0;
        for (int i = 0; i<message.length(); i++) {
            int num = Character.getNumericValue(key.charAt(round));
            System.out.println(i + " : " + (lap*key.length()+num) + " : " + message.charAt(i));
            decypheredTemp.set(lap*key.length()+num, message.charAt(i));
            round++;
            if (round%key.length()==0) {
                lap++;
                round = 0;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Character c : decypheredTemp) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static String cypherVertical (String message, String key) {
        StringBuilder cyphered = new StringBuilder();
        int rounds = message.length()/key.length();
        if (message.length()%key.length()!=0) {
            rounds=message.length()/key.length()+1;
        }
        for (int i = 0; i<key.length(); i++) {
            int keyVal = Character.getNumericValue(key.charAt(i));
            for (int j = 0; j<rounds; j++) {
                if (j*key.length()+keyVal < message.length()) {
                    cyphered.append(message.charAt(j*key.length()+keyVal));
                }else{
                    if (j*key.length()+keyVal < Utilities.nearestUpperMultiple(message.length(), key.length())) {
                        cyphered.append('X');
                    }
                }
            }
        }
        return cyphered.toString();
    }

    public static String decypherVertical (String message, String key) {
        ArrayList<Character> decypheredTemp = new ArrayList<>();
        int rounds = message.length()/key.length();
        if (message.length()%key.length() != 0) {
            rounds = (message.length()/key.length())+1;
        }
        for (int i = 0; i<key.length()*rounds; i++) {
            decypheredTemp.add('X');
        }
        int pos = 0;
        for (int i = 0; i<key.length(); i++) {
            int num = Character.getNumericValue(key.charAt(i));
            for (int j = 0; j<rounds; j++) {
                if (pos < message.length()) {
                    decypheredTemp.set(key.length() * j + num, message.charAt(pos));
                    pos++;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Character c : decypheredTemp) {
            sb.append(c);
        }
        return sb.toString();
    }
}
