import java.util.*;

public class SpellChecker {

    // Method to split sentence into words without using split()
    public static String[] getWords(String sentence) {
        ArrayList<String> words = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < sentence.length(); i++) {
            char ch = sentence.charAt(i);
            if (ch == ' ' || ch == '.' || ch == ',' || ch == '!' || ch == '?') {
                if (start < i) {
                    words.add(sentence.substring(start, i));
                }
                start = i + 1;
            }
        }
        // Add last word if exists
        if (start < sentence.length()) {
            words.add(sentence.substring(start));
        }
        return words.toArray(new String[0]);
    }

    // Method to calculate string distance
    public static int stringDistance(String w1, String w2) {
        int len1 = w1.length();
        int len2 = w2.length();
        int distance = Math.abs(len1 - len2);

        int minLen = Math.min(len1, len2);
        for (int i = 0; i < minLen; i++) {
            if (w1.charAt(i) != w2.charAt(i)) {
                distance++;
            }
        }
        return distance;
    }

    // Find closest matching word from dictionary
    public static String findClosest(String word, String[] dictionary) {
        int minDist = Integer.MAX_VALUE;
        String closest = word; // default as same word
        for (String dictWord : dictionary) {
            int dist = stringDistance(word.toLowerCase(), dictWord.toLowerCase());
            if (dist < minDist) {
                minDist = dist;
                closest = dictWord;
            }
        }
        return (minDist <= 2) ? closest : "No suggestion";
    }

    // Display results in tabular format
    public static void displayResults(String[] words, String[] dictionary) {
        System.out.printf("%-15s %-15s %-10s %-15s\n", "Original", "Suggestion", "Distance", "Status");
        System.out.println("-------------------------------------------------------------------");

        for (String word : words) {
            String suggestion = findClosest(word, dictionary);
            int distance = suggestion.equals("No suggestion") ? -1 : stringDistance(word, suggestion);
            String status = (suggestion.equals(word)) ? "Correct" : (suggestion.equals("No suggestion") ? "Misspelled" : "Misspelled");
            System.out.printf("%-15s %-15s %-10d %-15s\n", word, suggestion, distance, status);
        }
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Dictionary
        String[] dictionary = {"apple", "banana", "orange", "grape", "mango", "pineapple"};

        System.out.println("Enter a sentence: ");
        String sentence = sc.nextLine();

        // Process sentence
        String[] words = getWords(sentence);

        // Display spell check results
        displayResults(words, dictionary);

        sc.close();
    }
}
