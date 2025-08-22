import java.util.Scanner;

public class CustomSplitComparison {

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

        // Count number of spaces to find number of words
        for (int i = 0; i < length; i++) {
            if (text.charAt(i) == ' ') {
                spaceCount++;
            }
        }

        // Number of words is spaces + 1
        int wordsCount = spaceCount + 1;

        // Array to store indexes of spaces
        int[] spaceIndexes = new int[spaceCount];
        int index = 0;
        for (int i = 0; i < length; i++) {
            if (text.charAt(i) == ' ') {
                spaceIndexes[index++] = i;
            }
        }

        // Array to store words
        String[] words = new String[wordsCount];

        int start = 0;
        for (int i = 0; i < wordsCount; i++) {
            int end = (i < spaceCount) ? spaceIndexes[i] : length;
            StringBuilder word = new StringBuilder();
            for (int j = start; j < end; j++) {
                word.append(text.charAt(j));
            }
            words[i] = word.toString();
            start = end + 1; // move past the space
        }

        return words;
    }

    // Method to compare two string arrays
    public static boolean compareStringArrays(String[] arr1, String[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (!arr1[i].equals(arr2[i])) {
                return false;
            }
        }
        return true;
    }

    // Main method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a sentence: ");
        String inputText = scanner.nextLine();

        // Custom split
        String[] customSplitWords = customSplit(inputText);

        // Built-in split
        String[] builtinSplitWords = inputText.split(" ");

        // Display custom split result
        System.out.println("\nCustom split words:");
        for (String word : customSplitWords) {
            System.out.println(word);
        }

        // Display built-in split result
        System.out.println("\nBuilt-in split words:");
        for (String word : builtinSplitWords) {
            System.out.println(word);
        }

        // Compare results
        boolean isEqual = compareStringArrays(customSplitWords, builtinSplitWords);
        System.out.println("\nAre both split results equal? " + isEqual);

        scanner.close();
    }
}
