// File: Dog.java
public class Dog extends Mammal {
    private String breed;
    private boolean isDomesticated;
    private int loyaltyLevel;
    private String favoriteActivity;

    public Dog() {
        super("Dog", "Domestic", 15, false, "Brown", 60);
        this.breed = "Generic";
        this.isDomesticated = true;
        this.loyaltyLevel = 8;
        this.favoriteActivity = "Playing";
        System.out.println("Dog constructor: Creating " + breed + " dog");
    }

    public Dog(String species, String habitat, int lifespan, boolean isWildlife,
               String furColor, int gestationPeriod, String breed, boolean isDomesticated,
               int loyaltyLevel, String favoriteActivity) {
        super(species, habitat, lifespan, isWildlife, furColor, gestationPeriod);
        this.breed = breed;
        this.isDomesticated = isDomesticated;
        this.loyaltyLevel = loyaltyLevel;
        this.favoriteActivity = favoriteActivity;
        System.out.println("Dog constructor: Creating " + breed + " dog");
    }

    public Dog(Dog other) {
        this(other.species, other.habitat, other.lifespan, other.isWildlife,
             other.furColor, other.gestationPeriod, other.breed, other.isDomesticated,
             other.loyaltyLevel, other.favoriteActivity);
    }

    @Override
    public void eat() {
        super.eat();
        System.out.println("Wagging tail while eating");
    }

    @Override
    public void move() {
        System.out.println("Dog is running and playing");
    }

    @Override
    public void sleep() {
        System.out.println("Dog is sleeping in doghouse");
    }

    public void bark() {
        System.out.println("Woof! Woof!");
    }

    public void fetch() {
        System.out.println("Dog is fetching the ball");
    }

    public void showLoyalty() {
        System.out.println("Loyalty Level: " + loyaltyLevel + "/10");
    }

    public void demonstrateInheritance() {
        eat();
        sleep();
        move();
        nurse();
        regulateTemperature();
        bark();
        fetch();
        showLoyalty();
    }

    public static void main(String[] args) {
        System.out.println("---- Creating Dog with default constructor ----");
        Dog dog1 = new Dog();
        dog1.demonstrateInheritance();
        System.out.println("\n---- Creating Dog with parameterized constructor ----");
        Dog dog2 = new Dog("Dog", "Domestic", 12, false, "Black", 58,
                           "Labrador", true, 10, "Swimming");
        dog2.demonstrateInheritance();
        System.out.println("\n---- Creating Dog using copy constructor ----");
        Dog dog3 = new Dog(dog2);
        dog3.demonstrateInheritance();

        System.out.println("\n---- instanceof Tests ----");
        System.out.println("dog1 instanceof Dog: " + (dog1 instanceof Dog));
        System.out.println("dog1 instanceof Mammal: " + (dog1 instanceof Mammal));
        System.out.println("dog1 instanceof Animal: " + (dog1 instanceof Animal));
    }
}
