// Build a Class Called UseBase 
// That performs the following functionality:  Adds, Subtracts, Multiplies
// And Divides numbers in Base 2, Hex and Decimal.  ALso converts from
// One base to another (organically, not using existing methods)

public class UseBase {
  public static Hex binaryToHex(Binary binary) {
    return decimalToHex(binaryToDecimal(binary));
  }
  public static Decimal binaryToDecimal(Binary binary) {
    int value = 0;
    for (int i = 0; i < binary.getValue().length; i++) {
      value += binary.getValue()[i] ? Math.pow(2, binary.getValue().length - i - 1) : 0;
    }
    return new Decimal((double) value);
  }
  public static Hex decimalToHex(Decimal decimal) {
    String value = "";
    char[] rep = "0123456789ABCDEF".toCharArray(); // you can just do += rep[value]
    Decimal temp = new Decimal(decimal.getValue());
    while (temp.getValue() > 0) {
      value = rep[(int) (temp.getValue() % 16)] + value;
      temp.setValue(Math.floor(temp.getValue() / 16));
    }
    return new Hex(value);
  }
  public static Binary decimalToBinary(Decimal decimal) {
    String value = "";
    Decimal temp = new Decimal(decimal.getValue());
    while (temp.getValue() > 0) {
      value = (temp.getValue() % 2 == 1 ? "1" : "0") + value;
      temp.setValue(Math.floor(temp.getValue() / 2));
    }
    return new Binary(value);
  }
  public static Decimal hexToDecimal(Hex hex) {
    String value = hex.getValue();
    int decimal = 0;
    for (int i = 0; i < value.length(); i++) {
      decimal += "0123456789ABCDEF".indexOf(value.charAt(i)) * Math.pow(16, value.length() - i - 1);
    }
    return new Decimal((double) decimal);
  }
  public static Binary hexToBinary(Hex hex) {
    return decimalToBinary(hexToDecimal(hex));
  }
  public static void main(String[] args) {
    Decimal d = new Decimal(10);
    System.out.println(d);
    System.out.println(decimalToHex(d));
    System.out.println(decimalToBinary(d));
    System.out.println();
    Binary b = new Binary("1010");
    System.out.println(binaryToDecimal(b));
    System.out.println(binaryToHex(b));
    System.out.println(b);
    System.out.println();
    Hex h = new Hex("A");
    System.out.println(hexToDecimal(h));
    System.out.println(h);
    System.out.println(hexToBinary(h));
  
  }
}

class Hex {
  private String value;
  public Hex(String value) {
    this.value = value;
  }
  public void setValue(String value) {
    this.value = value;
  }
  public String getValue() {
    return value;
  }
  @Override
  public String toString() {
    return value;
  }
}

class Binary {
  private boolean[] value;
  public Binary(boolean[] value) {
    int bytes = (int) Math.ceil(value.length / 8.0);
    this.value = new boolean[bytes];
    for (int i = 0; i < value.length; i++) {
      this.value[this.value.length - i - 1] = value[value.length - i - 1];
    }
  }
  public Binary(String value) {
    // 1 byte = 128
    // short - 2 bytes, 32k
    // int - 4 bytes, 2 billion
    // long - 8 bytes, 9 quintillion
    int bytes = (int) Math.ceil(value.length() / 8.0);
    this.value = new boolean[bytes * 8];
    for (int i = 0; i < value.length(); i++) {
      this.value[this.value.length - i - 1] = value.charAt(value.length() - i - 1) == '1';
    }
  }
  public void setValue(boolean[] value) {
    this.value = value;
  }
  public boolean[] getValue() {
    return value;
  }
  public String toString() {
    String s = "";
    for (boolean b : value) {
      s += b ? "1" : "0";
    }
    return s;
  }
}

// just double
class Decimal {
  private double value;
  public Decimal(double value) {
    this.value = value;
  }
  public void setValue(double value) {
    this.value = value;
  }
  public double getValue() {
    return value;
  }
  public String toString() {
    return String.valueOf(value);
  }
}