// Create a class called Investment
//      Implement the Comparable interface
//      Holds name, age, phone number and SSN of Investor
//      Investment value
//      Setters and getters
//      setValue method callable from parent and children only
//      Abstract calcValue method
// Create a child called CD.
//      Holds amount --- the starting dollars invested in the CD,
//      rate --- the interest rate by which the amount grows,
//      term --- The number of days in which the CD “matures”,
//      calcValue impl
//          value = amount TIMES [ term TIMES [rate / 360]]
//      Display the Name, value and Type of an investment as a string
// Create another child called MutualFund
//      holds units --- the number of “shares” held in this fund,
//      unit value --- the dollar value of each “unit”
//      calcValue impl
//          value = units TIMES unit value
//      Display the Name, value and Type of an investment as a string
// Use TestInvestment.java to test your program
//      Creates an Array of Investments
//      Instantiates three investment types
//      Compares different investments
//      Displays the state of an investment

/**
 * Application1.java
 * Description:
 *
 * @author David Farrell
 */


public class TestInvestment {
    // Main entry point
    static public void main(String[] args) {
        Investment[] I = new Investment[3];


        I[0] = new CD("Casey", "973-555-3434", "111-22-3333", 21, 1000.0, 8.5, 90);
        I[1] = new MutualFund("John", "973-555-3434", "111-22-3333", 21, 1000.0, 10.50);
        I[2] = new CD("Farrell", "973-555-3434", "111-22-3333", 21, 1000.0, 6.5, 90);

        I[0].calcValue();
        I[1].calcValue();
        I[2].calcValue();


        if (I[0].compareTo(I[2]) > 0) {
            System.out.println(I[0].getName() + " Has More Invested");
            System.out.println(I[0].toString());
        } else {
            System.out.println(I[1].getName() + " Has More Invested");
            System.out.println(I[1].toString());
        }

        if (I[2].compareTo(I[1]) > 0) {
            System.out.println(I[2].getName() + " Has More Invested");
            System.out.println(I[2].toString());
        } else {
            System.out.println(I[1].getName() + " Has More Invested");
            System.out.println(I[1].toString());
        }

        // Print Details of the Mutual Fund
        System.out.println(I[1].getName() + " " + I[1].getSsn() + " " +
                I[1].getValue());

    }
}

abstract class Investment implements Comparable<Investment> {
    private String name;
    private double age;
    private String phone;
    private String ssn;
    private double value;

    public Investment(String name, double age, String phone, String ssn, double value) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.ssn = ssn;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public double getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public String getSsn() {
        return ssn;
    }

    public double getValue() {
        return value;
    }

    protected void setValue(double value) {
        this.value = value;
    }

    public abstract double calcValue();

    @Override
    public int compareTo(Investment o) {
        return Double.compare(this.getValue(), o.getValue());
    }
}

class CD extends Investment {
    private double amount;
    private double rate;
    private double term;

    public CD(String name, String phone, String ssn, double age, double amount, double rate, double term) {
        super(name, age, phone, ssn, 0);
        this.amount = amount;
        this.rate = rate;
        this.term = term;
        this.setValue(this.calcValue());
    }

    public double getAmount() {
        return amount;
    }

    public double getRate() {
        return rate;
    }

    public double getTerm() {
        return term;
    }

    public double calcValue() {
        return amount * (term * (rate / 360));
    }

    @Override
    public String toString() {
        return getName() + " - CD - " + getValue();
    }
}

class MutualFund extends Investment {
    private double units;
    private double unitValue;

    public MutualFund(String name, String phone, String ssn, double age, double units, double unitValue) {
        super(name, age, phone, ssn, 0);
        this.units = units;
        this.unitValue = unitValue;
        this.setValue(this.calcValue());
    }

    public double getUnits() {
        return units;
    }

    public double getUnitValue() {
        return unitValue;
    }

    public double calcValue() {
        return units * unitValue;
    }

    @Override
    public String toString() {
        return getName() + " - MutualFunds - " + getValue();
    }
}