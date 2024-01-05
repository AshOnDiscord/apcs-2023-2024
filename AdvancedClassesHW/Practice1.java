package AdvancedClassesHW;

/**
 * PRACTICE 1:
 * 
 * USE : int n = (int) ‘e’ - (int) ‘a’;
 * To evaluate the String “the quick brown fox jumped over the lazy dog or over
 * Veer”
 * 
 * Determine how many times each letter occurs
 */

public class Practice1 {
  public static void main(String[] args) {
    String str = "the quick brown fox jumped over the lazy dog or over Veer";
    int[] freq = new int[26];
    for (int i = 0; i < str.length(); i++) {
      char ch = Character.toLowerCase(str.charAt(i));
      int letter = ch - 'a';
      if (letter >= 0 && letter < 26) {
        freq[letter]++;
      }
    }
    for (int i = 0; i < freq.length - 1; i++) {
      System.out.print((char) (i + 'a') + ": " + freq[i] + " | ");
    }
    System.out.println((char) (freq.length - 1 + 'a') + ": " + freq[freq.length - 1]);
  }
}