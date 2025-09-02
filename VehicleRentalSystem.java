public class VehicleRentalSystem {
    public static void main(String[] args) {
        Vehicle.setCompanyName("CityRide Rentals");

        Vehicle v1 = new Vehicle("Toyota", "Corolla", 50);
        Vehicle v2 = new Vehicle("Honda", "Civic", 60);
        Vehicle v3 = new Vehicle("Ford", "Focus", 55);

        System.out.println("\n--- Initial Vehicle Info ---");
        v1.displayVehicleInfo();
        v2.displayVehicleInfo();
        v3.displayVehicleInfo();

        v1.rentVehicle(3);
        v2.rentVehicle(5);
        v1.returnVehicle();
        v1.rentVehicle(2);

        System.out.println("\n--- Updated Vehicle Info ---");
        v1.displayVehicleInfo();
        v2.displayVehicleInfo();
        v3.displayVehicleInfo();

        System.out.println("\n--- Company Stats ---");
        Vehicle.displayCompanyStats();
    }
}

class Vehicle {
    private String vehicleId;
    private String brand;
    private String model;
    private double rentPerDay;
    private boolean isAvailable;
    private int totalRentalDays;

    private static int totalVehicles = 0;
    private static double totalRevenue = 0;
    private static String companyName = "Default Rentals";
    private static int companyRentalDays = 0;

    public Vehicle(String brand, String model, double rentPerDay) {
        this.brand = brand;
        this.model = model;
        this.rentPerDay = rentPerDay;
        this.vehicleId = generateVehicleId();
        this.isAvailable = true;
        totalVehicles++;
    }

    public void rentVehicle(int days) {
        if (isAvailable) {
            double rent = calculateRent(days);
            isAvailable = false;
            totalRentalDays += days;
            companyRentalDays += days;
            System.out.println(brand + " " + model + " rented for " + days + " days. Rent: " + rent);
        } else {
            System.out.println(brand + " " + model + " is not available.");
        }
    }

    public void returnVehicle() {
        if (!isAvailable) {
            isAvailable = true;
            System.out.println(brand + " " + model + " returned and available now.");
        }
    }

    public double calculateRent(int days) {
        double rent = days * rentPerDay;
        totalRevenue += rent;
        return rent;
    }

    public void displayVehicleInfo() {
        System.out.println("Vehicle ID   : " + vehicleId);
        System.out.println("Brand        : " + brand);
        System.out.println("Model        : " + model);
        System.out.println("Rent/Day     : " + rentPerDay);
        System.out.println("Available    : " + (isAvailable ? "Yes" : "No"));
        System.out.println("Total Rented : " + totalRentalDays + " days");
        System.out.println("-------------------------------");
    }

    private static String generateVehicleId() {
        return String.format("V%03d", totalVehicles + 1);
    }

    public static void setCompanyName(String name) {
        companyName = name;
    }

    public static double getTotalRevenue() {
        return totalRevenue;
    }

    public static double getAverageRentPerDay() {
        return companyRentalDays == 0 ? 0 : totalRevenue / companyRentalDays;
    }

    public static void displayCompanyStats() {
        System.out.println("Company Name     : " + companyName);
        System.out.println("Total Vehicles   : " + totalVehicles);
        System.out.println("Total Revenue    : " + totalRevenue);
        System.out.println("Avg Rent/Day     : " + getAverageRentPerDay());
        System.out.println("Total RentalDays : " + companyRentalDays);
    }
}
