import java.util.Scanner;

public class StringArrays {

    // Max capacity for simplicity
    static final int MAX_STUDENTS = 100;
    static String[] students = new String[MAX_STUDENTS];
    static int count = 0;  // current number of students

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Student List Manager ---");
            System.out.println("1. Add student");
            System.out.println("2. Display all students");
            System.out.println("3. Search for a student");
            System.out.println("4. Remove a student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    displayStudents();
                    break;
                case 3:
                    searchStudent(scanner);
                    break;
                case 4:
                    removeStudent(scanner);
                    break;
                case 5:
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 5);

        scanner.close();
    }

    // Method to add a student name
    public static void addStudent(Scanner scanner) {
        if (count >= MAX_STUDENTS) {
            System.out.println("Student list is full! Cannot add more.");
            return;
        }
        System.out.print("Enter student name to add: ");
        String name = scanner.nextLine();
        students[count] = name;
        count++;
        System.out.println("Student added: " + name);
    }

    // Method to display all student names
    public static void displayStudents() {
        if (count == 0) {
            System.out.println("No students in the list.");
            return;
        }
        System.out.println("Student List:");
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + students[i]);
        }
    }

    // Method to search for a student by name (case insensitive)
    public static void searchStudent(Scanner scanner) {
        if (count == 0) {
            System.out.println("No students to search.");
            return;
        }
        System.out.print("Enter student name to search: ");
        String searchName = scanner.nextLine().toLowerCase();

        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (students[i].toLowerCase().equals(searchName)) {
                System.out.println("Student found at position " + (i + 1));
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Student not found.");
        }
    }

    // Method to remove a student by name (case insensitive)
    public static void removeStudent(Scanner scanner) {
        if (count == 0) {
            System.out.println("No students to remove.");
            return;
        }
        System.out.print("Enter student name to remove: ");
        String removeName = scanner.nextLine().toLowerCase();

        boolean removed = false;
        for (int i = 0; i < count; i++) {
            if (students[i].toLowerCase().equals(removeName)) {
                // Shift all elements after i left by one
                for (int j = i; j < count - 1; j++) {
                    students[j] = students[j + 1];
                }
                students[count - 1] = null;  // optional: clear last slot
                count--;
                System.out.println("Student removed: " + removeName);
                removed = true;
                break;
            }
        }
        if (!removed) {
            System.out.println("Student not found.");
        }
    }
}
