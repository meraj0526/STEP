public class PerformanceComparision {

    private static final int ITERATIONS = 100000;

    public static void main(String[] args) {
        // String concatenation test
        long startTime = System.currentTimeMillis();
        String resultString = "";
        for (int i = 0; i < ITERATIONS; i++) {
            resultString += "a";  // This creates new String objects repeatedly
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken by String concatenation: " + (endTime - startTime) + " ms");

        // StringBuilder test
        startTime = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ITERATIONS; i++) {
            sb.append("a");
        }
        String resultBuilder = sb.toString();
        endTime = System.currentTimeMillis();
        System.out.println("Time taken by StringBuilder: " + (endTime - startTime) + " ms");

        // StringBuffer test
        startTime = System.currentTimeMillis();
        StringBuffer sbuffer = new StringBuffer();
        for (int i = 0; i < ITERATIONS; i++) {
            sbuffer.append("a");
        }
        String resultBuffer = sbuffer.toString();
        endTime = System.currentTimeMillis();
        System.out.println("Time taken by StringBuffer: " + (endTime - startTime) + " ms");
    }
}
