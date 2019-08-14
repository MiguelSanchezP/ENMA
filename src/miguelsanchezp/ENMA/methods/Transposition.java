package miguelsanchezp.ENMA.methods;

import miguelsanchezp.ENMA.resources.Utilities;

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
        return cyphered.toString().toUpperCase();
    }

    public static String cypherVertical (String message, String key) {
        StringBuilder cyphered = new StringBuilder();
        int rounds = message.length()%key.length();
        if (message.length()%key.length()!=0) {
            rounds=message.length()%key.length()+1;
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
        return cyphered.toString().toUpperCase();
    }
}
