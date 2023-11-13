// Write a class called Sortall that will ultimately provide for various sorts. Initially, write a bubblesort that
// can work on ANY array of sortable objects.

// Public ? class Sortall ?

// ?

// Public ? bubbleSort( ? )

// IN MY SPVM, I should be able to do the following:

// String s[] = new String[300];

// …

// Sortall.bubbleSort(s);

import java.util.Random;

public class SortAll {
    public static void main(String[] args) {
        String s[] = new String[300];
        for (int i = 0; i < s.length; i++) {
            s[i] = String.valueOf((char) (new Random().nextInt(26) + 'A'));
        }
        bubbleSort(s);
        for (String s2 : s) {
            System.out.print(s2 + " ");
        }

        Integer[] ints = new Integer[300];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = new Random().nextInt(100);
        }
        bubbleSort(ints);
        System.out.println("\n");
        for (int i : ints) {
            System.out.print(i + " ");
        }
    }

    @SuppressWarnings("unchecked")
    public static void bubbleSort(Comparable[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (arr[i].compareTo(arr[j]) > 0) {
                    Comparable temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
}
