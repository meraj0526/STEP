import java.util.*;

public class PasswordAnalyzer {

    // Method to analyze password
    public static int[] analyzePassword(String password) {
        int upper = 0, lower = 0, digits = 0, special = 0;

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            int ascii = (int) ch;

            if (ascii >= 65 && ascii <= 90) upper++;          // Uppercase
            else if (ascii >= 97 && ascii <= 122) lower++;    // Lowercase
            else if (ascii >= 48 && ascii <= 57) digits++;    // Digits
            else if (ascii >= 33 && ascii <= 126) special++;  // Special characters
        }

        return new int[]{upper, lower, digits, special};
    }

    // Method to calculate password score
    public static int calculateScore(String password, int[] counts) {
        int length = password.length();
        int score = 0;

        // Length points
        if (length > 8) score += (length - 8) * 2;

        // Character variety
        if (counts[0] > 0) score += 10; // uppercase
        if (counts[1] > 0) score += 10; // lowercase
        if (counts[2] > 0) score += 10; // digits
        if (counts[3] > 0) score += 10; // special

        // Deduct for common patterns
        String lowerPass = password.toLowerCase();
        if (lowerPass.contains("123") || lowerPass.contains("abc") || lowerPass.contains("qwerty")) {
            score -= 10;
        }

        return score;
    }

    // Method to classify strength
    public static String getStrength(int score) {
        if (score <= 20) return "Weak";
        else if (score <= 50) return "Medium";
        else return "Strong";
    }

    // Method to generate strong password
    public static String generatePassword(int length) {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String special = "!@#$%^&*()-_=+<>?/";

        String allChars = upper + lower + digits + special;
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();

        // Ensure at least one of each type
        sb.append(upper.charAt(rand.nextInt(upper.length())));
        sb.append(lower.charAt(rand.nextInt(lower.length())));
        sb.append(digits.charAt(rand.nextInt(digits.length())));
        sb.append(special.charAt(rand.nextInt(special.length())));

        // Fill remaining
        for (int i = 4; i < length; i++) {
            sb.append(allChars.charAt(rand.nextInt(allChars.length())));
        }

        // Shuffle for randomness
        List<Character> charList = new ArrayList<>();
        for (char c : sb.toString().toCharArray()) {
            charList.add(c);
        }
        Collections.shuffle(charList);

        StringBuilder finalPass = new StringBuilder();
        for (char c : charList) finalPass.append(c);

        return finalPass.toString();
    }

    // Display results
    public static void displayResults(List<String> passwords) {
        System.out.printf("%-20s %-8s %-8s %-8s %-8s %-8s %-8s %-10s\n",
                "Password", "Length", "Upper", "Lower", "Digits", "Special", "Score", "Strength");
        System.out.println("-----------------------------------------------------------------------------------");

        for (String pwd : passwords) {
            int[] counts = analyzePassword(pwd);
            int score = calculateScore(pwd, counts);
            String strength = getStrength(score);

            System.out.printf("%-20s %-8d %-8d %-8d %-8d %-8d %-8d %-10s\n",
                    pwd, pwd.length(), counts[0], counts[1], counts[2], counts[3], score, strength);
        }
    }

    // Main function
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Take multiple passwords input
        System.out.println("Enter number of passwords to analyze: ");
        int n = sc.nextInt();
        sc.nextLine();

        List<String> passwords = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.println("Enter password " + (i + 1) + ": ");
            passwords.add(sc.nextLine());
        }

        // Display analysis results
        displayResults(passwords);

        // Generate strong password
        System.out.println("\nEnter desired length for new strong password: ");
        int len = sc.nextInt();
        String newPass = generatePassword(len);
        System.out.println("Generated Strong Password: " + newPass);

        sc.close();
    }
}
