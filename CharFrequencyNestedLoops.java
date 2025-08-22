import java.util.Scanner;

public class CharFrequencyNestedLoops {

    // Method to find frequency of characters using nested loops
    // Returns a 1D String array where each element is "char: frequency"
    public static String[] findFrequency(String text) {
        char[] chars = text.toCharArray();
        int length = chars.length;
        int[] freq = new int[length];

        // Initialize frequencies to 1 for each character
        for (int i = 0; i < length; i++) {
            freq[i] = 1;
        }

        // Nested loop to count frequencies and mark duplicates
        for (int i = 0; i < length; i++) {
            if (chars[i] == '0') {
                continue; // Skip duplicates already marked
            }
            for (int j = i + 1; j < length; j++) {
                if (chars[i] == chars[j]) {
                    freq[i]++;
                    chars[j] = '0'; // Mark duplicate to avoid recount
                }
            }
        }

        // Count unique characters (those not marked as '0')
        int uniqueCount = 0;
        for (int i = 0; i < length; i++) {
            if (chars[i] != '0') {
                uniqueCount++;
            }
        }

        // Prepare result array of "character: frequency"
        String[] result = new String[uniqueCount];
        int index = 0;
        for (int i = 0; i < length; i++) {
            if (chars[i] != '0') {
                result[index] = chars[i] + ": " + freq[i];
                index++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        String[] frequencies = findFrequency(input);

        System.out.println("Character frequencies:");
        for (String s : frequencies) {
            System.out.println(s);
        }

        scanner.close();
    }
}
