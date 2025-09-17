package kingdomsystem;

import java.util.*;

public class KingdomManager {
    private final List<Object> structures;
    private final KingdomConfig config;

    public KingdomManager(KingdomConfig config) {
        this.config = config;
        this.structures = new ArrayList<>();
    }

    public void addStructure(Object structure) {
        structures.add(structure);
    }

    public static boolean canStructuresInteract(Object s1, Object s2) {
        return (s1 instanceof WizardTower && s2 instanceof MysticLibrary) ||
               (s1 instanceof EnchantedCastle && s2 instanceof DragonLair);
    }

    public static String performMagicBattle(Object attacker, Object defender) {
        if (attacker instanceof WizardTower && defender instanceof DragonLair) {
            return "Wizard Tower launches spells against the Dragon Lair!";
        } else if (attacker instanceof DragonLair && defender instanceof EnchantedCastle) {
            return "Dragon attacks the Castle with fiery breath!";
        }
        return "No significant magical battle occurred.";
    }

    public static int calculateKingdomPower(Object[] structures) {
        int total = 0;
        for (Object s : structures) {
            if (s instanceof WizardTower) total += 200;
            else if (s instanceof EnchantedCastle) total += 500;
            else if (s instanceof MysticLibrary) total += 300;
            else if (s instanceof DragonLair) total += 800;
        }
        return total;
    }

    private String determineStructureCategory(Object structure) {
        if (structure instanceof WizardTower) return "Wizardry";
        if (structure instanceof EnchantedCastle) return "Defense";
        if (structure instanceof MysticLibrary) return "Knowledge";
        if (structure instanceof DragonLair) return "Power";
        return "Unknown";
    }
}
