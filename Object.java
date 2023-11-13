// 1. Create and populate an array of a "Mutable " Class (the Car class) SPVM
// 2. Create a Function that TAKES in the array you created: void functionx( Car[] x)
// Change the state of a few of the Car instances in the array
// Re-create a NEW Car instance in the array
// Call the function from SPVM
// Iterate thru the elements.
// Were they modified from the function ? Why?
// 3. In the function, reallocate the array. x = new Car[5]
// Populate the array
// when the Function ends, is the Array in SPVM changed ? why or why not ?

// Same thing as primitive.java, the arr var is just a pointer to the array(of pointers), so when you change the value of arr in the function, it just changes the pointer to a new array and the original arr in psvm stays pointing to the original array. as for the object fields, since we are passed a pointer and not a value copy, we can access the original object and not a copy. so changes are reflected in the original object and hence the orignal array too.

public class Object {
    public static void main(String[] args) {
        Car[] arr = new Car[5];

        arr[0] = new Car("Mini", "Cooper");
        arr[1] = new Car("KIa", "Soul");
        arr[2] = new Car("Ferrarri", "F40");
        arr[3] = new Car("McLaren", "P1");
        arr[4] = new Car("Nissan", "GTR");

        functionx(arr);

        for (Car e : arr) {
            System.out.println(e);
        }
    }

    public static void functionx(Car[] arr) {
        arr[2].setMake("Toyota");
        arr[4].setModel("Camry");

        arr[0] = new Car("Honda", "Civic");

        arr = new Car[5];

        arr[0] = new Car("Honda", "Civic");
        arr[1] = new Car("BMW", "M3");
        arr[2] = new Car("Toyota", "Corolla");
        arr[3] = new Car("Ford", "Mustang");
        arr[4] = new Car("Tesla", "Model 3");
    }

    static class Car {
        private String make;
        private String model;

        public Car(String make, String model) {
            this.make = make;
            this.model = model;
        }

        public void setMake(String make) {
            this.make = make;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getMake() {
            return this.make;
        }

        public String getModel() {
            return this.model;
        }

        @Override
        public String toString() {
            return this.make + " " + this.model;
        }
    }
}