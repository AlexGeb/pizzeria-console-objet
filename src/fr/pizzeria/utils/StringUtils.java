package fr.pizzeria.utils;

public class StringUtils {

	public static boolean isInteger(final String s) {
        if (isEmpty(s)) {
           return false;
        }
       final int sz = s.length();
        for (int i = 0; i < sz; i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
       }
        return true;
	}
	
    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

}
