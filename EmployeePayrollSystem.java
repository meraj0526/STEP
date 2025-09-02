public class EmployeePayrollSystem {
    public static void main(String[] args) {
        Employee fullTimeEmp = new Employee("Alice", "HR", 40000, "Full-Time");
        Employee partTimeEmp = new Employee("Bob", "IT", 200, 40, "Part-Time");
        Employee contractEmp = new Employee("Charlie", "Finance", 60000, "Contract", true);

        System.out.println("\n--- Employee Information ---");
        fullTimeEmp.displayEmployeeInfo();
        partTimeEmp.displayEmployeeInfo();
        contractEmp.displayEmployeeInfo();

        System.out.println("\n--- Pay Slips ---");
        fullTimeEmp.generatePaySlip(5000);
        partTimeEmp.generatePaySlip();
        contractEmp.generatePaySlip();

        System.out.println("\n--- Company Payroll Report ---");
        Employee[] staff = { fullTimeEmp, partTimeEmp, contractEmp };
        Employee.printCompanyPayrollReport(staff);

        System.out.println("\nTotal Employees: " + Employee.getTotalEmployees());
    }
}

class Employee {
    private String empId;
    private String empName;
    private String department;
    private double baseSalary;
    private String empType;
    private double hourlyRate;
    private int hoursWorked;

    private static int totalEmployees = 0;
    private static int empCounter = 0;

    public Employee(String empName, String department, double baseSalary, String empType) {
        this.empId = generateEmpId();
        this.empName = empName;
        this.department = department;
        this.baseSalary = baseSalary;
        this.empType = empType;
        totalEmployees++;
    }

    public Employee(String empName, String department, double hourlyRate, int hoursWorked, String empType) {
        this.empId = generateEmpId();
        this.empName = empName;
        this.department = department;
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
        this.empType = empType;
        totalEmployees++;
    }

    public Employee(String empName, String department, double fixedAmount, String empType, boolean isContract) {
        this.empId = generateEmpId();
        this.empName = empName;
        this.department = department;
        this.baseSalary = fixedAmount;
        this.empType = empType;
        totalEmployees++;
    }

    public double calculateSalary(double bonus) {
        return baseSalary + bonus;
    }

    public double calculateSalary() {
        return hourlyRate * hoursWorked;
    }

    public double calculateSalary(boolean isContract) {
        return baseSalary;
    }

    public double calculateTax(double salary, double bonus) {
        double gross = salary + bonus;
        return gross * 0.10;
    }

    public double calculateTax(double hourlyRate, int hours) {
        return (hourlyRate * hours) * 0.05;
    }

    public double calculateTax(double fixedAmount, boolean isContract) {
        return fixedAmount * 0.08;
    }

    public void generatePaySlip(double bonus) {
        double gross = calculateSalary(bonus);
        double tax = calculateTax(baseSalary, bonus);
        printPaySlip(gross, tax);
    }

    public void generatePaySlip() {
        double gross, tax;
        if (empType.equalsIgnoreCase("Part-Time")) {
            gross = calculateSalary();
            tax = calculateTax(hourlyRate, hoursWorked);
        } else {
            gross = calculateSalary(true);
            tax = calculateTax(baseSalary, true);
        }
        printPaySlip(gross, tax);
    }

    private void printPaySlip(double gross, double tax) {
        System.out.println("Employee ID   : " + empId);
        System.out.println("Name          : " + empName);
        System.out.println("Department    : " + department);
        System.out.println("Type          : " + empType);
        System.out.println("Gross Salary  : " + gross);
        System.out.println("Tax Deduction : " + tax);
        System.out.println("Net Salary    : " + (gross - tax));
        System.out.println("-----------------------------------");
    }

    public void displayEmployeeInfo() {
        System.out.println("Employee ID : " + empId);
        System.out.println("Name        : " + empName);
        System.out.println("Department  : " + department);
        System.out.println("Type        : " + empType);
        System.out.println("-----------------------------------");
    }

    private static String generateEmpId() {
        empCounter++;
        return String.format("E%03d", empCounter);
    }

    public static int getTotalEmployees() {
        return totalEmployees;
    }

    public static void printCompanyPayrollReport(Employee[] employees) {
        double totalGross = 0, totalTax = 0, totalNet = 0;

        for (Employee e : employees) {
            double gross;
            double tax;

            if (e.empType.equalsIgnoreCase("Full-Time")) {
                double bonus = 0;
                gross = e.calculateSalary(bonus);
                tax = e.calculateTax(e.baseSalary, bonus);
            } else if (e.empType.equalsIgnoreCase("Part-Time")) {
                gross = e.calculateSalary();
                tax = e.calculateTax(e.hourlyRate, e.hoursWorked);
            } else {
                gross = e.calculateSalary(true);
                tax = e.calculateTax(e.baseSalary, true);
            }

            totalGross += gross;
            totalTax += tax;
            totalNet += (gross - tax);
        }

        System.out.println("Total Gross : " + totalGross);
        System.out.println("Total Tax   : " + totalTax);
        System.out.println("Total Net   : " + totalNet);
    }
}
