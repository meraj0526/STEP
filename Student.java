// Define the Student class
public class Student {
    // Fields (attributes)
    private String name;
    private int age;
    private String studentId;
    private String course;

    // Constructor to initialize the Student object
    public Student(String name, int age, String studentId, String course) {
        this.name = name;
        this.age = age;
        this.studentId = studentId;
        this.course = course;
    }

    // Getter methods to access private fields
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getCourse() {
        return course;
    }

    // Setter methods to modify private fields
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    // Method to display student information
    public void displayStudentInfo() {
        System.out.println("Student Details:");
        System.out.println("Name      : " + name);
        System.out.println("Age       : " + age);
        System.out.println("Student ID: " + studentId);
        System.out.println("Course    : " + course);
    }

    // Main method to create and test Student objects
    public static void main(String[] args) {
        // Create a Student object using constructor
        Student student1 = new Student("Alice Johnson", 20, "S12345", "Computer Science");

        // Display the student information
        student1.displayStudentInfo();

        // Modify some attributes using setter methods
        student1.setAge(21);
        student1.setCourse("Software Engineering");

        System.out.println("\nUpdated Student Details:");
        student1.displayStudentInfo();
    }
}
