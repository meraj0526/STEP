package kingdomsystem;

public class Main {
    public static void main(String[] args) {
        KingdomConfig config = KingdomConfig.createDefaultKingdom();
        KingdomManager manager = new KingdomManager(config);

        // Create structures
        WizardTower tower = new WizardTower(new MagicalStructure("Arcane Spire", "North"));
        EnchantedCastle castle = new EnchantedCastle("Royal", 700, true, new MagicalStructure("Silver Keep", "East"));
        MysticLibrary library = new MysticLibrary(new MagicalStructure("Grand Library", "West"));
        DragonLair lair = new DragonLair("Fire Dragon", 5000, 50, new MagicalStructure("Inferno Cave", "South"));

        // Add to manager
        manager.addStructure(tower);
        manager.addStructure(castle);
        manager.addStructure(library);
        manager.addStructure(lair);

        // Demo
        System.out.println(config);
        System.out.println(tower);
        System.out.println(castle);
        System.out.println(library);
        System.out.println(lair);

        System.out.println("\nInteractions:");
        System.out.println(KingdomManager.canStructuresInteract(tower, library));
        System.out.println(KingdomManager.performMagicBattle(tower, lair));

        Object[] all = {tower, castle, library, lair};
        System.out.println("Total Kingdom Power = " + KingdomManager.calculateKingdomPower(all));
    }
}
