public class Comparing {
 public static void main(String[] args) {
   Car c = new Car(2020);
   Comparable x = new Car(2020) ;
   Comparable s = new String("sallyjoebob");
   System.out.println(s.length());

   System.out.println(x.compareTo(c)); 
 }
}

class Car implements Comparable<Car>
{
 private int year;
 public Car(int y)
 {
   year = y;
 }
 public int  compareTo(Car x)
 {
   return this.year - x.year;
 }
}
