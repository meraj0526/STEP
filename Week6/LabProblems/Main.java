class Fruit {
    protected String color;
    protected String taste;

    public Fruit(String color, String taste) {
        this.color = color;
        this.taste = taste;
    }

    public void displayFruitInfo() {
        System.out.println("Fruit Color: " + color);
        System.out.println("Fruit Taste: " + taste);
    }
}

class Apple extends Fruit {
    private String variety;

    public Apple(String color, String taste, String variety) {
        super(color, taste);
        this.variety = variety;
    }

    public void displayAppleInfo() {
        displayFruitInfo();
        System.out.println("Apple Variety: " + variety);
    }
}

public class Main {
    public static void main(String[] args) {
        Apple myApple = new Apple("Red", "Sweet", "Fuji");
        myApple.displayAppleInfo();
    }
}
