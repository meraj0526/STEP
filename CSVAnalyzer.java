import java.util.*;

public class CSVAnalyzer {

    // (b) Parse CSV-like data manually
    public static String[][] parseCSV(String input) {
        ArrayList<String[]> rows = new ArrayList<>();
        ArrayList<String> fields = new ArrayList<>();
        StringBuilder field = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == '"') {
                inQuotes = !inQuotes; // toggle quotes
            } else if (c == ',' && !inQuotes) {
                fields.add(field.toString());
                field.setLength(0);
            } else if ((c == '\n' || c == '\r') && !inQuotes) {
                if (field.length() > 0 || fields.size() > 0) {
                    fields.add(field.toString());
                    field.setLength(0);
                    rows.add(fields.toArray(new String[0]));
                    fields.clear();
                }
            } else {
                field.append(c);
            }
        }
        if (field.length() > 0) fields.add(field.toString());
        if (!fields.isEmpty()) rows.add(fields.toArray(new String[0]));

        return rows.toArray(new String[0][0]);
    }

    // (c) Clean and validate
    public static String[][] cleanData(String[][] data, List<String> issues) {
        String[][] cleaned = new String[data.length][];
        for (int i = 0; i < data.length; i++) {
            cleaned[i] = new String[data[i].length];
            for (int j = 0; j < data[i].length; j++) {
                String val = data[i][j].trim();
                if (val.isEmpty()) {
                    issues.add("Row " + (i + 1) + ", Col " + (j + 1) + ": Missing data");
                }
                cleaned[i][j] = val;
            }
        }
        return cleaned;
    }

    private static boolean isNumeric(String s) {
        if (s == null || s.isEmpty()) return false;
        int dotCount = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((c >= '0' && c <= '9')) continue;
            if (c == '.' && dotCount == 0) { dotCount++; continue; }
            return false;
        }
        return true;
    }

    // (d) Data analysis
    public static void analyzeData(String[][] data, List<String> issues) {
        if (data.length < 2) return; // need header + rows
        int cols = data[0].length;

        for (int j = 0; j < cols; j++) {
            boolean numeric = true;
            ArrayList<Double> numbers = new ArrayList<>();
            HashSet<String> unique = new HashSet<>();

            for (int i = 1; i < data.length; i++) {
                String val = data[i][j];
                if (isNumeric(val)) {
                    numbers.add(Double.parseDouble(val));
                } else {
                    numeric = false;
                    unique.add(val);
                    if (val.isEmpty()) issues.add("Row " + (i + 1) + ", Col " + (j + 1) + ": Invalid/missing value");
                }
            }

            if (numeric) {
                double min = Collections.min(numbers);
                double max = Collections.max(numbers);
                double sum = 0;
                for (double d : numbers) sum += d;
                double avg = sum / numbers.size();
                System.out.printf("Column '%s': Min=%.2f, Max=%.2f, Avg=%.2f\n",
                        data[0][j], min, max, avg);
            } else {
                System.out.printf("Column '%s': Unique values=%d\n", data[0][j], unique.size());
            }
        }
    }

    // (e) Format output
    public static void formatTable(String[][] data, List<String> issues) {
        int cols = data[0].length;
        int[] widths = new int[cols];

        for (int j = 0; j < cols; j++) {
            int maxLen = data[0][j].length();
            for (int i = 1; i < data.length; i++) {
                if (data[i][j].length() > maxLen) maxLen = data[i][j].length();
            }
            widths[j] = maxLen + 2;
        }

        // Print table
        System.out.println("\nFormatted Table:");
        for (int i = 0; i < data.length; i++) {
            System.out.print("|");
            for (int j = 0; j < cols; j++) {
                String val = data[i][j];
                System.out.printf(" %-" + widths[j] + "s", val);
                System.out.print("|");
            }
            System.out.println();
            if (i == 0) {
                System.out.print("+");
                for (int w : widths) {
                    for (int k = 0; k < w + 1; k++) System.out.print("-");
                    System.out.print("+");
                }
                System.out.println();
            }
        }

        // Highlight issues
        if (!issues.isEmpty()) {
            System.out.println("\nData Quality Issues:");
            for (String s : issues) System.out.println(" - " + s);
        }
    }

    // (f) Summary report
    public static void summaryReport(String[][] data, List<String> issues) {
        int totalRecords = data.length - 1; // excluding header
        int totalIssues = issues.size();
        double completeness = 100.0 * (totalRecords * data[0].length - totalIssues) / (totalRecords * data[0].length);

        System.out.println("\n=== Summary Report ===");
        System.out.println("Total Records Processed: " + totalRecords);
        System.out.println("Total Columns: " + data[0].length);
        System.out.println("Total Issues: " + totalIssues);
        System.out.printf("Data Completeness: %.2f%%\n", completeness);
    }

    // (g) Main
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter CSV-like data (end with empty line):");
        StringBuilder input = new StringBuilder();
        while (true) {
            String line = sc.nextLine();
            if (line.trim().isEmpty()) break;
            input.append(line).append("\n");
        }

        // Process
        String[][] parsed = parseCSV(input.toString());
        List<String> issues = new ArrayList<>();
        String[][] cleaned = cleanData(parsed, issues);

        formatTable(cleaned, issues);

        System.out.println("\nColumn Analysis:");
        analyzeData(cleaned, issues);

        summaryReport(cleaned, issues);

        sc.close();
    }
}
