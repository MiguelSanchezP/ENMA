package miguelsanchezp.ENMA.methods;

public class Transposition {
    public static String cypher (String message, String key) {
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
}
