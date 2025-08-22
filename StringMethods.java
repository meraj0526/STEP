import java.util.Scanner;

public class StringMethods {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask the user for a sentence
        System.out.print("Enter a sentence: ");
        String input = scanner.nextLine();

        // Display original input
        System.out.println("\nOriginal input: \"" + input + "\"");

        // Length of the input
        System.out.println("Length: " + input.length());

        // Convert to uppercase
        System.out.println("Uppercase: " + input.toUpperCase());

        // Convert to lowercase
        System.out.println("Lowercase: " + input.toLowerCase());

        // Trim whitespace from both ends
        System.out.println("Trimmed: \"" + input.trim() + "\"");

        // Check if input contains the word "Java"
        System.out.println("Contains 'Java'? " + input.contains("Java"));

        // Replace spaces with hyphens
        String replaced = input.replace(" ", "-");
        System.out.println("Spaces replaced with '-': " + replaced);

        // Get a substring (first 5 characters or full length if shorter)
        String substring = input.length() >= 5 ? input.substring(0, 5) : input;
        System.out.println("First 5 characters: " + substring);

        // Split the sentence into words and count them
        String[] words = input.trim().split("\\s+"); // splits on one or more spaces
        System.out.println("Number of words: " + words.length);

        // Display each word on a new line
        System.out.println("Words:");
        for (String word : words) {
            System.out.println("- " + word);
        }

        scanner.close();
    }
}
