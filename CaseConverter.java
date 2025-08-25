import java.util.Scanner;

public class CaseConverter {

    // Convert a single character to uppercase (if lowercase)
    public static char toUpperCaseChar(char ch) {
        if (ch >= 'a' && ch <= 'z') {
            return (char) (ch - 32);
        }
        return ch;
    }

    // Convert a single character to lowercase (if uppercase)
    public static char toLowerCaseChar(char ch) {
        if (ch >= 'A' && ch <= 'Z') {
            return (char) (ch + 32);
        }
        return ch;
    }

    // Convert entire string to uppercase using ASCII conversion
    public static String toUpperCase(String text) {
        StringBuilder result = new StringBuilder();
        for (char ch : text.toCharArray()) {
            result.append(toUpperCaseChar(ch));
        }
        return result.toString();
    }

    // Convert entire string to lowercase using ASCII conversion
    public static String toLowerCase(String text) {
        StringBuilder result = new StringBuilder();
        for (char ch : text.toCharArray()) {
            result.append(toLowerCaseChar(ch));
        }
        return result.toString();
    }

    // Convert string to title case: first letter uppercase, rest lowercase per word
    public static String toTitleCase(String text) {
        StringBuilder result = new StringBuilder();
        boolean newWord = true;  // Flag to detect start of word

        for (char ch : text.toCharArray()) {
            if (ch == ' ' || ch == '\t' || ch == '\n') {
                // Whitespace resets word start
                newWord = true;
                result.append(ch);
            } else {
                if (newWord) {
                    // Capitalize first letter of word
                    result.append(toUpperCaseChar(ch));
                    newWord = false;
                } else {
                    // Lowercase rest of the word
                    result.append(toLowerCaseChar(ch));
                }
            }
        }
        return result.toString();
    }

    // Compare custom conversions with built-in String methods
    public static boolean compareWithBuiltIn(String original) {
        boolean upperMatch = toUpperCase(original).equals(original.toUpperCase());
        boolean lowerMatch = toLowerCase(original).equals(original.toLowerCase());
        boolean titleMatch = toTitleCase(original).equals(toBuiltInTitleCase(original));
        return upperMatch && lowerMatch && titleMatch;
    }

    // Helper to convert to title case using built-in methods (for comparison)
    private static String toBuiltInTitleCase(String text) {
        String[] words = text.split("\\s+");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                sb.append(Character.toUpperCase(words[i].charAt(0)));
                sb.append(words[i].substring(1).toLowerCase());
            }
            if (i < words.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = sc.nextLine();

        String upper = toUpperCase(text);
        String lower = toLowerCase(text);
        String title = toTitleCase(text);

        System.out.printf("%-20s %-30s %-30s\n", "Original Text", "Uppercase Conversion", "Lowercase Conversion");
        System.out.printf("%-20s %-30s %-30s\n", text, upper, lower);

        System.out.println();
        System.out.printf("%-20s %-30s\n", "Title Case Conversion", "Built-in Title Case");
        System.out.printf("%-20s %-30s\n", title, toBuiltInTitleCase(text));

        // Validate conversions match built-in methods
        if (compareWithBuiltIn(text)) {
            System.out.println("\nAll conversions match built-in Java methods.");
        } else {
            System.out.println("\nSome conversions do not match built-in Java methods.");
        }

        sc.close();
    }
}
