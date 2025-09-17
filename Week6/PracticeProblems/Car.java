// File: Car.java
public class Car extends Vehicle {
    private int numberOfDoors;
    private String fuelType;
    private String transmissionType;

    public Car() {
        super();
        this.numberOfDoors = 4;
        this.fuelType = "Petrol";
        this.transmissionType = "Manual";
        System.out.println("Car default constructor called");
    }

    public Car(String brand, String model, int year, String engineType,
               int numberOfDoors, String fuelType, String transmissionType) {
        super(brand, model, year, engineType);
        this.numberOfDoors = numberOfDoors;
        this.fuelType = fuelType;
        this.transmissionType = transmissionType;
        System.out.println("Car parameterized constructor called");
    }

    @Override
    public void start() {
        super.start();
        System.out.println("Car-specific startup sequence initiated");
    }

    @Override
    public void displaySpecs() {
        super.displaySpecs();
        System.out.println("Car Specs:");
        System.out.println("Number of Doors: " + numberOfDoors);
        System.out.println("Fuel Type: " + fuelType);
        System.out.println("Transmission: " + transmissionType);
    }

    public void openTrunk() {
        System.out.println("Trunk opened");
    }

    public void playRadio() {
        System.out.println("Radio playing music");
    }

    public static void main(String[] args) {
        System.out.println("---- Testing Default Constructor ----");
        Car car1 = new Car();
        car1.displaySpecs();
        car1.start();
        car1.openTrunk();
        car1.playRadio();
        car1.stop();
        System.out.println(car1.getVehicleInfo());

        System.out.println("\n---- Testing Parameterized Constructor ----");
        Car car2 = new Car("Toyota", "Corolla", 2022, "V6", 4, "Diesel", "Automatic");
        car2.displaySpecs();
        car2.start();
        car2.playRadio();
        car2.stop();
        System.out.println(car2.getVehicleInfo());
    }
}
