import java.util.Scanner;

public class StringManupulation {

    // Method to count words in a text
    public static int countWords(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }
        String[] words = text.trim().split("\\s+");
        return words.length;
    }

    // Method to reverse the entire text
    public static String reverseText(String text) {
        return new StringBuilder(text).reverse().toString();
    }

    // Method to check if text is palindrome (ignores case and spaces)
    public static boolean isPalindrome(String text) {
        String cleaned = text.replaceAll("\\s+", "").toLowerCase();
        String reversed = new StringBuilder(cleaned).reverse().toString();
        return cleaned.equals(reversed);
    }

    // Method to capitalize the first letter of each word
    public static String capitalizeWords(String text) {
        String[] words = text.toLowerCase().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if(word.length() > 0) {
                sb.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1)).append(" ");
            }
        }
        return sb.toString().trim();
    }

    // Method to count occurrences of a character
    public static int countCharOccurrences(String text, char ch) {
        int count = 0;
        for(int i = 0; i < text.length(); i++) {
            if(text.charAt(i) == ch) {
                count++;
            }
        }
        return count;
    }

    // Main program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your text:");
        String input = scanner.nextLine();

        System.out.println("\n--- Text Processing Results ---");
        System.out.println("Original Text: " + input);
        System.out.println("Number of words: " + countWords(input));
        System.out.println("Reversed Text: " + reverseText(input));
        System.out.println("Capitalized Words: " + capitalizeWords(input));
        System.out.println("Is Palindrome?: " + (isPalindrome(input) ? "Yes" : "No"));

        System.out.print("Enter a character to count occurrences: ");
        char ch = scanner.nextLine().charAt(0);
        System.out.println("Occurrences of '" + ch + "': " + countCharOccurrences(input, ch));

        scanner.close();
    }
}
