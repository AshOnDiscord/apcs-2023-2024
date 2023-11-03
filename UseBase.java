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
  
  // Addition
  public static Decimal add(Decimal decimal1, Decimal decimal2) {
    double result = decimal1.getValue() + decimal2.getValue();
    return new Decimal(result);
  }

  public static Binary add(Binary binary1, Binary binary2) {
    // Convert binary to decimal, perform addition, and then convert back to binary
    Decimal decimal1 = binaryToDecimal(binary1);
    Decimal decimal2 = binaryToDecimal(binary2);
    Decimal sumDecimal = add(decimal1, decimal2);
    return decimalToBinary(sumDecimal);
  }

  public static Hex add(Hex hex1, Hex hex2) {
      // Convert hexadecimal to decimal, perform addition, and then convert back to hexadecimal
      Decimal decimal1 = hexToDecimal(hex1);
      Decimal decimal2 = hexToDecimal(hex2);
      Decimal sumDecimal = add(decimal1, decimal2);
      return decimalToHex(sumDecimal);
  }

  // Subtraction 
  public static Decimal subtract(Decimal decimal1, Decimal decimal2) {
    double result = decimal1.getValue() - decimal2.getValue();
    return new Decimal(result);
  }

  public static Binary subtract(Binary binary1, Binary binary2) {
    // Convert binary to decimal, perform addition, and then convert back to binary
    Decimal decimal1 = binaryToDecimal(binary1);
    Decimal decimal2 = binaryToDecimal(binary2);
    Decimal sumDecimal = subtract(decimal1, decimal2);
    return decimalToBinary(sumDecimal);
  }

  public static Hex subtract(Hex hex1, Hex hex2) {
      // Convert hexadecimal to decimal, perform addition, and then convert back to hexadecimal
      Decimal decimal1 = hexToDecimal(hex1);
      Decimal decimal2 = hexToDecimal(hex2);
      Decimal sumDecimal = subtract(decimal1, decimal2);
      return decimalToHex(sumDecimal);

  // Multiplication
  public static Decimal multiply(Decimal decimal1, Decimal decimal2) {
      double result = decimal1.getValue() * decimal2.getValue();
      return new Decimal(result);
  }

  public static Binary multiply(Binary binary1, Binary binary2) {
      Decimal decimal1 = binaryToDecimal(binary1);
      Decimal decimal2 = binaryToDecimal(binary2);
      Decimal productDecimal = multiply(decimal1, decimal2);
      return decimalToBinary(productDecimal);
  }

  public static Hex multiply(Hex hex1, Hex hex2) {
      Decimal decimal1 = hexToDecimal(hex1);
      Decimal decimal2 = hexToDecimal(hex2);
      Decimal productDecimal = multiply(decimal1, decimal2);
      return decimalToHex(productDecimal);
  }

  // Division
  public static Decimal divide(Decimal dividend, Decimal divisor) {
      if (divisor.getValue() == 0.0) {
          // Handle division by zero
          throw new ArithmeticException("Division by zero");
      }
      double result = dividend.getValue() / divisor.getValue();
      return new Decimal(result);
  }

  public static Binary divide(Binary dividend, Binary divisor) {
      Decimal decimalDividend = binaryToDecimal(dividend);
      Decimal decimalDivisor = binaryToDecimal(divisor);
      Decimal quotientDecimal = divide(decimalDividend, decimalDivisor);
      return decimalToBinary(quotientDecimal);
  }

  public static Hex divide(Hex dividend, Hex divisor) {
      Decimal decimalDividend = hexToDecimal(dividend);
      Decimal decimalDivisor = hexToDecimal(divisor);
      Decimal quotientDecimal = divide(decimalDividend, decimalDivisor);
      return decimalToHex(quotientDecimal);
  }

  
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the first number (in base 10): ");
    double num1 = scanner.nextDouble();
    Decimal decimal1 = new Decimal(num1);

    System.out.print("Enter the second number (in base 10): ");
    double num2 = scanner.nextDouble();
    Decimal decimal2 = new Decimal(num2);

    // Addition
    Decimal sumDecimal = add(decimal1, decimal2);
    System.out.println("Addition (Decimal): " + sumDecimal);

    // Multiplication
    Binary productBinary = multiply(decimalToBinary(decimal1), decimalToBinary(decimal2));
    System.out.println("Multiplication (Binary): " + productBinary);

    // Division
    try {
        Hex quotientHex = divide(decimalToHex(decimal1), decimalToHex(decimal2));
        System.out.println("Division (Hex): " + quotientHex);
    } catch (ArithmeticException e) {
        System.out.println("Division by zero is not allowed.");
    }
    scanner.close();
  
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
