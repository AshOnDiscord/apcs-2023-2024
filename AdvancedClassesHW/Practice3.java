package AdvancedClassesHW;

/**
 * Create a function that generates a random number given the following inputs:
 * 
 * Minimum number or -1 to exclude this requirement
 * 
 * Maximum number or -1 to exclude this requirement
 * 
 * Odd number or -1 to exclude this requirement
 * 
 * Even Number or -1 to exclude this requirement
 * 
 * Prime Number or -1 to exclude this requirement
 * 
 * 
 * NOTE: you cant allow the user to ask for BOTH an odd and an even number and
 * if they ask for a prime number within a range that does not contain a prime,
 * return a NOT POSSIBLE message
 */

public class Practice3 { // why don't we just use boolean values?
  public static int generateRandomNumber(int min, int max, int oddInt, int evenInt, int primeInt) {
    boolean odd = oddInt != -1;
    boolean even = evenInt != -1;
    boolean prime = primeInt != -1;

    if (odd && even) {
      throw new IllegalArgumentException("Cannot ask for both an odd and even number");
    }

    if (prime && (min > 2 || max < 2)) {
      throw new IllegalArgumentException("Cannot ask for a prime number within a range that does not contain a prime");
    }

    for (;;) {
      int rand = 0;
      if (min != -1) {
        if (max != -1) {
          // both min and max are defined
          rand = (int) (Math.random() * (max - min + 1)) + min;
        } else {
          // only min is defined
          rand = (int) (Math.random() * (Integer.MAX_VALUE - min + 1)) + min;
        }
      } else {
        if (max != -1) {
          // only max is defined
          rand = (int) (Math.random() * (max + 1));
        } else {
          // neither min nor max are defined
          rand = (int) (Math.random() * Integer.MAX_VALUE);
        }
      }
      if (prime) {
        // check if rand is prime
        boolean isPrime = true;
        for (int i = 2; i < rand; i++) {
          if (rand % i == 0) {
            isPrime = false;
            break;
          }
        }
        if (isPrime) {
          return rand;
        } else {
          continue; // regenerate rand
        }
      }
      if (rand % 2 == 0) {
        // rand is even
        if (even) {
          return rand;
        } else {
          continue; // regenerate rand
        }
      } else {
        // rand is odd
        if (odd) {
          return rand;
        } else {
          continue; // regenerate rand
        }
      }
    }
  }
}
