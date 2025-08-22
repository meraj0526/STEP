import java.util.Scanner;

public class StringLengthFinder {

    // Method to calculate string length without using length()
    public static int getLengthWithoutUsingLength(String input) {
        int count = 0;
        try {
            while (true) {
                input.charAt(count);  // will throw IndexOutOfBoundsException
                count++;
            }
        } catch (Exception e) {
            // Do nothing, just return the count
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Taking user input
        System.out.print("Enter a string: ");
        String userInput = scanner.next();

        // Getting length using custom method
        int customLength = getLengthWithoutUsingLength(userInput);

        // Getting length using built-in method for comparison
        int builtInLength = userInput.length();

        // Displaying both results
        System.out.println("Length without using length(): " + customLength);
        System.out.println("Length using length(): " + builtInLength);

        scanner.close();
    }
}
