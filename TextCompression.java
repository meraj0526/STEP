import java.util.*;

public class TextCompression {

    // Count character frequency without HashMap
    public static Object[] countFrequency(String text) {
        char[] chars = new char[text.length()];
        int[] freq = new int[text.length()];
        int uniqueCount = 0;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int index = -1;

            // Check if already exists
            for (int j = 0; j < uniqueCount; j++) {
                if (chars[j] == c) {
                    index = j;
                    break;
                }
            }

            // If new character
            if (index == -1) {
                chars[uniqueCount] = c;
                freq[uniqueCount] = 1;
                uniqueCount++;
            } else {
                freq[index]++;
            }
        }

        // Trim arrays
        char[] finalChars = Arrays.copyOf(chars, uniqueCount);
        int[] finalFreq = Arrays.copyOf(freq, uniqueCount);

        return new Object[]{finalChars, finalFreq};
    }

    // Create compression codes (shorter for frequent chars)
    public static String[][] createCodes(char[] chars, int[] freq) {
        int n = chars.length;
        String[][] mapping = new String[n][2];

        // Sort by frequency (descending)
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (freq[i] < freq[j]) {
                    int tempF = freq[i]; freq[i] = freq[j]; freq[j] = tempF;
                    char tempC = chars[i]; chars[i] = chars[j]; chars[j] = tempC;
                }
            }
        }

        // Assign codes (shorter for frequent)
        String symbols = "1234567890!@#$%^&*()_+=-abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < n; i++) {
            mapping[i][0] = String.valueOf(chars[i]);
            mapping[i][1] = String.valueOf(symbols.charAt(i));
        }
        return mapping;
    }

    // Compress text using mapping
    public static String compress(String text, String[][] mapping) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            for (String[] map : mapping) {
                if (map[0].charAt(0) == c) {
                    sb.append(map[1]);
                    break;
                }
            }
        }
        return sb.toString();
    }

    // Decompress text using mapping
    public static String decompress(String compressed, String[][] mapping) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < compressed.length(); i++) {
            char c = compressed.charAt(i);
            for (String[] map : mapping) {
                if (map[1].charAt(0) == c) {
                    sb.append(map[0]);
                    break;
                }
            }
        }
        return sb.toString();
    }

    // Display analysis
    public static void displayAnalysis(String text, char[] chars, int[] freq, 
                                       String[][] mapping, String compressed, String decompressed) {
        System.out.println("\nCharacter Frequency Table:");
        System.out.printf("%-10s %-10s\n", "Character", "Frequency");
        System.out.println("--------------------------");
        for (int i = 0; i < chars.length; i++) {
            System.out.printf("%-10s %-10d\n", chars[i], freq[i]);
        }

        System.out.println("\nCompression Mapping:");
        System.out.printf("%-10s %-10s\n", "Char", "Code");
        System.out.println("--------------------------");
        for (String[] map : mapping) {
            System.out.printf("%-10s %-10s\n", map[0], map[1]);
        }

        System.out.println("\nOriginal Text: " + text);
        System.out.println("Compressed Text: " + compressed);
        System.out.println("Decompressed Text: " + decompressed);

        int originalSize = text.length();
        int compressedSize = compressed.length();
        double efficiency = (1 - (compressedSize / (double) originalSize)) * 100;

        System.out.println("\nCompression Efficiency: " + String.format("%.2f", efficiency) + "%");
    }

    // Main
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter text to compress: ");
        String text = sc.nextLine();

        // Step 1: Frequency count
        Object[] result = countFrequency(text);
        char[] chars = (char[]) result[0];
        int[] freq = (int[]) result[1];

        // Step 2: Create codes
        String[][] mapping = createCodes(chars, freq);

        // Step 3: Compress
        String compressed = compress(text, mapping);

        // Step 4: Decompress
        String decompressed = decompress(compressed, mapping);

        // Step 5: Display analysis
        displayAnalysis(text, chars, freq, mapping, compressed, decompressed);

        sc.close();
    }
}
