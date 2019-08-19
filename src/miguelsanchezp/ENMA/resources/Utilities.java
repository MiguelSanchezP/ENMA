package miguelsanchezp.ENMA.resources;

import java.util.ArrayList;

import static miguelsanchezp.ENMA.Controller.conf;

public class Utilities {

    public static String adjustKeyLength (String key, int length) {
        StringBuilder sb = new StringBuilder ();
        for (int i = key.length(); i<length+key.length(); i++) {
            sb.append(key.charAt(i%key.length()));
        }
        return sb.toString().toUpperCase();
    }

    public static String adjustKeyLength (String key, int length, String message) {
        int size = 0;
        int round = 0;
        StringBuilder parsedKey = new StringBuilder();
        while (size<=length) {
            if (size<key.length()) {
                parsedKey.append(key.charAt(size));
                round += 1;
            }else{
                StringBuilder checker = new StringBuilder();
                char c = message.charAt(round-key.length());
                checker.append(c);
                if (conf.getAlphabet().contains(checker)) {
                    parsedKey.append(c);
                }
                round+=1;
            }
            size=parsedKey.length();
        }
        parsedKey.setLength(message.length());
        return parsedKey.toString().toUpperCase();
    }

    public static ArrayList<Character> toArray (String s) {
        ArrayList<Character> chars = new ArrayList<>();
        for (int i = 0; i<s.length(); i++) {
            chars.add(s.charAt(i));
        }
        return chars;
    }

    public static int noSpaces (String s) {
        int size=0;
        for (int i = 0; i<s.length(); i++) {
            StringBuilder sb = new StringBuilder ();
            sb.append(s.charAt(i));
            if(conf.getAlphabet().contains(sb)) {
                size++;
            }
        }
        return size;
    }

    public static int nearestUpperMultiple (int initialVal, int divisor) {
        int i = initialVal;
        while (i%divisor != 0) {
            i++;
        }
        return i;
    }

    private static String NoSpacedString (String message) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i<message.length(); i++) {
            if (message.charAt(i) != ' ') {
                sb.append(message.charAt(i));
            }
        }
        return sb.toString();
    }

    private static String XSpacedString (String message) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i<message.length(); i++) {
            if (message.charAt(i)!=' ') {
                sb.append(message.charAt(i));
            }else{
                sb.append('X');
            }
        }
        return sb.toString();
    }

    public static String InputPreparation(String message) {
        switch (conf.getSpacesTreatment()) {
            case "Keep":
                return message;
            case "Ignore":
                return NoSpacedString(message);
            case "X":
                return XSpacedString(message);
        }
        return message;
    }
}
