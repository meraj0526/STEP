import java.util.Scanner;

public class FirstNonRepeatingChar {

    // Method to find the first non-repeating character using charAt()
    public static char findFirstNonRepeatingChar(String text) {
        int[] freq = new int[256];  // ASCII character frequency

        // Step 1: Count frequency of each character
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            freq[c]++;
        }

        // Step 2: Find first character with frequency 1
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (freq[c] == 1) {
                return c;  // first non-repeating character found
            }
        }

        // If no non-repeating character found, return a special char
        return '\0';  // null char to indicate none found
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        char result = findFirstNonRepeatingChar(input);

        if (result == '\0') {
            System.out.println("No non-repeating character found.");
        } else {
            System.out.println("First non-repeating character: " + result);
        }

        scanner.close();
    }
}
