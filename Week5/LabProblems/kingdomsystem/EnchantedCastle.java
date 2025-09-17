package kingdomsystem;

public class EnchantedCastle {
    private final String castleType;
    private int defenseRating;
    private boolean hasDrawbridge;
    private final MagicalStructure core;

    public EnchantedCastle(String castleType, int defense, boolean drawbridge, MagicalStructure core) {
        this.castleType = castleType;
        this.defenseRating = defense;
        this.hasDrawbridge = drawbridge;
        this.core = core;
    }

    public String getCastleType() { return castleType; }
    public int getDefenseRating() { return defenseRating; }
    public void setDefenseRating(int defenseRating) { this.defenseRating = defenseRating; }
    public boolean hasDrawbridge() { return hasDrawbridge; }
    public void setHasDrawbridge(boolean hasDrawbridge) { this.hasDrawbridge = hasDrawbridge; }
    public MagicalStructure getCore() { return core; }

    @Override
    public String toString() {
        return "EnchantedCastle{" +
                "type='" + castleType + '\'' +
                ", defense=" + defenseRating +
                ", drawbridge=" + hasDrawbridge +
                ", core=" + core +
                '}';
    }
}
