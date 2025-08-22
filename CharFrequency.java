import java.util.Scanner;

public class CharFrequency {

    // Method to find frequency of characters using charAt() and return 2D array
    // 2D array format: [][0] = character as String, [][1] = frequency as String
    public static String[][] findCharFrequency(String text) {
        int[] freq = new int[256]; // ASCII frequency array

        // Step 1: Count frequency of each character
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            freq[c]++;
        }

        // Step 2: Count number of unique characters to size result array
        int uniqueCount = 0;
        for (int i = 0; i < 256; i++) {
            if (freq[i] > 0) {
                uniqueCount++;
            }
        }

        // Step 3: Create 2D array to store characters and their frequencies
        String[][] result = new String[uniqueCount][2];

        // Step 4: Populate the 2D array
        int index = 0;
        for (int i = 0; i < 256; i++) {
            if (freq[i] > 0) {
                result[index][0] = Character.toString((char) i);
                result[index][1] = Integer.toString(freq[i]);
                index++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        String[][] frequencies = findCharFrequency(input);

        System.out.println("Character | Frequency");
        System.out.println("---------------------");
        for (String[] pair : frequencies) {
            System.out.printf("    %s     |     %s%n", pair[0], pair[1]);
        }

        scanner.close();
    }
}
