import java.util.Scanner;

public class UniqueCharacters {

    // Method to find length of string without using length()
    public static int findLength(String text) {
        int length = 0;
        try {
            while (true) {
                // Try to access character at length index until exception occurs
                text.charAt(length);
                length++;
            }
        } catch (IndexOutOfBoundsException e) {
            // Reached the end of the string
        }
        return length;
    }

    // Method to find unique characters and return as 1D char array
    public static char[] findUniqueChars(String text) {
        int length = findLength(text);
        char[] uniqueChars = new char[length]; // max possible unique chars
        int uniqueCount = 0;

        for (int i = 0; i < length; i++) {
            char currentChar = text.charAt(i);
            boolean isUnique = true;

            // Check if currentChar appeared before
            for (int j = 0; j < uniqueCount; j++) {
                if (uniqueChars[j] == currentChar) {
                    isUnique = false;
                    break;
                }
            }

            if (isUnique) {
                uniqueChars[uniqueCount] = currentChar;
                uniqueCount++;
            }
        }

        // Create a new array with exact size of unique characters
        char[] result = new char[uniqueCount];
        for (int i = 0; i < uniqueCount; i++) {
            result[i] = uniqueChars[i];
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        int length = findLength(input);
        System.out.println("Length of the string (without using length()): " + length);

        char[] unique = findUniqueChars(input);

        System.out.print("Unique characters in the string: ");
        for (char c : unique) {
            System.out.print(c + " ");
        }
        System.out.println();

        scanner.close();
    }
}
