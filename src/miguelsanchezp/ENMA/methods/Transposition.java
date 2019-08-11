package miguelsanchezp.ENMA.methods;

public class Transposition {
    public static String cypher (String message, String key) {
        StringBuilder sb = new StringBuilder();
        int position = 0;
        int lap = 0;
        for (int i = 0; i<message.length(); i++) {
            if (position == key.length()) {
                position=0;
                lap += 1;
            }
            int val = position+48;
            char num = (char)val;
            System.out.println(num);
            System.out.println(message.charAt(key.length()*lap+key.indexOf(num)));
            sb.append(message.charAt(key.length()*lap+key.indexOf(num)));
            position++;
        }
        return sb.toString();
    }
}
