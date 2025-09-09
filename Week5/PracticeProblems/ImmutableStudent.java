import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public final class ImmutableStudent {
    private final String studentId;
    private final String name;
    private final LocalDate birthDate;
    private final List<String> courses;
    private final Map<String, Double> grades;
    private final LocalDate graduationDate;

    public ImmutableStudent(String studentId, String name, LocalDate birthDate,
                            List<String> courses, Map<String, Double> grades,
                            LocalDate graduationDate) {
        if (studentId == null || studentId.isEmpty()) throw new IllegalArgumentException("studentId required");
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("name required");
        if (birthDate == null) throw new IllegalArgumentException("birthDate required");
        if (courses == null) throw new IllegalArgumentException("courses required");
        if (grades == null) throw new IllegalArgumentException("grades required");

        this.studentId = studentId;
        this.name = name;
        this.birthDate = birthDate;
        this.courses = Collections.unmodifiableList(new ArrayList<>(courses));
        this.grades = Collections.unmodifiableMap(new HashMap<>(grades));
        this.graduationDate = graduationDate;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public List<String> getCourses() {
        return new ArrayList<>(courses);
    }

    public Map<String, Double> getGrades() {
        return new HashMap<>(grades);
    }

    public LocalDate getGraduationDate() {
        return graduationDate;
    }

    public int getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public double getGPA() {
        if (grades.isEmpty()) return 0.0;
        double total = 0;
        for (double grade : grades.values()) {
            total += grade;
        }
        return total / grades.size();
    }

    public int getTotalCourses() {
        return courses.size();
    }

    public boolean isGraduated() {
        return graduationDate != null;
    }

    public ImmutableStudent withGraduationDate(LocalDate date) {
        return new ImmutableStudent(studentId, name, birthDate, courses, grades, date);
    }

    public ImmutableStudent withAdditionalCourse(String course) {
        if (course == null || course.isEmpty()) throw new IllegalArgumentException("course required");
        List<String> newCourses = new ArrayList<>(this.courses);
        newCourses.add(course);
        return new ImmutableStudent(studentId, name, birthDate, newCourses, grades, graduationDate);
    }

    public ImmutableStudent withGrade(String course, double grade) {
        if (course == null || course.isEmpty()) throw new IllegalArgumentException("course required");
        Map<String, Double> newGrades = new HashMap<>(this.grades);
        newGrades.put(course, grade);
        return new ImmutableStudent(studentId, name, birthDate, courses, newGrades, graduationDate);
    }

    public ImmutableStudent withName(String newName) {
        if (newName == null || newName.isEmpty()) throw new IllegalArgumentException("name required");
        return new ImmutableStudent(studentId, newName, birthDate, courses, grades, graduationDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImmutableStudent)) return false;
        ImmutableStudent that = (ImmutableStudent) o;
        return studentId.equals(that.studentId) &&
               name.equals(that.name) &&
               birthDate.equals(that.birthDate) &&
               courses.equals(that.courses) &&
               grades.equals(that.grades) &&
               Objects.equals(graduationDate, that.graduationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, name, birthDate, courses, grades, graduationDate);
    }

    @Override
    public String toString() {
        return "ImmutableStudent{" +
               "studentId='" + studentId + '\'' +
               ", name='" + name + '\'' +
               ", birthDate=" + birthDate +
               ", courses=" + courses +
               ", grades=" + grades +
               ", graduationDate=" + graduationDate +
               '}';
    }

    public static class Builder {
        private String studentId;
        private String name;
        private LocalDate birthDate;
        private List<String> courses = new ArrayList<>();
        private Map<String, Double> grades = new HashMap<>();
        private LocalDate graduationDate;

        public Builder setStudentId(String studentId) {
            this.studentId = studentId;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setBirthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Builder setCourses(List<String> courses) {
            this.courses = new ArrayList<>(courses);
            return this;
        }

        public Builder setGrades(Map<String, Double> grades) {
            this.grades = new HashMap<>(grades);
            return this;
        }

        public Builder setGraduationDate(LocalDate graduationDate) {
            this.graduationDate = graduationDate;
            return this;
        }

        public ImmutableStudent build() {
            if (studentId == null || studentId.isEmpty()) throw new IllegalStateException("studentId required");
            if (name == null || name.isEmpty()) throw new IllegalStateException("name required");
            if (birthDate == null) throw new IllegalStateException("birthDate required");
            if (courses == null) courses = new ArrayList<>();
            if (grades == null) grades = new HashMap<>();
            return new ImmutableStudent(studentId, name, birthDate, courses, grades, graduationDate);
        }
    }

    public static ImmutableStudent createBasicStudent(String id, String name, LocalDate birthDate) {
        return new ImmutableStudent(id, name, birthDate, Collections.emptyList(), Collections.emptyMap(), null);
    }

    public static ImmutableStudent createGraduatedStudent(String id, String name, LocalDate birthDate, LocalDate graduationDate) {
        return new ImmutableStudent(id, name, birthDate, Collections.emptyList(), Collections.emptyMap(), graduationDate);
    }

    public static void main(String[] args) {
        List<String> courses = new ArrayList<>(Arrays.asList("Math", "Science"));
        Map<String, Double> grades = new HashMap<>();
        grades.put("Math", 95.0);
        grades.put("Science", 87.0);

        ImmutableStudent student = new ImmutableStudent("S123", "Alice", LocalDate.of(2000, 1, 15),
                                                        courses, grades, null);

        // Modify original collections (should NOT affect student)
        courses.add("History");
        grades.put("History", 75.0);

        System.out.println(student.getCourses()); // should NOT include History
        System.out.println(student.getGrades());  // should NOT include History grade

        // Get defensive copies and modify those (should NOT affect student)
        List<String> studentCourses = student.getCourses();
        studentCourses.add("Art");
        Map<String, Double> studentGrades = student.getGrades();
        studentGrades.put("Art", 100.0);

        System.out.println(student.getCourses()); // unchanged
        System.out.println(student.getGrades());  // unchanged

        // Using withXXX methods to get new instances
        ImmutableStudent updatedStudent = student.withAdditionalCourse("History").withGrade("History", 75.0);

        System.out.println(updatedStudent.getCourses()); // includes History
        System.out.println(updatedStudent.getGrades());  // includes History grade

        // Builder usage
        ImmutableStudent builtStudent = new ImmutableStudent.Builder()
                .setStudentId("S456")
                .setName("Bob")
                .setBirthDate(LocalDate.of(1999, 5, 20))
                .setCourses(Arrays.asList("Physics"))
                .setGrades(Collections.singletonMap("Physics", 90.0))
                .build();

        System.out.println(builtStudent);

        // Additional tests like using in HashSet, HashMap, multithread safety can be done as per problem requirements
    }
}
