/**
 * A library of string functions.
 */
public class MyString {
    public static void main(String args[]) {
        String hello = "hello";
        System.out.println(countChar(hello, 'h'));
        System.out.println(countChar(hello, 'l'));
        System.out.println(countChar(hello, 'z'));
        System.out.println(spacedString(hello));
        //// Put your other tests here.
    }

    /**
     * Returns the number of times the given character appears in the given string.
     * Example: countChar("Center",'e') returns 2 and countChar("Center",'c') returns 0. 
     * 
     * @param str - a string
     * @param c - a character
     * @return the number of times c appears in str
     */
    public static int countChar(String str, char ch) {
        if (str == null || str.isEmpty()) return 0;
        int counter = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                counter++;
            }
        }
        return counter;
    }

    /** Returns true if str1 is a subset string str2, false otherwise
     *  Examples:
     *  subsetOf("sap","space") returns true
     *  subsetOf("spa","space") returns true
     *  subsetOf("pass","space") returns false
     *  subsetOf("c","space") returns true
     *
     * @param str1 - a string
     * @param str2 - a string
     * @return true is str1 is a subset of str2, false otherwise
     */
    public static boolean subsetOf(String str1, String str2) {
        if (str1.isEmpty()) return true;
        for (int i = 0; i < str1.length(); i++) {
            char c = str1.charAt(i);
            if (!str2.contains(Character.toString(c))) {
                return false;
            }
            str2 = str2.replaceFirst(Character.toString(c), "");
        }
        return true;
    }

    /** Returns a string which is the same as the given string, with a space
     * character inserted after each character in the given string, except
     * for the last character. 
     * Example: spacedString("silent") returns "s i l e n t"
     * 
     * @param str - a string
     * @return a string consisting of the characters of str, separated by spaces.
     */
    public static String spacedString(String str) {
        if (str == null || str.length() <= 1) return str;
        char[] result = new char[(2 * str.length()) - 1];
        int location = 0;

        for (int i = 0; i < str.length(); i++) {
            result[location++] = str.charAt(i);
            if (i < str.length() - 1) {
                result[location++] = ' ';
            }
        }

        return new String(result);
    }

    /**
     * Returns a string of n lowercase letters, selected randomly from 
     * the English alphabet 'a', 'b', 'c', ..., 'z'. Note that the same
     * letter can be selected more than once.
     * 
     * Example: randomStringOfLetters(3) can return "zoo"
     * 
     * @param n - the number of letter to select
     * @return a randomly generated string, consisting of 'n' lowercase letters
     */
    public static String randomStringOfLetters(int n) {
        char[] result = new char[n];
        for (int i = 0; i < result.length; i++) {
            result[i] = (char) (97 + (int) (Math.random() * 26));
        }
        return new String(result);
    }

    /**
     * Returns a string consisting of the string str1, minus all the characters in the
     * string str2. Assumes (without checking) that str2 is a subset of str1.
     * Example: remove("meet","committee") returns "comit" 
     * 
     * @param str1 - a string
     * @param str2 - a string
     * @return a string consisting of str1 minus all the characters of str2
     */
    public static String remove(String str1, String str2) {
        if (str2.isEmpty()) return str1;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str1.length(); i++) {
            char currentChar = str1.charAt(i);
            if (!str2.contains(Character.toString(currentChar))) {
                result.append(currentChar);
            } else {
                str2 = str2.replaceFirst(Character.toString(currentChar), "");
            }
        }
        return result.toString();
    }

    /**
     * Returns a string consisting of the given string, with the given 
     * character inserted randomly somewhere in the string.
     * For example, insertRandomly("s","cat") can return "scat", or "csat", or "cast", or "cats".  
     * @param ch - a character
     * @param str - a string
     * @return a string consisting of str with ch inserted somewhere
     */
    public static String insertRandomly(char ch, String str) {
         int randomIndex = (int) (Math.random() * (str.length() + 1));
         return str.substring(0, randomIndex) + ch + str.substring(randomIndex);
    }    
}
