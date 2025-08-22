// Class representing a Car
class Car {
    // Fields (attributes)
    String make;
    String model;
    int year;
    String color;

    // Constructor to initialize a Car object
    Car(String make, String model, int year, String color) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
    }

    // Method to display car details
    void displayInfo() {
        System.out.println("Car Info:");
        System.out.println("Make: " + make);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Color: " + color);
    }

    // Method to simulate starting the car
    void start() {
        System.out.println(make + " " + model + " is starting...");
    }

    // Method to simulate stopping the car
    void stop() {
        System.out.println(make + " " + model + " is stopping...");
    }
}

public class Main {
    public static void main(String[] args) {
        // Creating objects (instances) of Car
        Car car1 = new Car("Toyota", "Corolla", 2022, "Red");
        Car car2 = new Car("Honda", "Civic", 2020, "Blue");

        // Using the methods of the Car objects
        car1.displayInfo();
        car1.start();
        car1.stop();

        System.out.println();

        car2.displayInfo();
        car2.start();
        car2.stop();
    }
}
