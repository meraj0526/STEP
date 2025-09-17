class Tool {
    private String type;
    protected String material;
    public String brand;

    public Tool(String type, String material, String brand) {
        this.type = type;
        this.material = material;
        this.brand = brand;
    }

    public String getType() {
        return type;  
    }
}

class Hammer extends Tool {
    private double weight;

    public Hammer(String type, String material, String brand, double weight) {
        super(type, material, brand);
        this.weight = weight;
    }

    public void displayDetails() {
        System.out.println("Type: " + getType()); 
        System.out.println("Material: " + material); 
        System.out.println("Brand: " + brand);       
        System.out.println("Weight: " + weight + " kg");
    }
}

public class HWProblem2 {
    public static void main(String[] args) {
        Hammer h = new Hammer("Hammer", "Steel", "Stanley", 1.5);
        h.displayDetails();
    }
}
