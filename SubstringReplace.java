import java.util.ArrayList;
import java.util.Scanner;

public class SubstringReplace {

    // Method to find all occurrences of the substring and return their start indices
    public static ArrayList<Integer> findOccurrences(String text, String sub) {
        ArrayList<Integer> positions = new ArrayList<>();
        int index = text.indexOf(sub);
        while (index != -1) {
            positions.add(index);
            index = text.indexOf(sub, index + 1);
        }
        return positions;
    }

    // Method to replace all occurrences manually
    public static String manualReplace(String text, String find, String replacement) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < text.length()) {
            // Check if substring starting at i matches 'find'
            if (i <= text.length() - find.length() && text.substring(i, i + find.length()).equals(find)) {
                // Append replacement substring
                result.append(replacement);
                i += find.length(); // Skip the length of 'find' substring in original text
            } else {
                // Append current character
                result.append(text.charAt(i));
                i++;
            }
        }
        return result.toString();
    }

    // Method to compare manual replace result with built-in replace()
    public static boolean compareWithBuiltIn(String text, String find, String replacement) {
        String builtInResult = text.replace(find, replacement);
        String manualResult = manualReplace(text, find, replacement);
        return builtInResult.equals(manualResult);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the main text: ");
        String mainText = sc.nextLine();

        System.out.print("Enter the substring to find: ");
        String find = sc.nextLine();

        System.out.print("Enter the replacement substring: ");
        String replacement = sc.nextLine();

        // Find all occurrences
        ArrayList<Integer> occurrences = findOccurrences(mainText, find);
        System.out.println("Positions of occurrences: " + occurrences);

        // Replace manually
        String replacedText = manualReplace(mainText, find, replacement);
        System.out.println("Text after manual replacement: " + replacedText);

        // Compare with built-in replace()
        boolean isSame = compareWithBuiltIn(mainText, find, replacement);
        System.out.println("Does manual replacement match built-in replace()? " + isSame);

        // Also display the built-in replace for verification
        System.out.println("Text after built-in replacement: " + mainText.replace(find, replacement));

        sc.close();
    }
}
