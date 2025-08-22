import java.util.Scanner;

public class ShortestLongestWords {

    // Find length without using length()
    public static int getLengthWithoutLength(String str) {
        int count = 0;
        try {
            while (true) {
                str.charAt(count);
                count++;
            }
        } catch (Exception e) {
            // end of string reached
        }
        return count;
    }

    // Split text into words without using split()
    public static String[] customSplit(String text) {
        int length = getLengthWithoutLength(text);
        int spaceCount = 0;

        // Count spaces
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

    // Create 2D array of words and their lengths as strings
    public static String[][] wordsWithLengths(String[] words) {
        int n = words.length;
        String[][] result = new String[n][2];
        for (int i = 0; i < n; i++) {
            result[i][0] = words[i];
            int len = getLengthWithoutLength(words[i]);
            result[i][1] = String.valueOf(len);
        }
        return result;
    }

    // Find shortest and longest word indexes (returns array: [shortestIndex, longestIndex])
    public static int[] findShortestLongest(String[][] wordData) {
        int shortestIndex = 0;
        int longestIndex = 0;

        int n = wordData.length;
        int shortestLen = Integer.parseInt(wordData[0][1]);
        int longestLen = Integer.parseInt(wordData[0][1]);

        for (int i = 1; i < n; i++) {
            int currLen = Integer.parseInt(wordData[i][1]);
            if (currLen < shortestLen) {
                shortestLen = currLen;
                shortestIndex = i;
            }
            if (currLen > longestLen) {
                longestLen = currLen;
                longestIndex = i;
            }
        }
        return new int[] { shortestIndex, longestIndex };
    }

    // Display words with lengths
    public static void displayWordLengths(String[][] wordData) {
        System.out.printf("%-15s | %-6s%n", "Word", "Length");
        System.out.println("---------------------------");
        for (String[] row : wordData) {
            System.out.printf("%-15s | %-6s%n", row[0], row[1]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a text line: ");
        String input = sc.nextLine();

        String[] words = customSplit(input);
        String[][] wordData = wordsWithLengths(words);
        
        System.out.println("\nWords and their lengths:");
        displayWordLengths(wordData);

        int[] shortestLongest = findShortestLongest(wordData);

        System.out.println("\nShortest word: " + wordData[shortestLongest[0]][0] + " (Length: " + wordData[shortestLongest[0]][1] + ")");
        System.out.println("Longest word: " + wordData[shortestLongest[1]][0] + " (Length: " + wordData[shortestLongest[1]][1] + ")");

        sc.close();
    }
}
