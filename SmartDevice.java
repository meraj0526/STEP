import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SmartDevice {

    // Read-only properties
    private final String deviceId;
    private final LocalDateTime manufacturingDate;
    private final String serialNumber;

    // Write-only properties (store hashes)
    private int hashedEncryptionKey;
    private int hashedAdminPassword;

    // Read-write properties
    private String deviceName;
    private boolean isEnabled;

    // Internal state
    private final LocalDateTime startupTime;

    // Constructor
    public SmartDevice(String deviceName) {
        this.deviceId = UUID.randomUUID().toString();
        this.manufacturingDate = LocalDateTime.now();
        this.serialNumber = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        this.startupTime = LocalDateTime.now();
        this.deviceName = deviceName;
        this.isEnabled = true;

        this.hashedEncryptionKey = 0;
        this.hashedAdminPassword = 0;
    }

    // Read-only getters
    public String getDeviceId() {
        return deviceId;
    }

    public LocalDateTime getManufacturingDate() {
        return manufacturingDate;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public long getUptime() {
        Duration duration = Duration.between(startupTime, LocalDateTime.now());
        return duration.toSeconds();
    }

    public int getDeviceAge() {
        Period period = Period.between(manufacturingDate.toLocalDate(), LocalDateTime.now().toLocalDate());
        return period.getYears();
    }

    // Write-only setters
    public void setEncryptionKey(String key) {
        if (!validateEncryptionKey(key)) {
            throw new IllegalArgumentException("Encryption key does not meet strength requirements.");
        }
        hashedEncryptionKey = key.hashCode();
    }

    public void setAdminPassword(String password) {
        if (!validateAdminPassword(password)) {
            throw new IllegalArgumentException("Admin password does not meet complexity requirements.");
        }
        hashedAdminPassword = password.hashCode();
    }

    // Validation methods (do not expose actual keys/passwords)
    public boolean validateEncryptionKey(String key) {
        if (key == null) return false;
        return key.length() >= 8 && key.matches(".*[A-Z].*") && key.matches(".*[0-9].*");
    }

    public boolean validateAdminPassword(String password) {
        if (password == null) return false;
        return password.length() >= 10 && password.matches(".*[A-Z].*")
                && password.matches(".*[a-z].*") && password.matches(".*\\d.*") && password.matches(".*[!@#$%^&*].*");
    }

    // Read-write getters and setters
    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        if (deviceName == null || deviceName.trim().isEmpty()) {
            throw new IllegalArgumentException("Device name cannot be null or empty.");
        }
        this.deviceName = deviceName;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        this.isEnabled = enabled;
    }

    // Utility method: returns property types and access levels
    public Map<String, String> getPropertyInfo() {
        Map<String, String> info = new HashMap<>();
        info.put("deviceId", "Read-only");
        info.put("manufacturingDate", "Read-only");
        info.put("serialNumber", "Read-only");
        info.put("encryptionKey", "Write-only");
        info.put("adminPassword", "Write-only");
        info.put("deviceName", "Read-Write");
        info.put("isEnabled", "Read-Write");
        info.put("uptime", "Read-only (computed)");
        info.put("deviceAge", "Read-only (computed)");
        return info;
    }

    // Resets write-only properties, keeps read-only intact
    public void resetDevice() {
        hashedEncryptionKey = 0;
        hashedAdminPassword = 0;
        deviceName = "Reset Device";
        isEnabled = false;
    }

    public static void main(String[] args) throws InterruptedException {
        SmartDevice device1 = new SmartDevice("Alpha");
        SmartDevice device2 = new SmartDevice("Beta");

        System.out.println("Device 1 ID: " + device1.getDeviceId());
        System.out.println("Device 1 Manufacture Date: " + device1.getManufacturingDate());
        System.out.println("Device 1 Serial Number: " + device1.getSerialNumber());

        System.out.println("Device 1 Uptime (secs): " + device1.getUptime());
        System.out.println("Device 1 Age (years): " + device1.getDeviceAge());

        // device1.setDeviceId("newId"); // Not allowed (no setter)

        device1.setEncryptionKey("StrongKey1");
        device1.setAdminPassword("ComplexPass!1");

        // No getters for encryptionKey or adminPassword:
        // device1.getEncryptionKey(); // No such method
        // device1.getAdminPassword(); // No such method

        System.out.println("Encryption key valid? " + device1.validateEncryptionKey("StrongKey1"));
        System.out.println("Admin password valid? " + device1.validateAdminPassword("ComplexPass!1"));

        System.out.println("Device 1 Name: " + device1.getDeviceName());
        device1.setDeviceName("AlphaX");
        System.out.println("Updated Device 1 Name: " + device1.getDeviceName());

        System.out.println("Device 1 is enabled? " + device1.isEnabled());
        device1.setEnabled(false);
        System.out.println("Device 1 enabled status after update: " + device1.isEnabled());

        // Demonstrate multiple devices have independent states
        System.out.println("\nDevice 2 Name: " + device2.getDeviceName());
        System.out.println("Device 2 is enabled? " + device2.isEnabled());

        // Reset device1 and show changes
        device1.resetDevice();
        System.out.println("\nAfter reset:");
        System.out.println("Device 1 Name: " + device1.getDeviceName());
        System.out.println("Device 1 is enabled? " + device1.isEnabled());

        // Property info map
        System.out.println("\nProperty info:");
        device1.getPropertyInfo().forEach((prop, access) -> System.out.println(prop + ": " + access));
    }
}
