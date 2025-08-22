import java.util.Scanner;

public class BMICalculator {

    // Method to calculate BMI and status
    // Input: 2D double array with weight(kg) and height(cm)
    // Output: 2D String array with weight, height, BMI, status
    public static String[][] calculateBMIAndStatus(double[][] data) {
        int n = data.length;
        String[][] results = new String[n][4]; // weight, height, BMI, status

        for (int i = 0; i < n; i++) {
            double weight = data[i][0];
            double heightCm = data[i][1];
            double heightM = heightCm / 100.0;

            double bmi = weight / (heightM * heightM);
            String status;

            if (bmi < 18.5) {
                status = "Underweight";
            } else if (bmi < 24.9) {
                status = "Normal weight";
            } else if (bmi < 29.9) {
                status = "Overweight";
            } else {
                status = "Obese";
            }

            // Store values as strings (BMI rounded to 2 decimals)
            results[i][0] = String.format("%.2f", weight);
            results[i][1] = String.format("%.2f", heightCm);
            results[i][2] = String.format("%.2f", bmi);
            results[i][3] = status;
        }
        return results;
    }

    // Method to display the 2D String array in tabular format
    public static void displayResults(String[][] results) {
        System.out.printf("%-10s %-10s %-10s %-15s%n", "Weight(kg)", "Height(cm)", "BMI", "Status");
        System.out.println("-------------------------------------------------");
        for (String[] person : results) {
            System.out.printf("%-10s %-10s %-10s %-15s%n", person[0], person[1], person[2], person[3]);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int TEAM_SIZE = 10;
        double[][] data = new double[TEAM_SIZE][2]; // [weight, height]

        System.out.println("Enter weight (kg) and height (cm) for 10 team members:");

        for (int i = 0; i < TEAM_SIZE; i++) {
            System.out.printf("Person %d - Weight (kg): ", i + 1);
            data[i][0] = scanner.nextDouble();

            System.out.printf("Person %d - Height (cm): ", i + 1);
            data[i][1] = scanner.nextDouble();
        }

        // Calculate BMI and status
        String[][] results = calculateBMIAndStatus(data);

        // Display results
        System.out.println("\nBMI Report:");
        displayResults(results);

        scanner.close();
    }
}
