import java.util.Scanner;

public class CaesarCipher {

    // Encrypt method
    public static String encrypt(String text, int shift) {
        StringBuilder encrypted = new StringBuilder();

        for (char ch : text.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                // Shift uppercase letters and wrap-around
                char e = (char) ('A' + (ch - 'A' + shift) % 26);
                encrypted.append(e);
            } else if (Character.isLowerCase(ch)) {
                // Shift lowercase letters and wrap-around
                char e = (char) ('a' + (ch - 'a' + shift) % 26);
                encrypted.append(e);
            } else {
                // Non-alphabetic characters unchanged
                encrypted.append(ch);
            }
        }

        return encrypted.toString();
    }

    // Decrypt method
    public static String decrypt(String text, int shift) {
        StringBuilder decrypted = new StringBuilder();

        for (char ch : text.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                // Shift uppercase letters backwards with wrap-around
                char d = (char) ('A' + (ch - 'A' - shift + 26) % 26);
                decrypted.append(d);
            } else if (Character.isLowerCase(ch)) {
                // Shift lowercase letters backwards with wrap-around
                char d = (char) ('a' + (ch - 'a' - shift + 26) % 26);
                decrypted.append(d);
            } else {
                // Non-alphabetic characters unchanged
                decrypted.append(ch);
            }
        }

        return decrypted.toString();
    }

    // Method to display ASCII values of characters in a string
    public static void displayAsciiValues(String label, String text) {
        System.out.print(label + ": ");
        for (char ch : text.toCharArray()) {
            System.out.print(ch + "(" + (int) ch + ") ");
        }
        System.out.println();
    }

    // Method to validate that decrypted text equals original text
    public static boolean validateDecryption(String original, String decrypted) {
        return original.equals(decrypted);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Taking inputs
        System.out.print("Enter text to encrypt: ");
        String originalText = sc.nextLine();

        System.out.print("Enter shift value (integer): ");
        int shift = sc.nextInt();

        // Encrypt the original text
        String encryptedText = encrypt(originalText, shift);

        // Decrypt the encrypted text
        String decryptedText = decrypt(encryptedText, shift);

        // Display ASCII values and texts
        displayAsciiValues("Original Text", originalText);
        displayAsciiValues("Encrypted Text", encryptedText);
        displayAsciiValues("Decrypted Text", decryptedText);

        // Validate decryption
        if (validateDecryption(originalText, decryptedText)) {
            System.out.println("Decryption successful: Decrypted text matches the original.");
        } else {
            System.out.println("Decryption failed: Decrypted text does not match the original.");
        }

        sc.close();
    }
}
