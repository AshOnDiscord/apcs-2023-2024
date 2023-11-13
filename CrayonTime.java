import java.util.*;

public class CrayonTime {
    public static void main(String[] args) {
        String[] strs = randomStrs();
        int[] ints = randomInts();
        Fish[] fishs = randomFishs();
        cS(strs);
        for (String s : strs) {
            System.out.print(s + " ");
        }
        System.out.println();
        cI(ints);
        for (int i : ints) {
            System.out.println(i);
        }
        cF(fishs);
        for (Fish f : fishs) {
            System.out.println(f);
        }
    }

    public static void cS(String[] arr) {
        if (arr[0].charAt(0) >= 'P') {
            arr[0] = "Z";
        }
    }

    public static void cI(int[] arr) {
        int ran = new Random().nextInt(4);
        if (ran == 1) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = 999;
            }
        } else if (ran == 2) {
            for (int i = 0; i < arr.length; i += 2) {
                arr[i] = 555;
            }
        } else if (ran == 3) {
            for (int i = 0; i < arr.length; i += 3) {
                arr[i] = 444;
            }
        }
    }

    public static void cF(Fish[] arr) {
        for (Fish f : arr) {
            if (f.len > 10) {
                f.name = "Tuna";
            }
        }
    }

    public static String[] randomStrs() {
        int l = new Random().nextInt(101);
        String[] t = new String[l];
        for (int i = 0; i < l; i++) {
            char t2 = (char) (new Random().nextInt(26) + 'A');
            t[i] = String.valueOf(t2);
        }
        return t;
    }

    public static int[] randomInts() {
        int l = new Random().nextInt(101);
        int[] t = new int[l];
        for (int i = 0; i < l; i++) {
            t[i] = new Random().nextInt(101);
        }
        return t;
    }

    public static Fish[] randomFishs() {
        int l = new Random().nextInt(101);
        Fish[] t = new Fish[l];
        for (int i = 0; i < l; i++) {
            t[i] = new Fish(new Random().nextDouble(20), "Not Tuna");
        }
        return t;
    }
}

class Fish {
    public String name;
    public double len;

    public Fish(double len, String name) {
        this.len = len;
        this.name = name;
    }

    public String toString() {
        return this.name + " - " + this.len;
    }
}