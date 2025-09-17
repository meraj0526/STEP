package kingdomsystem;

import java.util.Objects;
import java.util.UUID;

public class MagicalStructure {
    // Immutable identity
    private final String structureId;
    private final long constructionTimestamp;
    private final String structureName;
    private final String location;

    // Controlled state
    private int magicPower;
    private boolean isActive;
    private String currentMaintainer;

    // Constants
    static final int MIN_MAGIC_POWER = 0;
    static final int MAX_MAGIC_POWER = 1000;
    public static final String MAGIC_SYSTEM_VERSION = "3.0";

    // Constructors
    public MagicalStructure(String name, String location) {
        this(name, location, 100, true);
    }

    public MagicalStructure(String name, String location, int power) {
        this(name, location, power, true);
    }

    public MagicalStructure(String name, String location, int power, boolean active) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be empty");
        if (location == null || location.isBlank()) throw new IllegalArgumentException("Location cannot be empty");
        if (power < MIN_MAGIC_POWER || power > MAX_MAGIC_POWER) throw new IllegalArgumentException("Invalid power");

        this.structureId = UUID.randomUUID().toString();
        this.constructionTimestamp = System.currentTimeMillis();
        this.structureName = name;
        this.location = location;
        this.magicPower = power;
        this.isActive = active;
        this.currentMaintainer = "Unknown";
    }

    // Getters & Setters
    public String getStructureId() { return structureId; }
    public String getStructureName() { return structureName; }
    public String getLocation() { return location; }
    public int getMagicPower() { return magicPower; }
    public void setMagicPower(int magicPower) {
        if (magicPower >= MIN_MAGIC_POWER && magicPower <= MAX_MAGIC_POWER)
            this.magicPower = magicPower;
    }
    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
    public String getCurrentMaintainer() { return currentMaintainer; }
    public void setCurrentMaintainer(String currentMaintainer) { this.currentMaintainer = currentMaintainer; }

    @Override
    public String toString() {
        return "MagicalStructure{" +
                "id='" + structureId + '\'' +
                ", name='" + structureName + '\'' +
                ", location='" + location + '\'' +
                ", power=" + magicPower +
                ", active=" + isActive +
                ", maintainer='" + currentMaintainer + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MagicalStructure)) return false;
        MagicalStructure that = (MagicalStructure) o;
        return structureId.equals(that.structureId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(structureId);
    }
}
