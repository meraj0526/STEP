import java.util.ArrayList;
import java.util.List;

class Vehicle {
    String vehicleId, brand, model, fuelType, currentStatus;
    int year;
    double mileage;
    static int totalVehicles = 0;
    static double fleetValue = 0;
    static double totalFuelConsumption = 0;
    static String companyName = "TransportCo";

    Vehicle(String vehicleId, String brand, String model, int year, double mileage, String fuelType, String currentStatus, double value) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.fuelType = fuelType;
        this.currentStatus = currentStatus;
        totalVehicles++;
        fleetValue += value;
    }

    void assignDriver(Driver driver) {
        driver.assignedVehicle = this;
    }

    void scheduleMaintenance() {
        currentStatus = "Under Maintenance";
    }

    double calculateRunningCost(double fuelPricePerLiter, double fuelConsumed) {
        totalFuelConsumption += fuelConsumed;
        return fuelConsumed * fuelPricePerLiter;
    }

    void updateMileage(double distance) {
        mileage += distance;
    }

    boolean checkServiceDue(double serviceInterval) {
        return mileage >= serviceInterval;
    }

    static double calculateTotalMaintenanceCost(double costPerVehicle) {
        return totalVehicles * costPerVehicle;
    }

    static List<Vehicle> getVehiclesByType(List<Vehicle> fleet, String type) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle v : fleet) {
            if (v.getClass().getSimpleName().equalsIgnoreCase(type)) result.add(v);
        }
        return result;
    }

    static double getFleetUtilization() {
        return (totalVehicles == 0) ? 0 : (totalFuelConsumption / totalVehicles);
    }
}

class Car extends Vehicle {
    int doors;

    Car(String vehicleId, String brand, String model, int year, double mileage, String fuelType, String currentStatus, double value, int doors) {
        super(vehicleId, brand, model, year, mileage, fuelType, currentStatus, value);
        this.doors = doors;
    }
}

class Bus extends Vehicle {
    int seatingCapacity;

    Bus(String vehicleId, String brand, String model, int year, double mileage, String fuelType, String currentStatus, double value, int seatingCapacity) {
        super(vehicleId, brand, model, year, mileage, fuelType, currentStatus, value);
        this.seatingCapacity = seatingCapacity;
    }
}

class Truck extends Vehicle {
    double loadCapacity;

    Truck(String vehicleId, String brand, String model, int year, double mileage, String fuelType, String currentStatus, double value, double loadCapacity) {
        super(vehicleId, brand, model, year, mileage, fuelType, currentStatus, value);
        this.loadCapacity = loadCapacity;
    }
}

class Driver {
    String driverId, driverName, licenseType;
    Vehicle assignedVehicle;
    int totalTrips;

    Driver(String driverId, String driverName, String licenseType) {
        this.driverId = driverId;
        this.driverName = driverName;
        this.licenseType = licenseType;
        this.totalTrips = 0;
    }

    void incrementTrips() {
        totalTrips++;
    }
}

public class Assignment7_Meraj {
    public static void main(String[] args) {
        List<Vehicle> fleet = new ArrayList<>();
        Car car1 = new Car("C001", "Toyota", "Corolla", 2020, 12000, "Petrol", "Available", 20000, 4);
        Bus bus1 = new Bus("B001", "Volvo", "X100", 2019, 50000, "Diesel", "Available", 80000, 50);
        Truck truck1 = new Truck("T001", "Mercedes", "Actros", 2021, 30000, "Diesel", "Available", 120000, 15);

        fleet.add(car1);
        fleet.add(bus1);
        fleet.add(truck1);

        Driver d1 = new Driver("D001", "John", "B");
        Driver d2 = new Driver("D002", "Alice", "C");

        car1.assignDriver(d1);
        bus1.assignDriver(d2);

        car1.updateMileage(500);
        bus1.updateMileage(1000);
        truck1.updateMileage(2000);

        System.out.println("Fleet Utilization: " + Vehicle.getFleetUtilization());
        System.out.println("Total Maintenance Cost: " + Vehicle.calculateTotalMaintenanceCost(500));

        List<Vehicle> buses = Vehicle.getVehiclesByType(fleet, "Bus");
        System.out.println("Total Buses: " + buses.size());

        for (Vehicle v : fleet) {
            System.out.println(v.vehicleId + " - " + v.currentStatus);
        }
    }
}
