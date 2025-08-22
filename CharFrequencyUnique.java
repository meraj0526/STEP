import java.util.Scanner;

public class CharFrequencyUnique {

    // Method to find unique characters using nested loops and charAt()
    public static char[] uniqueCharacters(String text) {
        int length = findLength(text);
        char[] uniqueChars = new char[length];
        int uniqueCount = 0;

        for (int i = 0; i < length; i++) {
            char current = text.charAt(i);
            boolean isUnique = true;

            // Check if current character already added to uniqueChars
            for (int j = 0; j < uniqueCount; j++) {
                if (uniqueChars[j] == current) {
                    isUnique = false;
                    break;
                }
            }

            if (isUnique) {
                uniqueChars[uniqueCount] = current;
                uniqueCount++;
            }
        }

        // Trim array to exact size
        char[] result = new char[uniqueCount];
        for (int i = 0; i < uniqueCount; i++) {
            result[i] = uniqueChars[i];
        }
        return result;
    }

    // Helper method to find length without using length()
    public static int findLength(String text) {
        int length = 0;
        try {
            while (true) {
                text.charAt(length);
                length++;
            }
        } catch (IndexOutOfBoundsException e) {
            // End of string reached
        }
        return length;
    }

    // Method to find frequency of unique characters and return 2D array
    public static String[][] frequencyOfUniqueChars(String text) {
        int[] freq = new int[256]; // ASCII frequency array

        // Count frequency of each character in text
        for (int i = 0; i < text.length(); i++) {
            freq[text.charAt(i)]++;
        }

        // Get unique characters
        char[] uniqueChars = uniqueCharacters(text);

        // Prepare 2D array to store character and frequency
        String[][] result = new String[uniqueChars.length][2];

        // Populate result array
        for (int i = 0; i < uniqueChars.length; i++) {
            result[i][0] = Character.toString(uniqueChars[i]);
            result[i][1] = Integer.toString(freq[uniqueChars[i]]);
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        String[][] freqResults = frequencyOfUniqueChars(input);

        System.out.println("Character | Frequency");
        System.out.println("---------------------");
        for (String[] pair : freqResults) {
            System.out.printf("    %s     |     %s%n", pair[0], pair[1]);
        }

        scanner.close();
    }
}
