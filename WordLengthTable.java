import java.util.Scanner;

public class WordLengthTable {

    // Method to find length without using length()
    public static int getLengthWithoutLength(String str) {
        int count = 0;
        try {
            while (true) {
                str.charAt(count);
                count++;
            }
        } catch (Exception e) {
            // End of string reached
        }
        return count;
    }

    // Method to split text into words without using split()
    public static String[] customSplit(String text) {
        int length = getLengthWithoutLength(text);
        int spaceCount = 0;

        // Count number of spaces
        for (int i = 0; i < length; i++) {
            if (text.charAt(i) == ' ') {
                spaceCount++;
            }
        }

        int wordsCount = spaceCount + 1;
        int[] spaceIndexes = new int[spaceCount];
        int idx = 0;

        for (int i = 0; i < length; i++) {
            if (text.charAt(i) == ' ') {
                spaceIndexes[idx++] = i;
            }
        }

        String[] words = new String[wordsCount];
        int start = 0;

        for (int i = 0; i < wordsCount; i++) {
            int end = (i < spaceCount) ? spaceIndexes[i] : length;
            StringBuilder word = new StringBuilder();
            for (int j = start; j < end; j++) {
                word.append(text.charAt(j));
            }
            words[i] = word.toString();
            start = end + 1;
        }

        return words;
    }

    // Method to create 2D array of word and its length (length as String)
    public static String[][] wordsWithLengths(String[] words) {
        int n = words.length;
        String[][] result = new String[n][2];

        for (int i = 0; i < n; i++) {
            result[i][0] = words[i];
            int length = getLengthWithoutLength(words[i]);
            result[i][1] = String.valueOf(length);
        }

        return result;
    }

    // Display 2D array in tabular format, converting length string to int for display
    public static void displayTable(String[][] wordData) {
        System.out.printf("%-15s | %-6s%n", "Word", "Length");
        System.out.println("---------------------------");
        for (String[] row : wordData) {
            String word = row[0];
            int length = Integer.parseInt(row[1]);
            System.out.printf("%-15s | %-6d%n", word, length);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a sentence: ");
        String inputText = scanner.nextLine();

        // Split words without using split()
        String[] words = customSplit(inputText);

        // Create 2D array of word and length
        String[][] wordData = wordsWithLengths(words);

        // Display
        System.out.println("\nWord Length Table:");
        displayTable(wordData);

        scanner.close();
    }
}
