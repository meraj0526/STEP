import java.util.*;

public class FileOrganizer {
    
    // Data structure to store file info
    static class FileInfo {
        String originalName;
        String namePart;
        String extension;
        String category;
        String newName;
        String subCategory;
        int priority;
    }

    // a. Extract filename and extension
    public static FileInfo extractFileInfo(String file) {
        FileInfo fi = new FileInfo();
        fi.originalName = file;
        int dotIndex = file.lastIndexOf('.');
        if (dotIndex != -1) {
            fi.namePart = file.substring(0, dotIndex);
            fi.extension = file.substring(dotIndex + 1).toLowerCase();
        } else {
            fi.namePart = file;
            fi.extension = "";
        }
        return fi;
    }

    // b. Categorize files by extension
    public static String categorize(String ext) {
        if (ext.equals("txt") || ext.equals("doc")) return "Document";
        if (ext.equals("jpg") || ext.equals("png")) return "Image";
        if (ext.equals("mp3") || ext.equals("wav")) return "Audio";
        if (ext.equals("mp4") || ext.equals("mkv")) return "Video";
        if (ext.equals("pdf")) return "PDF";
        return "Unknown";
    }

    // c. Generate new filename using StringBuilder
    public static String generateNewName(String category, String baseName, int count) {
        StringBuilder sb = new StringBuilder();
        sb.append(category.substring(0, 3).toUpperCase());
        sb.append("_");
        sb.append(baseName.replaceAll("[^a-zA-Z0-9]", "_"));
        sb.append("_");
        sb.append(count);
        return sb.toString();
    }

    // d. Simulate content-based analysis (simple keyword check)
    public static void analyzeContent(FileInfo fi) {
        if (fi.category.equals("Document")) {
            if (fi.namePart.toLowerCase().contains("resume")) fi.subCategory = "Resume";
            else if (fi.namePart.toLowerCase().contains("report")) fi.subCategory = "Report";
            else if (fi.namePart.toLowerCase().contains("code")) fi.subCategory = "Code";
            else fi.subCategory = "GeneralDoc";
        } else {
            fi.subCategory = "N/A";
        }
        // Priority based on name length
        fi.priority = fi.namePart.length();
    }

    // e. Display file organization report
    public static void displayReport(List<FileInfo> files) {
        System.out.println("\n=== File Organization Report ===");
        System.out.printf("%-20s %-12s %-20s %-12s %-8s\n", 
                          "Original", "Category", "New Name", "SubCat", "Priority");
        System.out.println("--------------------------------------------------------------");
        for (FileInfo fi : files) {
            System.out.printf("%-20s %-12s %-20s %-12s %-8d\n", 
                              fi.originalName, fi.category, fi.newName, fi.subCategory, fi.priority);
        }
    }

    // f. Generate batch rename commands
    public static void generateRenameCommands(List<FileInfo> files) {
        System.out.println("\n=== Batch Rename Commands ===");
        for (FileInfo fi : files) {
            System.out.println("rename " + fi.originalName + " -> " + fi.newName + "." + fi.extension);
        }
    }

    // Main function
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // a. Take user input for multiple file names
        System.out.println("Enter number of files:");
        int n = sc.nextInt();
        sc.nextLine();

        List<FileInfo> files = new ArrayList<>();
        Map<String, Integer> categoryCount = new HashMap<>();

        for (int i = 0; i < n; i++) {
            System.out.println("Enter file name " + (i + 1) + ":");
            String input = sc.nextLine();

            FileInfo fi = extractFileInfo(input);
            fi.category = categorize(fi.extension);

            int count = categoryCount.getOrDefault(fi.category, 0) + 1;
            fi.newName = generateNewName(fi.category, fi.namePart, count);
            categoryCount.put(fi.category, count);

            analyzeContent(fi);
            files.add(fi);
        }

        // Show report
        displayReport(files);

        // Show rename commands
        generateRenameCommands(files);

        // Show statistics
        System.out.println("\n=== Category Statistics ===");
        for (String cat : categoryCount.keySet()) {
            System.out.println(cat + ": " + categoryCount.get(cat) + " files");
        }

        sc.close();
    }
}
