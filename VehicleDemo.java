// Base class
class Vehicle {
    protected String make;
    protected String model;

    public Vehicle(String make, String model) {
        this.make = make;
        this.model = model;
    }

    public void displayInfo() {
        System.out.println("Make: " + make);
        System.out.println("Model: " + model);
    }
}

// Derived class Car
class Car extends Vehicle {
    private int numberOfDoors;

    public Car(String make, String model, int numberOfDoors) {
        super(make, model);  // Call base class constructor
        this.numberOfDoors = numberOfDoors;
    }

    public void displayCarInfo() {
        displayInfo();  // Reuse base class method
        System.out.println("Number of doors: " + numberOfDoors);
    }
}

// Derived class Bike
class Bike extends Vehicle {
    private boolean hasCarrier;

    public Bike(String make, String model, boolean hasCarrier) {
        super(make, model);
        this.hasCarrier = hasCarrier;
    }

    public void displayBikeInfo() {
        displayInfo();
        System.out.println("Has carrier: " + (hasCarrier ? "Yes" : "No"));
    }
}

// Main class to demonstrate
public class VehicleDemo {
    public static void main(String[] args) {
        Car car = new Car("Toyota", "Camry", 4);
        Bike bike = new Bike("Honda", "CBR", true);

        System.out.println("Car details:");
        car.displayCarInfo();

        System.out.println("\nBike details:");
        bike.displayBikeInfo();
    }
}
