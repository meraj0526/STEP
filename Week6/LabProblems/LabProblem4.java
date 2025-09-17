class Color {
    protected String name;

    public Color(String name) {
        this.name = name;
        System.out.println("Color Constructor Called");
    }

    public void displayColor() {
        System.out.println("Color: " + name);
    }
}

class PrimaryColor extends Color {
    protected String intensity;

    public PrimaryColor(String name, String intensity) {
        super(name);
        this.intensity = intensity;
        System.out.println("PrimaryColor Constructor Called");
    }

    public void displayPrimaryColor() {
        displayColor();
        System.out.println("Intensity: " + intensity);
    }
}

class RedColor extends PrimaryColor {
    private String shade;

    public RedColor(String name, String intensity, String shade) {
        super(name, intensity);
        this.shade = shade;
        System.out.println("RedColor Constructor Called");
    }

    public void displayRedColor() {
        displayPrimaryColor();
        System.out.println("Shade: " + shade);
    }
}

public class LabProblem4 {
    public static void main(String[] args) {
        RedColor red = new RedColor("Red", "High", "Crimson");
        System.out.println("\nDisplaying Full Hierarchy:");
        red.displayRedColor();
    }
}
