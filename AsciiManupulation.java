import java.util.Scanner;

public class AsciiManupulation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Convert character to ASCII value
        System.out.print("Enter a character to get its ASCII value: ");
        char ch = scanner.nextLine().charAt(0);
        int asciiValue = (int) ch;
        System.out.println("ASCII value of '" + ch + "' is: " + asciiValue);

        // 2. Convert ASCII value to character
        System.out.print("Enter an ASCII value (0-127) to get the character: ");
        int asciiInput = scanner.nextInt();
        if (asciiInput >= 0 && asciiInput <= 127) {
            char convertedChar = (char) asciiInput;
            System.out.println("Character for ASCII value " + asciiInput + " is: " + convertedChar);
        } else {
            System.out.println("Invalid ASCII value. Must be between 0 and 127.");
        }

        // 3. Display all ASCII characters from 32 to 126 with their values
        System.out.println("\nPrintable ASCII characters from 32 to 126:");
        for (int i = 32; i <= 126; i++) {
            System.out.println(i + " : " + (char) i);
        }

        // 4. Convert lowercase letter to uppercase using ASCII manipulation
        System.out.print("\nEnter a lowercase letter to convert to uppercase: ");
        scanner.nextLine(); // consume newline left by nextInt()
        char lower = scanner.nextLine().charAt(0);
        if (lower >= 'a' && lower <= 'z') {
            char upper = (char) (lower - 32);
            System.out.println("Uppercase of '" + lower + "' is '" + upper + "'");
        } else {
            System.out.println("Input is not a lowercase letter.");
        }

        scanner.close();
    }
}
