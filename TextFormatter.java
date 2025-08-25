import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextFormatter {

    // Method to split text into words without using split()
    public static String[] splitIntoWords(String text) {
        List<String> words = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                if (start != i) {
                    words.add(text.substring(start, i));
                }
                start = i + 1;
            }
        }
        // Add last word
        if (start < text.length()) {
            words.add(text.substring(start));
        }
        return words.toArray(new String[0]);
    }

    // Method to justify text using StringBuilder
    public static List<String> justifyText(String[] words, int width) {
        List<String> lines = new ArrayList<>();
        int index = 0;

        while (index < words.length) {
            int lineLen = words[index].length();
            int last = index + 1;

            // Determine how many words can fit in the line
            while (last < words.length) {
                if (lineLen + 1 + words[last].length() > width) break;
                lineLen += 1 + words[last].length();
                last++;
            }

            StringBuilder line = new StringBuilder();
            int gaps = last - index - 1;

            // If last line or line contains only one word -> left aligned
            if (last == words.length || gaps == 0) {
                for (int i = index; i < last; i++) {
                    line.append(words[i]);
                    if (i != last - 1) line.append(" ");
                }
                // Fill the remaining spaces at the end
                int remaining = width - line.length();
                for (int i = 0; i < remaining; i++) {
                    line.append(" ");
                }
            } else {
                // Fully justify
                int totalSpaces = width - lineLen + gaps; // add gaps because spaces are counted as one each
                int spacesPerGap = totalSpaces / gaps;
                int extraSpaces = totalSpaces % gaps;

                for (int i = index; i < last; i++) {
                    line.append(words[i]);
                    if (i < last - 1) {
                        for (int s = 0; s < spacesPerGap; s++) {
                            line.append(" ");
                        }
                        if (extraSpaces > 0) {
                            line.append(" ");
                            extraSpaces--;
                        }
                    }
                }
            }

            lines.add(line.toString());
            index = last;
        }

        return lines;
    }

    // Method to center-align text using StringBuilder
    public static List<String> centerAlignText(List<String> lines, int width) {
        List<String> centeredLines = new ArrayList<>();

        for (String line : lines) {
            int padding = (width - line.trim().length()) / 2;
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < padding; i++) sb.append(" ");
            sb.append(line.trim());
            // Fill remaining spaces to the right to keep length consistent
            while (sb.length() < width) sb.append(" ");

            centeredLines.add(sb.toString());
        }

        return centeredLines;
    }

    // Same justification using String concatenation for performance comparison
    public static List<String> justifyTextConcat(String[] words, int width) {
        List<String> lines = new ArrayList<>();
        int index = 0;

        while (index < words.length) {
            int lineLen = words[index].length();
            int last = index + 1;

            while (last < words.length) {
                if (lineLen + 1 + words[last].length() > width) break;
                lineLen += 1 + words[last].length();
                last++;
            }

            String line = "";
            int gaps = last - index - 1;

            if (last == words.length || gaps == 0) {
                for (int i = index; i < last; i++) {
                    line += words[i];
                    if (i != last - 1) line += " ";
                }
                int remaining = width - line.length();
                for (int i = 0; i < remaining; i++) {
                    line += " ";
                }
            } else {
                int totalSpaces = width - lineLen + gaps;
                int spacesPerGap = totalSpaces / gaps;
                int extraSpaces = totalSpaces % gaps;

                for (int i = index; i < last; i++) {
                    line += words[i];
                    if (i < last - 1) {
                        for (int s = 0; s < spacesPerGap; s++) {
                            line += " ";
                        }
                        if (extraSpaces > 0) {
                            line += " ";
                            extraSpaces--;
                        }
                    }
                }
            }

            lines.add(line);
            index = last;
        }

        return lines;
    }

    // Display formatted text with line numbers and char counts
    public static void displayFormattedText(List<String> lines, String title) {
        System.out.println("\n" + title);
        System.out.println("-------------------------------------------------");
        int lineNum = 1;
        for (String line : lines) {
            System.out.printf("%2d | %-50s | %2d chars\n", lineNum++, line, line.length());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter text to format:");
        String text = sc.nextLine();

        System.out.print("Enter desired line width: ");
        int width = sc.nextInt();
        sc.nextLine(); // consume newline

        String[] words = splitIntoWords(text);

        // Justify with StringBuilder
        long startSB = System.nanoTime();
        List<String> justifiedSB = justifyText(words, width);
        long endSB = System.nanoTime();

        // Justify with String concatenation
        long startSC = System.nanoTime();
        List<String> justifiedSC = justifyTextConcat(words, width);
        long endSC = System.nanoTime();

        // Center align based on justifiedSB lines
        List<String> centeredLines = centerAlignText(justifiedSB, width);

        // Display
        System.out.println("\nOriginal Text:\n" + text);

        displayFormattedText(justifiedSB, "Left-justified Text (StringBuilder)");
        displayFormattedText(centeredLines, "Center-aligned Text");

        System.out.println("\nPerformance comparison:");
        System.out.printf("Justify with StringBuilder: %d ns\n", (endSB - startSB));
        System.out.printf("Justify with String concatenation: %d ns\n", (endSC - startSC));

        sc.close();
    }
}
