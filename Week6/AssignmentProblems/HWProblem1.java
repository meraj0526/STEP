class Light {
    protected String type;
    protected int power;

    public Light() {
        this("Generic Light", 0);
        System.out.println("Light Default Constructor Called");
    }

    public Light(String type) {
        this(type, 0);
        System.out.println("Light Single-Arg Constructor Called");
    }

    public Light(String type, int power) {
        this.type = type;
        this.power = power;
        System.out.println("Light Two-Arg Constructor Called");
    }

    public void displayLight() {
        System.out.println("Light Type: " + type);
        System.out.println("Power: " + power + "W");
    }
}

class LED extends Light {
    private String color;

    public LED() {
        this("LED Light", 10, "White");
        System.out.println("LED Default Constructor Called");
    }

    public LED(String type, int power) {
        super(type, power);
        System.out.println("LED Two-Arg Constructor Called");
    }

    public LED(String type, int power, String color) {
        super(type, power);
        this.color = color;
        System.out.println("LED Three-Arg Constructor Called");
    }

    public void displayLED() {
        displayLight();
        System.out.println("Color: " + color);
    }
}

public class HWProblem1 {
    public static void main(String[] args) {
        System.out.println("Creating Light objects:");
        Light l1 = new Light();
        Light l2 = new Light("Tube Light");
        Light l3 = new Light("Halogen", 100);

        System.out.println("\nCreating LED objects:");
        LED led1 = new LED();
        led1.displayLED();

        System.out.println();
        LED led2 = new LED("LED Bulb", 15);
        led2.displayLED();

        System.out.println();
        LED led3 = new LED("LED Strip", 20, "Blue");
        led3.displayLED();
    }
}
