import java.util.*;

abstract class Employee {
    protected String empId;
    protected String empName;
    protected String department;
    protected String designation;
    protected double baseSalary;
    protected String joinDate;
    protected boolean[] attendanceRecord;
    protected int leavesTaken;

    protected static int totalEmployees = 0;
    protected static String companyName = "TechCorp Solutions";
    protected static double totalSalaryExpense = 0.0;
    protected static int workingDaysPerMonth = 22;

    public Employee(String empId, String empName, String department, String designation, double baseSalary, String joinDate) {
        this.empId = empId;
        this.empName = empName;
        this.department = department;
        this.designation = designation;
        this.baseSalary = baseSalary;
        this.joinDate = joinDate;
        this.attendanceRecord = new boolean[30];
        this.leavesTaken = 0;
        totalEmployees++;
    }

    public void markAttendance(int day, boolean present) {
        if (day < 1 || day > 30) return;
        attendanceRecord[day - 1] = present;
        if (!present) leavesTaken++;
    }

    public abstract double calculateSalary();

    public double calculateBonus() {
        int presentDays = 0;
        for (boolean present : attendanceRecord) {
            if (present) presentDays++;
        }
        double attendanceRate = (presentDays / (double) workingDaysPerMonth) * 100;
        if (attendanceRate >= 95) return baseSalary * 0.20;
        else if (attendanceRate >= 85) return baseSalary * 0.10;
        else return baseSalary * 0.05;
    }

    public void generatePaySlip() {
        double salary = calculateSalary();
        double bonus = calculateBonus();
        totalSalaryExpense += salary + bonus;
        System.out.println("\n===== Pay Slip for " + empName + " =====");
        System.out.println("Employee ID: " + empId);
        System.out.println("Designation: " + designation);
        System.out.println("Base Salary: $" + baseSalary);
        System.out.println("Calculated Salary: $" + salary);
        System.out.println("Bonus: $" + bonus);
        System.out.println("Total: $" + (salary + bonus));
    }

    public void requestLeave(int days) {
        if (days <= 3) {
            System.out.println("Leave approved for " + empName + " (" + days + " days)");
        } else {
            System.out.println("Leave request denied for " + empName + " (" + days + " days exceeds limit)");
        }
    }

    public String getDepartment() { return department; }
    public String getEmpName() { return empName; }
    public double getBaseSalary() { return baseSalary; }
}

class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String empId, String empName, String department, String designation, double baseSalary, String joinDate) {
        super(empId, empName, department, designation, baseSalary, joinDate);
    }
    @Override
    public double calculateSalary() {
        return baseSalary;
    }
}

class PartTimeEmployee extends Employee {
    private int hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(String empId, String empName, String department, String designation, double hourlyRate, String joinDate) {
        super(empId, empName, department, designation, hourlyRate * 80, joinDate);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = 0;
    }

    public void logHours(int hours) {
        this.hoursWorked += hours;
    }

    @Override
    public double calculateSalary() {
        return hoursWorked * hourlyRate;
    }
}

class ContractEmployee extends Employee {
    private int contractDuration;
    public ContractEmployee(String empId, String empName, String department, String designation, double baseSalary, String joinDate, int contractDuration) {
        super(empId, empName, department, designation, baseSalary, joinDate);
        this.contractDuration = contractDuration;
    }
    @Override
    public double calculateSalary() {
        return baseSalary * 0.9;
    }
}

class Department {
    private String deptId;
    private String deptName;
    private Employee manager;
    private List<Employee> employees;
    private double budget;

    public Department(String deptId, String deptName, Employee manager, double budget) {
        this.deptId = deptId;
        this.deptName = deptName;
        this.manager = manager;
        this.budget = budget;
        this.employees = new ArrayList<>();
        addEmployee(manager);
    }

    public void addEmployee(Employee emp) {
        employees.add(emp);
    }

    public double getDepartmentExpense() {
        double expense = 0;
        for (Employee e : employees) {
            expense += e.calculateSalary() + e.calculateBonus();
        }
        return expense;
    }

    public String getDeptName() { return deptName; }
    public List<Employee> getEmployees() { return employees; }
}

class CompanyReports {
    public static void calculateCompanyPayroll(List<Department> departments) {
        double payroll = 0;
        for (Department d : departments) {
            payroll += d.getDepartmentExpense();
        }
        System.out.println("\nTotal Company Payroll: $" + payroll);
    }

    public static void getDepartmentWiseExpenses(List<Department> departments) {
        System.out.println("\nDepartment Wise Expenses:");
        for (Department d : departments) {
            System.out.println(d.getDeptName() + ": $" + d.getDepartmentExpense());
        }
    }

    public static void getAttendanceReport(List<Department> departments) {
        System.out.println("\nAttendance Report:");
        for (Department d : departments) {
            for (Employee e : d.getEmployees()) {
                int presentDays = 0;
                for (boolean p : e.attendanceRecord) if (p) presentDays++;
                System.out.println(e.getEmpName() + " (" + d.getDeptName() + ") → Present Days: " + presentDays);
            }
        }
    }
}

public class Assignment6_Meraj {
    public static void main(String[] args) {
        FullTimeEmployee e1 = new FullTimeEmployee("E001", "Alice", "IT", "Developer", 6000, "01-01-2020");
        PartTimeEmployee e2 = new PartTimeEmployee("E002", "Bob", "HR", "Assistant", 20, "01-02-2021");
        ContractEmployee e3 = new ContractEmployee("E003", "Charlie", "Finance", "Consultant", 5000, "01-03-2022", 12);

        Department it = new Department("D001", "IT", e1, 100000);
        it.addEmployee(e2);
        Department finance = new Department("D002", "Finance", e3, 80000);

        List<Department> departments = Arrays.asList(it, finance);

        e1.markAttendance(1, true);
        e1.markAttendance(2, true);
        e2.markAttendance(1, false);
        e2.markAttendance(2, true);
        e3.markAttendance(1, true);

        e2.logHours(90);

        e1.requestLeave(2);
        e2.requestLeave(4);

        e1.generatePaySlip();
        e2.generatePaySlip();
        e3.generatePaySlip();

        CompanyReports.calculateCompanyPayroll(departments);
        CompanyReports.getDepartmentWiseExpenses(departments);
        CompanyReports.getAttendanceReport(departments);
    }
}
