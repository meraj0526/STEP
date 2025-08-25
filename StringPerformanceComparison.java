import java.util.Scanner;

public class StringPerformanceComparison {

    // Method for String concatenation using '+'
    public static Result testStringConcatenation(int iterations) {
        long start = System.currentTimeMillis();

        String s = "";
        for (int i = 0; i < iterations; i++) {
            s = s + "a";  // sample string is "a"
        }

        long end = System.currentTimeMillis();
        return new Result("String Concatenation", end - start, s.length());
    }

    // Method for StringBuilder.append()
    public static Result testStringBuilder(int iterations) {
        long start = System.currentTimeMillis();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append("a");
        }

        long end = System.currentTimeMillis();
        return new Result("StringBuilder", end - start, sb.length());
    }

    // Method for StringBuffer.append()
    public static Result testStringBuffer(int iterations) {
        long start = System.currentTimeMillis();

        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            sbf.append("a");
        }

        long end = System.currentTimeMillis();
        return new Result("StringBuffer", end - start, sbf.length());
    }

    // Method to display performance results in a table
    public static void displayResults(Result... results) {
        System.out.printf("%-25s %-15s %-20s\n", "Method Used", "Time (ms)", "Final String Length");
        System.out.println("--------------------------------------------------------------");
        for (Result r : results) {
            System.out.printf("%-25s %-15d %-20d\n", r.method, r.timeTaken, r.finalLength);
        }
    }

    // Inner class to hold result data
    static class Result {
        String method;
        long timeTaken;
        int finalLength;

        Result(String method, long timeTaken, int finalLength) {
            this.method = method;
            this.timeTaken = timeTaken;
            this.finalLength = finalLength;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of iterations (e.g., 1000, 10000, 100000): ");
        int iterations = sc.nextInt();

        System.out.println("Running performance tests...");

        Result concatResult = testStringConcatenation(iterations);
        Result builderResult = testStringBuilder(iterations);
        Result bufferResult = testStringBuffer(iterations);

        displayResults(concatResult, builderResult, bufferResult);

        sc.close();
    }
}
