import java.util.*;

public class VirtualPetSimulator {
    public static void main(String[] args) {
        PetDaycare daycare = new PetDaycare();
        daycare.runSimulation(5);
    }
}

class VirtualPet {
    static final String[] EVOLUTION_STAGES = {"Egg", "Baby", "Child", "Teen", "Adult", "Elder"};
    static int totalPetsCreated = 0;

    final String petId;
    String petName;
    String species;
    int age;
    int happiness;
    int health;
    int evolutionStageIndex;
    boolean isGhost;

    public VirtualPet(String petName, String species, int age, int happiness, int health, int evolutionStageIndex) {
        this.petId = generatePetId();
        this.petName = petName;
        this.species = species;
        this.age = age;
        this.happiness = happiness;
        this.health = health;
        this.evolutionStageIndex = evolutionStageIndex;
        this.isGhost = false;
        totalPetsCreated++;
    }

    public VirtualPet(String petName, String species) {
        this(petName, species, 2, 50, 50, 2);
    }

    public VirtualPet(String petName) {
        this(petName, getRandomSpecies(), 1, 50, 50, 1);
    }

    public VirtualPet() {
        this("Unknown", getRandomSpecies(), 0, 0, 0, 0);
    }

    public static String generatePetId() {
        return UUID.randomUUID().toString();
    }

    private static String getRandomSpecies() {
        String[] speciesList = {"Dragon", "Cat", "Dog", "Alien", "Rabbit"};
        Random rand = new Random();
        return speciesList[rand.nextInt(speciesList.length)];
    }

    public void evolvePet() {
        if (isGhost) {
            System.out.println(petName + " is a ghost and cannot evolve.");
            return;
        }
        if (evolutionStageIndex < EVOLUTION_STAGES.length - 1 && health > 20 && happiness > 20) {
            evolutionStageIndex++;
            System.out.println(petName + " evolved into: " + getPetStatus());
        } else {
            System.out.println(petName + " did not evolve.");
        }
    }

    public void simulateDay() {
        if (isGhost) {
            System.out.println(petName + " is haunting the daycare...");
            return;
        }
        age++;
        Random rand = new Random();
        happiness += rand.nextInt(5) - 2;
        health += rand.nextInt(5) - 2;
        happiness = Math.max(0, Math.min(100, happiness));
        health = Math.max(0, Math.min(100, health));
        if (health <= 0) {
            becomeGhost();
        } else {
            evolvePet();
        }
    }

    public void feedPet() {
        if (!isGhost) {
            health += 10;
            System.out.println(petName + " was fed.");
        }
    }

    public void playWithPet() {
        if (!isGhost) {
            happiness += 10;
            System.out.println(petName + " played happily.");
        }
    }

    public void healPet() {
        if (!isGhost) {
            health += 15;
            System.out.println(petName + " was healed.");
        }
    }

    private void becomeGhost() {
        isGhost = true;
        species = "Ghost";
        System.out.println(petName + " has died and become a ghost...");
    }

    public String getPetStatus() {
        return EVOLUTION_STAGES[Math.min(evolutionStageIndex, EVOLUTION_STAGES.length - 1)];
    }

    public void printStatus() {
        System.out.println("[" + petId + "] " + petName + " (" + species + ")");
        System.out.println("  Age: " + age + ", Health: " + health + ", Happiness: " + happiness);
        System.out.println("  Status: " + (isGhost ? "Ghost" : getPetStatus()));
    }
}

class PetDaycare {
    private List<VirtualPet> pets = new ArrayList<>();

    public PetDaycare() {
        pets.add(new VirtualPet());
        pets.add(new VirtualPet("Fluffy"));
        pets.add(new VirtualPet("Sparky", "Dragon"));
        pets.add(new VirtualPet("Buddy", "Dog", 3, 60, 60, 3));
    }

    public void runSimulation(int days) {
        for (int day = 1; day <= days; day++) {
            System.out.println("\n=== Day " + day + " ===");
            for (VirtualPet pet : pets) {
                pet.simulateDay();
                pet.feedPet();
                pet.playWithPet();
                pet.healPet();
                pet.printStatus();
            }
        }
        System.out.println("\nTotal pets created: " + VirtualPet.totalPetsCreated);
    }
}
