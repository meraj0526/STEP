package kingdomsystem;

import java.util.*;

public class MysticLibrary {
    private final Map<String, String> bookCollection;
    private int knowledgeLevel;
    private final MagicalStructure core;

    public MysticLibrary(MagicalStructure core) {
        this(core, new HashMap<>(), 50);
    }

    public MysticLibrary(MagicalStructure core, Map<String, String> books, int knowledge) {
        this.core = Objects.requireNonNull(core);
        this.bookCollection = new HashMap<>(books);
        this.knowledgeLevel = knowledge;
    }

    public Map<String, String> getBookCollection() { return new HashMap<>(bookCollection); }
    public int getKnowledgeLevel() { return knowledgeLevel; }
    public void setKnowledgeLevel(int knowledgeLevel) { this.knowledgeLevel = knowledgeLevel; }
    public MagicalStructure getCore() { return core; }

    @Override
    public String toString() {
        return "MysticLibrary{" +
                "books=" + bookCollection.keySet() +
                ", knowledge=" + knowledgeLevel +
                ", core=" + core +
                '}';
    }
}
