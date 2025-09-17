package kingdomsystem;

import java.util.*;

public final class KingdomConfig {
    private final String kingdomName;
    private final int foundingYear;
    private final String[] allowedStructureTypes;
    private final Map<String, Integer> resourceLimits;

    public KingdomConfig(String kingdomName, int foundingYear,
                         String[] allowedStructureTypes, Map<String, Integer> resourceLimits) {
        if (kingdomName == null || kingdomName.isBlank())
            throw new IllegalArgumentException("Kingdom name cannot be empty");
        if (foundingYear <= 0)
            throw new IllegalArgumentException("Founding year must be positive");
        if (allowedStructureTypes == null || allowedStructureTypes.length == 0)
            throw new IllegalArgumentException("Must provide structure types");
        if (resourceLimits == null || resourceLimits.isEmpty())
            throw new IllegalArgumentException("Resource limits cannot be empty");

        this.kingdomName = kingdomName;
        this.foundingYear = foundingYear;
        this.allowedStructureTypes = Arrays.copyOf(allowedStructureTypes, allowedStructureTypes.length);
        this.resourceLimits = new HashMap<>(resourceLimits);
    }

    public String getKingdomName() { return kingdomName; }
    public int getFoundingYear() { return foundingYear; }
    public String[] getAllowedStructureTypes() { return Arrays.copyOf(allowedStructureTypes, allowedStructureTypes.length); }
    public Map<String, Integer> getResourceLimits() { return new HashMap<>(resourceLimits); }

    // Factory methods
    public static KingdomConfig createDefaultKingdom() {
        return new KingdomConfig("Avaloria", 1020,
                new String[]{"WizardTower", "EnchantedCastle", "MysticLibrary", "DragonLair"},
                Map.of("Gold", 10000, "Mana", 5000, "Food", 2000));
    }

    public static KingdomConfig createFromTemplate(String type) {
        switch (type.toLowerCase()) {
            case "magic":
                return new KingdomConfig("Magica", 1200,
                        new String[]{"WizardTower", "MysticLibrary"},
                        Map.of("Mana", 8000, "Gold", 3000));
            case "war":
                return new KingdomConfig("Valorheim", 1100,
                        new String[]{"EnchantedCastle", "DragonLair"},
                        Map.of("Gold", 12000, "Food", 6000));
            default:
                return createDefaultKingdom();
        }
    }

    @Override
    public String toString() {
        return "KingdomConfig{" +
                "kingdomName='" + kingdomName + '\'' +
                ", foundingYear=" + foundingYear +
                ", allowedStructures=" + Arrays.toString(allowedStructureTypes) +
                ", resourceLimits=" + resourceLimits +
                '}';
    }
}
