import java.util.Scanner;

public class StringMethodsDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a sentence:");
        String text = scanner.nextLine();

        // 1. Length of the string
        System.out.println("Length of text: " + text.length());

        // 2. Convert to uppercase
        System.out.println("Uppercase: " + text.toUpperCase());

        // 3. Convert to lowercase
        System.out.println("Lowercase: " + text.toLowerCase());

        // 4. Check if it contains a word
        System.out.print("Enter a word to check if it exists in the text: ");
        String word = scanner.nextLine();
        System.out.println("Contains '" + word + "'? " + text.contains(word));

        // 5. Find index of a word
        int index = text.indexOf(word);
        if(index != -1) {
            System.out.println("Index of '" + word + "': " + index);
        } else {
            System.out.println("'" + word + "' not found in the text.");
        }

        // 6. Replace a substring
        System.out.print("Enter a word to replace: ");
        String oldWord = scanner.nextLine();
        System.out.print("Enter a new word: ");
        String newWord = scanner.nextLine();
        String replacedText = text.replace(oldWord, newWord);
        System.out.println("Text after replacement: " + replacedText);

        // 7. Split text into words
        String[] words = text.split("\\s+");
        System.out.println("Words in the text:");
        for (String w : words) {
            System.out.println(w);
        }

        // 8. Trim whitespace
        System.out.println("Trimmed text: '" + text.trim() + "'");

        scanner.close();
    }
}
