public class string21 {
    public static void main(String[] args) {
        // 1. Creating strings using string literals
        String str1 = "Hello, World!";

        // 2. Creating strings using the new keyword
        String str2 = new String("Java Programming");

        // 3. Creating strings from char arrays
        char[] chars = {'J', 'a', 'v', 'a'};
        String str3 = new String(chars);

        // 4. Creating strings from byte arrays
        byte[] bytes = {65, 66, 67, 68};  // ASCII values for A, B, C, D
        String str4 = new String(bytes);

        // Display the strings
        System.out.println("str1: " + str1);
        System.out.println("str2: " + str2);
        System.out.println("str3: " + str3);
        System.out.println("str4: " + str4);

        // Basic string manipulations
        // Length of a string
        System.out.println("Length of str1: " + str1.length());

        // Convert to uppercase
        System.out.println("Uppercase str2: " + str2.toUpperCase());

        // Convert to lowercase
        System.out.println("Lowercase str2: " + str2.toLowerCase());

        // Substring
        System.out.println("Substring of str1 (7 to 12): " + str1.substring(7, 12));

        // Replace characters
        System.out.println("Replace 'Java' with 'Python' in str2: " + str2.replace("Java", "Python"));

        // Check if string contains a substring
        System.out.println("str1 contains 'World': " + str1.contains("World"));

        // Concatenate strings
        String combined = str1 + " " + str2;
        System.out.println("Concatenated string: " + combined);

        // Trim whitespace
        String str5 = "   Trim me   ";
        System.out.println("Before trim: '" + str5 + "'");
        System.out.println("After trim: '" + str5.trim() + "'");
    }
}
