import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

enum PersonalityType {
    BRAVE, CUNNING, MYSTERIOUS, FUNNY, DARK
}

abstract class StoryCharacter implements Serializable {
    static final String[] STORY_GENRES = {"Fantasy", "Sci-Fi", "Mystery", "Romance", "Adventure"};
    final String characterId;
    final String backstory;
    final PersonalityType corePersonality;

    String currentMood;
    String currentLocation;
    Map<String, Integer> relationshipMap = new HashMap<>();
    Set<String> skillSet = new HashSet<>();
    List<String> memoryLog = new ArrayList<>();

    public StoryCharacter(String backstory, PersonalityType personality) {
        this.characterId = UUID.randomUUID().toString();
        this.backstory = backstory;
        this.corePersonality = personality;
        this.currentMood = "Neutral";
        this.currentLocation = "Unknown";
    }

    public abstract void speak();

    public void becomeSelfAware() {
        System.out.println(getClass().getSimpleName() + " [" + characterId + "]:");
        System.out.println("  I know I'm part of a program. My core personality is final: " + corePersonality);
        System.out.println("  I tried to change it once... It didn't go well.");
    }

    public void addSkill(String skill) {
        skillSet.add(skill);
    }

    public void remember(String event) {
        memoryLog.add(event);
    }

    public void relateTo(String otherId, int strength) {
        relationshipMap.put(otherId, strength);
    }
}

class Hero extends StoryCharacter {
    final String origin;

    public Hero(String origin) {
        super("Raised in the light of justice from " + origin, PersonalityType.BRAVE);
        this.origin = origin;
        addSkill("Swordsmanship");
    }

    public void speak() {
        System.out.println("Hero: I fight for justice! My origin is " + origin);
    }
}

class Villain extends StoryCharacter {
    final String evilMotivation;

    public Villain(String evilMotivation) {
        super("Twisted by " + evilMotivation, PersonalityType.DARK);
        this.evilMotivation = evilMotivation;
        addSkill("Scheming");
    }

    public void speak() {
        System.out.println("Villain: My motivation is eternal: " + evilMotivation);
    }
}

class MysteriousStranger extends StoryCharacter {
    String hiddenTruth;

    public MysteriousStranger() {
        super("Unknown even to themselves...", PersonalityType.MYSTERIOUS);
        hiddenTruth = "Secret Royal Bloodline";
        addSkill("Stealth");
    }

    public void reveal() {
        System.out.println("Stranger reveals truth: " + hiddenTruth);
    }

    public void speak() {
        System.out.println("Stranger: I walk unseen, my past is hidden.");
    }
}

class ComicRelief extends StoryCharacter {
    final String humorStyle;

    public ComicRelief(String humorStyle) {
        super("Brings laughter wherever they go", PersonalityType.FUNNY);
        this.humorStyle = humorStyle;
        addSkill("Distraction");
    }

    public void speak() {
        System.out.println("ComicRelief (" + humorStyle + "): Knock knock! Who’s there? Meta-awareness!");
    }
}

class CharacterFactory {
    public static StoryCharacter fromPrompt(String prompt) {
        if (prompt.contains("justice")) return new Hero("Capital City");
        if (prompt.contains("revenge")) return new Villain("Betrayal");
        if (prompt.contains("mystery")) return new MysteriousStranger();
        return new ComicRelief("Slapstick");
    }

    public static StoryCharacter randomCharacter() {
        int pick = ThreadLocalRandom.current().nextInt(4);
        switch (pick) {
            case 0: return new Hero("Valley");
            case 1: return new Villain("Greed");
            case 2: return new MysteriousStranger();
            default: return new ComicRelief("Deadpan");
        }
    }

    public static StoryCharacter fusion(StoryCharacter a, StoryCharacter b) {
        String fusionBackstory = a.backstory + " + " + b.backstory;
        return new StoryCharacter(fusionBackstory, PersonalityType.MYSTERIOUS) {
            public void speak() {
                System.out.println("Fusion of " + a.getClass().getSimpleName() + " and " + b.getClass().getSimpleName());
            }
        };
    }
}

class StoryEngine {
    static Set<String> achievements = new HashSet<>();

    public static void generateStoryArc(List<StoryCharacter> characters) {
        System.out.println("\n--- Generating Story Arc ---");
        Random rand = new Random();
        for (StoryCharacter c : characters) {
            c.speak();
            c.remember("Started arc at " + new Date());
            c.currentLocation = StoryCharacter.STORY_GENRES[rand.nextInt(StoryCharacter.STORY_GENRES.length)] + " Realm";
        }

        if (characters.stream().anyMatch(c -> c instanceof Hero) &&
            characters.stream().anyMatch(c -> c instanceof Villain)) {
            System.out.println("Plot: Epic battle between good and evil!");
        } else if (characters.stream().allMatch(c -> c instanceof ComicRelief)) {
            System.out.println("Plot: Chaotic comedy ensues.");
        } else {
            System.out.println("Plot: Mysterious journey begins...");
        }

        characters.forEach(c -> c.becomeSelfAware());
    }

    public static void resolveConflict(StoryCharacter a, StoryCharacter b) {
        System.out.println("\n--- Conflict ---");
        if (a instanceof Hero && b instanceof Villain) {
            System.out.println("Hero faces the Villain in battle!");
        } else if (a instanceof MysteriousStranger || b instanceof MysteriousStranger) {
            System.out.println("Mysterious tension. No clear outcome.");
        } else {
            System.out.println("Characters talk it out... kind of.");
        }
        a.remember("Met " + b.characterId);
        b.remember("Met " + a.characterId);
    }

    public static void showAchievements() {
        System.out.println("\n--- Achievements ---");
        achievements.forEach(System.out::println);
    }

    public static void saveStory(List<StoryCharacter> characters, String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(characters);
            achievements.add("Story Saved Successfully");
        } catch (IOException e) {
            System.out.println("Failed to save story.");
        }
    }

    @SuppressWarnings("unchecked")
    public static List<StoryCharacter> loadStory(String fileName) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            List<StoryCharacter> list = (List<StoryCharacter>) in.readObject();
            achievements.add("Story Loaded Successfully");
            return list;
        } catch (Exception e) {
            System.out.println("Failed to load story.");
            return new ArrayList<>();
        }
    }
}

public class InteractiveStoryGenerator {
    public static void main(String[] args) {
        List<StoryCharacter> characters = new ArrayList<>();

        characters.add(CharacterFactory.fromPrompt("A tale of justice and light"));
        characters.add(CharacterFactory.randomCharacter());
        characters.add(CharacterFactory.fusion(
                new Hero("Mountain Village"),
                new Villain("Hatred")
        ));

        StoryEngine.generateStoryArc(characters);

        if (characters.size() >= 2) {
            StoryEngine.resolveConflict(characters.get(0), characters.get(1));
        }

        StoryEngine.saveStory(characters, "story.ser");
        StoryEngine.showAchievements();

        List<StoryCharacter> loaded = StoryEngine.loadStory("story.ser");
        if (!loaded.isEmpty()) {
            System.out.println("\n--- Loaded Characters ---");
            loaded.forEach(c -> System.out.println(c.getClass().getSimpleName() + ": " + c.backstory));
        }
    }
}
