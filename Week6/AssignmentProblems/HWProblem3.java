class Game {
    protected String name;
    protected int players;

    public Game(String name, int players) {
        this.name = name;
        this.players = players;
    }

    @Override
    public String toString() {
        return "Game{name='" + name + "', players=" + players + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Game)) return false;
        Game g = (Game) obj;
        return this.name.equals(g.name) && this.players == g.players;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + players;
    }
}

class CardGame extends Game {
    private String deckType;

    public CardGame(String name, int players, String deckType) {
        super(name, players);
        this.deckType = deckType;
    }

    @Override
    public String toString() {
        return super.toString() + ", deckType='" + deckType + "'";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CardGame)) return false;
        if (!super.equals(obj)) return false;
        CardGame cg = (CardGame) obj;
        return this.deckType.equals(cg.deckType);
    }

    @Override
    public int hashCode() {
        return super.hashCode() + deckType.hashCode();
    }
}

public class HWProblem3 {
    public static void main(String[] args) {
        Game g1 = new Game("Chess", 2);
        Game g2 = new Game("Chess", 2);
        CardGame c1 = new CardGame("Poker", 4, "Standard 52");
        CardGame c2 = new CardGame("Poker", 4, "Standard 52");
        CardGame c3 = new CardGame("Rummy", 4, "Standard 52");

        System.out.println(g1.toString());
        System.out.println(g2.toString());
        System.out.println("g1 equals g2: " + g1.equals(g2));

        System.out.println(c1.toString());
        System.out.println(c2.toString());
        System.out.println("c1 equals c2: " + c1.equals(c2));
        System.out.println("c1 equals c3: " + c1.equals(c3));
    }
}
