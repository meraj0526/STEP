import java.util.HashSet;
import java.util.Scanner;

public class TextProcessor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = "";

        System.out.println("----- Simple Text Processor -----");

        // Step 1: Get user input
        System.out.print("Enter a block of text:\n> ");
        text = scanner.nextLine();

        int choice;
        do {
            // Menu
            System.out.println("\nChoose an option:");
            System.out.println("1. Show text length");
            System.out.println("2. Convert to UPPERCASE");
            System.out.println("3. Convert to lowercase");
            System.out.println("4. Word count");
            System.out.println("5. Replace a word");
            System.out.println("6. Search for a word");
            System.out.println("7. Display unique words");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.println("Text length: " + text.length() + " characters");
                    break;
                case 2:
                    System.out.println("Uppercase:\n" + text.toUpperCase());
                    break;
                case 3:
                    System.out.println("Lowercase:\n" + text.toLowerCase());
                    break;
                case 4:
                    String[] words = text.trim().split("\\s+");
                    System.out.println("Word count: " + words.length);
                    break;
                case 5:
                    System.out.print("Enter word to replace: ");
                    String oldWord = scanner.nextLine();
                    System.out.print("Enter new word: ");
                    String newWord = scanner.nextLine();
                    text = text.replace(oldWord, newWord);
                    System.out.println("Updated text:\n" + text);
                    break;
                case 6:
                    System.out.print("Enter word to search: ");
                    String searchWord = scanner.nextLine().toLowerCase();
                    if (text.toLowerCase().contains(searchWord)) {
                        System.out.println("'" + searchWord + "' found in the text.");
                    } else {
                        System.out.println("'" + searchWord + "' not found.");
                    }
                    break;
                case 7:
                    String[] allWords = text.toLowerCase().trim().split("\\s+");
                    HashSet<String> uniqueWords = new HashSet<>();
                    for (String w : allWords) {
                        uniqueWords.add(w.replaceAll("[^a-zA-Z]", "")); // remove punctuation
                    }
                    System.out.println("Unique words (" + uniqueWords.size() + "):");
                    for (String w : uniqueWords) {
                        if (!w.isEmpty()) {
                            System.out.println("- " + w);
                        }
                    }
                    break;
                case 8:
                    System.out.println("Exiting Text Processor.");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 8);

        scanner.close();
    }
}
