class Phone {
    protected String brand;
    protected String model;

    public Phone() {
        System.out.println("Phone Default Constructor Called");
    }

    public Phone(String brand, String model) {
        this.brand = brand;
        this.model = model;
        System.out.println("Phone Parameterized Constructor Called");
    }
}

class SmartPhone extends Phone {
    private String operatingSystem;

    public SmartPhone() {
        super();
        System.out.println("SmartPhone Default Constructor Called");
    }

    public SmartPhone(String brand, String model, String operatingSystem) {
        super(brand, model);
        this.operatingSystem = operatingSystem;
        System.out.println("SmartPhone Parameterized Constructor Called");
    }

    public void displayDetails() {
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Operating System: " + operatingSystem);
    }
}

public class LabProblem2 {
    public static void main(String[] args) {
        System.out.println("Creating Phone with default constructor:");
        Phone p1 = new Phone();

        System.out.println("\nCreating Phone with parameters:");
        Phone p2 = new Phone("Nokia", "1100");

        System.out.println("\nCreating SmartPhone with default constructor:");
        SmartPhone sp1 = new SmartPhone();

        System.out.println("\nCreating SmartPhone with parameters:");
        SmartPhone sp2 = new SmartPhone("Apple", "iPhone 15", "iOS 18");
        sp2.displayDetails();
    }
}
