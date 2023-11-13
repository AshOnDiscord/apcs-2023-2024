import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class Buildings {
    public static void main(String[] args) {
        Apartment apartment = new Apartment(100, 2, 1);
        Apartment apartment1 = new Apartment(50, 1, 1);
        Warehouse warehouse = new Warehouse(15000, 20);
        Warehouse warehouse1 = new Warehouse(40, 30, 10);
        ArrayList<Building> buildings = new ArrayList<Building>();
        buildings.add(apartment);
        buildings.add(apartment1);
        buildings.add(warehouse);
        buildings.add(warehouse1);
        for (java.lang.Object building : buildings.stream().sorted().toArray()) {
            System.out.println(building + " - $" + String.format("%,.2f", ((Building) building).getValue()));
        }
    }
}

abstract class Building implements Comparable<Building> {
    public abstract double getValue();

    @Override
    public int compareTo(Building o) {
        double diff = o.getValue() - this.getValue();
        return (int) Math.ceil(Math.abs(diff)) * (int) (Math.abs(diff) / diff);
    }
}

class Apartment extends Building {
    private int units;
    private int bedsPerUnit;
    private int bathsPerUnit;

    public Apartment(int units, int bedsPerUnit, int bathsPerUnit) {
        this.units = units;
        this.bedsPerUnit = bathsPerUnit;
        this.bathsPerUnit = bathsPerUnit;
    }

    public int getUnits() {
        return this.units;
    }

    public int getBedsPerUnit() {
        return this.bedsPerUnit;
    }

    public int getBathsPerUnit() {
        return this.bathsPerUnit;
    }

    @Override
    public double getValue() {
        double valuePerUnit = this.bedsPerUnit * 1000 + this.bathsPerUnit * 800;
        return this.units * valuePerUnit * 12; // units * yearlyRent
    }

    ;

    @Override
    public String toString() {
        return "Apartment(" + this.units + " units(" + this.bedsPerUnit + " beds, " + this.bathsPerUnit + " bathrooms))";
    }
}

class Warehouse extends Building {
    private double squareFeet;
    private double height;

    public Warehouse(double squareFeet, double height) {
        this.squareFeet = squareFeet;
        this.height = height;
    }

    public Warehouse(double width, double length, double height) {
        this.squareFeet = width * length;
        this.height = height;
    }

    public double getSquareFeet() {
        return this.squareFeet;
    }

    public double getHeight() {
        return this.height;
    }

    @Override
    public double getValue() {
        return (this.squareFeet * 12 / 8) * (this.height * 8);
    }

    @Override
    public String toString() {
        return "Warehouse(" + String.format("%,.2f", this.squareFeet) + " ft^2 x " + String.format("%,.2f", this.height) + " ft)";
    }
}