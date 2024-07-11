//Create a program that works with the String class to perform the following tasks:
//Create a string “thequickbrownfoxjumpsoverthelazydog”
//
//Create a new string that changes all lc “t” to uc “T”
//
//Determine how many vowels are in the string
//
//Determine how many occurances of “th” appear i. The string
//
//Create a set of strings from the first string that consist of 7 letters each
//
//Using the new strings, take the first string and see if an 2 char sequences exist in the remaining strings
//Ex/ th exists in the string “otherst”
//He exists in the string overthe

public class StringPrac {
    public static void main(String[] args) {
        String str = "thequickbrownfoxjumpsoverthelazydog";

        String result = str.replace('t', 'T');

        int vowels = 0;
        int thOccurances = 0; // loop based approach to thOccurances, more efficent since it reuses the vowel loop
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'a' || str.charAt(i) == 'e' || str.charAt(i) == 'i' || str.charAt(i) == 'o' || str.charAt(i) == 'u') {
                vowels++;
            }
            if (i < str.length() - 1 && str.charAt(i) == 't' && str.charAt(i + 1) == 'h') {
                thOccurances++;
            }
        }

        int thOccurancesIO = 0; // indexOf and substring approach to thOccurances
        String temp = str;
        while (temp.contains("th")) {
            thOccurancesIO++;
            temp = temp.substring(temp.indexOf("th") + 2);
        }

        String[] sevenLetterStrings = new String[str.length() / 7 - 1];
        for (int i = 0; i < sevenLetterStrings.length; i++) {
            sevenLetterStrings[i] = str.substring(i * 7, (i + 1) * 7);
        }

        System.out.println("Original: " + str);
        System.out.println("Replace t with T: " + result);
        System.out.println("Vowels: " + vowels);
        System.out.println("th Occurances(loop): " + thOccurances);
        System.out.println("th Occurances(indexOf): " + thOccurancesIO);
        for (int i = 0; i < sevenLetterStrings.length; i++) {
            System.out.println(i + ": " + sevenLetterStrings[i]);
        }
    }
}
