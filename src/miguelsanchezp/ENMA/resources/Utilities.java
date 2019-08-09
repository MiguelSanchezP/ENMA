package miguelsanchezp.ENMA.resources;

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

    public static String adjustKeyLength (String key, int length, String message) { //check this method (cracks when keeping spaces)
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //----------------------------------------------------------------------------------
        /*
        StringBuilder sb = new StringBuilder ();
        int round = 0;
        for (int i = 0; i<length; i++) {
            if (i<key.length()) {
                sb.append(key.charAt(round));
                round++;
            }else{
                StringBuilder sb2 = new StringBuilder();
                sb2.append(Character.toUpperCase(message.charAt(round-key.length())));
                System.out.println("Outside "+ i);
                if (alphabet.contains(sb2)) {
                    sb.append(sb2);
                }else{
                }
            }
        }*/
        //---------------------------------------------------------------------------------
        int size = 0;
        StringBuilder parsedKey = new StringBuilder();
        while (size<length) { //add variable of round for here
            if (size<key.length()) {
                parsedKey.append(key.charAt(size));
                size += 1;
            }else{
                StringBuilder checker = new StringBuilder();
                checker.append(message.charAt(size-key.length()));
                if (alphabet.contains(checker)) {
                    parsedKey.append(message.charAt(size)); //<--
                    size+=1;
                }
                //round+=1 --> necessary
            }
        }
//        System.out.println(sb.toString());
//        return sb.toString().toUpperCase();
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
}
