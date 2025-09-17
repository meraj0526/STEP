package kingdomsystem;

import java.util.*;

public class WizardTower {
    private final int maxSpellCapacity;
    private List<String> knownSpells;
    private String currentWizard;
    private final MagicalStructure core;

    // Constructors
    public WizardTower(MagicalStructure core) {
        this(core, 10, new ArrayList<>(), "None");
    }

    public WizardTower(MagicalStructure core, int maxCapacity, List<String> spells, String wizard) {
        this.core = Objects.requireNonNull(core);
        this.maxSpellCapacity = maxCapacity;
        this.knownSpells = new ArrayList<>(spells);
        this.currentWizard = wizard;
    }

    public int getMaxSpellCapacity() { return maxSpellCapacity; }
    public List<String> getKnownSpells() { return new ArrayList<>(knownSpells); }
    public void addSpell(String spell) {
        if (knownSpells.size() < maxSpellCapacity) knownSpells.add(spell);
    }
    public String getCurrentWizard() { return currentWizard; }
    public void setCurrentWizard(String wizard) { this.currentWizard = wizard; }
    public MagicalStructure getCore() { return core; }

    @Override
    public String toString() {
        return "WizardTower{" +
                "maxCapacity=" + maxSpellCapacity +
                ", spells=" + knownSpells +
                ", wizard='" + currentWizard + '\'' +
                ", core=" + core +
                '}';
    }
}
