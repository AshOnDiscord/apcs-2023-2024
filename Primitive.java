// 1. Create and populate an array of ints or doubles in SPVM
// 2. Create a Function that TAKES in the array you created: void functionx( int[] x)
// Change some of the values in the array
// Call the function from SPVM
// Iterate thru the elements.
// Were they modified from the function ? Why?
// 3. In the function, reallocate the array. x = new int[5]
// Populate the array
// when the Function ends, is the Array in SPVM changed ? why or why not ?

// the array has the different modifed values however it does not have the new array as arr(param one) is just a new var with a pointer to the array. by changing the value of arr in the function, it just changes the pointer to a new array and the original arr in psvm stays pointing to the original array.

public class Primitive {
  public static void main(String[] args) {
    int[] arr = new int[] { 1, 2, 3, 4, 5 };

    functionx(arr);

    for (int e : arr) {
      System.out.print(e + " ");
    }
  }

  public static void functionx(int[] arr) {
    arr[3] = 10;
    arr[1] = 20;

    arr = new int[5];
    arr[0] = 100;
    arr[1] = 200;
    arr[2] = 300;
    arr[3] = 400;
    arr[4] = 500;
  }
}
