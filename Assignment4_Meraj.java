import java.util.*;

class Subject {
    private String subjectCode;
    private String subjectName;
    private int credits;
    private String instructor;

    public Subject(String subjectCode, String subjectName, int credits, String instructor) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.credits = credits;
        this.instructor = instructor;
    }

    // Getters
    public String getSubjectCode() { return subjectCode; }
    public String getSubjectName() { return subjectName; }
    public int getCredits() { return credits; }
    public String getInstructor() { return instructor; }

    @Override
    public String toString() {
        return subjectCode + " - " + subjectName + " | Credits: " + credits + " | " + instructor;
    }
}

class Student {
    private String studentId;
    private String studentName;
    private String className;
    private String[] subjects;
    private double[] marks;  // marks aligned with subjects
    private double gpa;

    private static int totalStudents = 0;
    private static String schoolName = "Springfield High School";
    private static String[] gradingScale = {"A", "B", "C", "D", "F"};
    private static double passPercentage = 40.0;

    public Student(String studentId, String studentName, String className, String[] subjects) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.className = className;
        this.subjects = subjects;
        this.marks = new double[subjects.length];
        this.gpa = 0.0;
        totalStudents++;
    }

    // Instance methods
    public void addMarks(String subject, double score) {
        for (int i = 0; i < subjects.length; i++) {
            if (subjects[i].equalsIgnoreCase(subject)) {
                marks[i] = score;
                return;
            }
        }
        System.out.println("Subject not found: " + subject);
    }

    public void calculateGPA() {
        double total = 0;
        for (double m : marks) total += m;
        double avg = total / marks.length;
        gpa = avg / 20; // scale 0–5
    }

    public void generateReportCard() {
        System.out.println("\n==== Report Card ====");
        System.out.println("Student: " + studentName + " (" + studentId + ")");
        System.out.println("Class: " + className);
        System.out.println("School: " + schoolName);
        System.out.println("----------------------------");
        for (int i = 0; i < subjects.length; i++) {
            System.out.println(subjects[i] + ": " + marks[i] + " -> " + getGrade(marks[i]));
        }
        System.out.println("----------------------------");
        System.out.println("GPA: " + gpa);
        System.out.println("Promotion Status: " + (checkPromotionEligibility() ? "Eligible" : "Not Eligible"));
    }

    public boolean checkPromotionEligibility() {
        for (double m : marks) {
            if (m < passPercentage) return false;
        }
        return true;
    }

    private String getGrade(double mark) {
        if (mark >= 90) return gradingScale[0]; // A
        else if (mark >= 75) return gradingScale[1]; // B
        else if (mark >= 60) return gradingScale[2]; // C
        else if (mark >= 40) return gradingScale[3]; // D
        else return gradingScale[4]; // F
    }

    // Getters
    public String getStudentId() { return studentId; }
    public String getStudentName() { return studentName; }
    public String getClassName() { return className; }
    public double getGpa() { return gpa; }

    // Static methods
    public static void setGradingScale(String[] scale) {
        gradingScale = scale;
    }

    public static double calculateClassAverage(Student[] students) {
        double total = 0;
        for (Student s : students) {
            s.calculateGPA();
            total += s.gpa;
        }
        return total / students.length;
    }

    public static Student[] getTopPerformers(Student[] students, int count) {
        Arrays.sort(students, Comparator.comparingDouble(Student::getGpa).reversed());
        return Arrays.copyOfRange(students, 0, Math.min(count, students.length));
    }

    public static void generateSchoolReport(Student[] students) {
        System.out.println("\n==== School Report ====");
        System.out.println("Total Students: " + totalStudents);
        System.out.println("School Name: " + schoolName);
        System.out.println("Average GPA (Class): " + calculateClassAverage(students));
        System.out.println("Top Performers:");
        Student[] toppers = getTopPerformers(students, 3);
        for (Student s : toppers) {
            System.out.println(s.getStudentName() + " | GPA: " + s.getGpa());
        }
    }
}

public class Assignment4_Meraj {
    public static void main(String[] args) {
        // Subjects for Class 10
        String[] subjects = {"Math", "Science", "English", "History"};

        // Create students
        Student s1 = new Student("S001", "Alice", "10A", subjects);
        Student s2 = new Student("S002", "Bob", "10A", subjects);
        Student s3 = new Student("S003", "Charlie", "10B", subjects);

        // Add marks
        s1.addMarks("Math", 95);
        s1.addMarks("Science", 88);
        s1.addMarks("English", 76);
        s1.addMarks("History", 82);

        s2.addMarks("Math", 65);
        s2.addMarks("Science", 72);
        s2.addMarks("English", 54);
        s2.addMarks("History", 49);

        s3.addMarks("Math", 45);
        s3.addMarks("Science", 38); // fail case
        s3.addMarks("English", 67);
        s3.addMarks("History", 73);

        // Calculate GPA
        s1.calculateGPA();
        s2.calculateGPA();
        s3.calculateGPA();

        // Generate individual report cards
        s1.generateReportCard();
        s2.generateReportCard();
        s3.generateReportCard();

        // School report
        Student[] allStudents = {s1, s2, s3};
        Student.generateSchoolReport(allStudents);
    }
}
