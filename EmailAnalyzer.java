import java.util.*;

public class EmailAnalyzer {

    static class EmailInfo {
        String email;
        String username;
        String domain;
        String domainName;
        String extension;
        boolean isValid;

        EmailInfo(String email, boolean isValid, String username, String domain, String domainName, String extension) {
            this.email = email;
            this.isValid = isValid;
            this.username = username;
            this.domain = domain;
            this.domainName = domainName;
            this.extension = extension;
        }
    }

    // Validate email format
    public static boolean validateEmail(String email) {
        int atIndex = email.indexOf('@');
        int lastAtIndex = email.lastIndexOf('@');

        // Exactly one '@' symbol
        if (atIndex == -1 || atIndex != lastAtIndex) {
            return false;
        }

        // Username and domain not empty
        if (atIndex == 0 || atIndex == email.length() - 1) {
            return false;
        }

        // At least one '.' after '@'
        int dotAfterAt = email.indexOf('.', atIndex);
        if (dotAfterAt == -1 || dotAfterAt == atIndex + 1) {
            return false;
        }

        return true;
    }

    // Extract components from a valid email
    public static EmailInfo extractComponents(String email) {
        if (!validateEmail(email)) {
            return new EmailInfo(email, false, "", "", "", "");
        }

        int atIndex = email.indexOf('@');
        String username = email.substring(0, atIndex);
        String domain = email.substring(atIndex + 1);  // domain after '@'

        int dotIndex = domain.lastIndexOf('.');

        String domainName = "";
        String extension = "";

        if (dotIndex != -1 && dotIndex < domain.length() - 1) {
            domainName = domain.substring(0, dotIndex);
            extension = domain.substring(dotIndex + 1);
        } else {
            domainName = domain;
        }

        return new EmailInfo(email, true, username, domain, domainName, extension);
    }

    // Analyze email statistics
    public static void analyzeEmails(List<EmailInfo> emails) {
        int validCount = 0;
        int invalidCount = 0;
        int totalUsernameLength = 0;
        Map<String, Integer> domainCount = new HashMap<>();

        for (EmailInfo info : emails) {
            if (info.isValid) {
                validCount++;
                totalUsernameLength += info.username.length();
                domainCount.put(info.domain, domainCount.getOrDefault(info.domain, 0) + 1);
            } else {
                invalidCount++;
            }
        }

        String mostCommonDomain = "";
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : domainCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostCommonDomain = entry.getKey();
            }
        }

        double averageUsernameLength = validCount == 0 ? 0 : (double) totalUsernameLength / validCount;

        System.out.println("\n--- Email Analysis ---");
        System.out.println("Total valid emails: " + validCount);
        System.out.println("Total invalid emails: " + invalidCount);
        System.out.println("Most common domain: " + (mostCommonDomain.isEmpty() ? "N/A" : mostCommonDomain));
        System.out.printf("Average username length: %.2f\n", averageUsernameLength);
    }

    // Display emails in tabular format
    public static void displayEmails(List<EmailInfo> emails) {
        System.out.printf("%-30s %-15s %-25s %-15s %-10s %-10s\n", "Email", "Valid", "Username", "Domain", "DomainName", "Extension");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        for (EmailInfo info : emails) {
            System.out.printf("%-30s %-15s %-25s %-15s %-10s %-10s\n",
                    info.email,
                    info.isValid ? "Valid" : "Invalid",
                    info.username.isEmpty() ? "-" : info.username,
                    info.domain.isEmpty() ? "-" : info.domain,
                    info.domainName.isEmpty() ? "-" : info.domainName,
                    info.extension.isEmpty() ? "-" : info.extension);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<EmailInfo> emails = new ArrayList<>();

        System.out.println("Enter email addresses (type 'done' to finish):");
        while (true) {
            String email = sc.nextLine().trim();
            if (email.equalsIgnoreCase("done")) {
                break;
            }
            EmailInfo info = extractComponents(email);
            emails.add(info);
        }

        displayEmails(emails);
        analyzeEmails(emails);

        sc.close();
    }
}
