package kingdomsystem;

public class DragonLair {
    private final String dragonType;
    private long treasureValue;
    private int territorialRadius;
    private final MagicalStructure core;

    public DragonLair(String dragonType, long treasure, int radius, MagicalStructure core) {
        this.dragonType = dragonType;
        this.treasureValue = treasure;
        this.territorialRadius = radius;
        this.core = core;
    }

    public String getDragonType() { return dragonType; }
    public long getTreasureValue() { return treasureValue; }
    public void setTreasureValue(long treasureValue) { this.treasureValue = treasureValue; }
    public int getTerritorialRadius() { return territorialRadius; }
    public void setTerritorialRadius(int territorialRadius) { this.territorialRadius = territorialRadius; }
    public MagicalStructure getCore() { return core; }

    @Override
    public String toString() {
        return "DragonLair{" +
                "dragonType='" + dragonType + '\'' +
                ", treasure=" + treasureValue +
                ", radius=" + territorialRadius +
                ", core=" + core +
                '}';
    }
}
