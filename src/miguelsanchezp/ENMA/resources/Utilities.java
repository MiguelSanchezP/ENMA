package miguelsanchezp.ENMA.resources;

import miguelsanchezp.ENMA.definitions.Configuration;

import java.util.ArrayList;

public class Utilities {
    private static ArrayList<Character> prepareMessageNoSpace(String message) {
        ArrayList<Character> characters = new ArrayList<>();
        for (int i = 0; i<message.length(); i++) {
            if (message.charAt(i) != ' ') {
                characters.add(Character.toUpperCase(message.charAt(i)));
            }
        }
        return characters;
    }
    private static ArrayList<Character> prepareMessageSpaceX (String message) {
        ArrayList<Character> characters = new ArrayList<>();
        for (int i = 0; i<message.length(); i++) {
            if (message.charAt(i) == ' ') {
                characters.add('X');
            }else{
                characters.add(Character.toUpperCase(message.charAt(i)));
            }
        }
        return characters;
    }
    private static ArrayList<Character> prepareMessageSpaced (String message) {
        ArrayList<Character> characters = new ArrayList<>();
        for (int i = 0; i<message.length(); i++) {
            characters.add(Character.toUpperCase(message.charAt(i)));
        }
        return characters;
    }

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
                if (Configuration.getAlphabet().contains(checker)) {
                    parsedKey.append(c);
                }
                round+=1;
            }
            size=parsedKey.length();
        }
        parsedKey.setLength(message.length());
        return parsedKey.toString().toUpperCase();
    }

    public static ArrayList<Character> prepareWithTreatment (String message, String treatment) {
        ArrayList<Character> finalmessage = new ArrayList<>();
        if (treatment.equals("Keep")) {
            finalmessage = prepareMessageSpaced(message);
        }
        if (treatment.equals("X")) {
            finalmessage = prepareMessageSpaceX(message);
        }
        if (treatment.equals("Ignore")) {
            finalmessage = prepareMessageNoSpace(message);
        }
        return finalmessage;
    }

    public static String toString (ArrayList<Character> a) {
        StringBuilder sb = new StringBuilder();
        for (char c : a) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static ArrayList<Character> toArray (String s) {
        ArrayList<Character> chars = new ArrayList<>();
        for (int i = 0; i<s.length(); i++) {
            chars.add(s.charAt(i));
        }
        return chars;
    }

    public static int noSpaces (String s, String alphabet) {
        int size=0;
        for (int i = 0; i<s.length(); i++) {
            StringBuilder sb = new StringBuilder ();
            sb.append(s.charAt(i));
            if(alphabet.contains(sb)) {
                size++;
            }
        }
        return size;
    }
}
