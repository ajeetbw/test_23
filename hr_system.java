import java.util.ArrayList;
import java.util.List;

// Custom Exception for business logic validation
class InvalidSalaryException extends Exception {
    public InvalidSalaryException(String message) {
        super(message);
    }
}

// Interface defining core operational behaviors
interface Payable {
    double calculatePay();
}

// Abstract base class leveraging encapsulation
abstract class Employee implements Payable {
    private final String id;
    private final String name;
    protected double baseSalary;

    public Employee(String id, String name, double baseSalary) throws InvalidSalaryException {
        if (baseSalary < 0) {
            throw new InvalidSalaryException("Base salary cannot be negative.");
        }
        this.id = id;
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public String getId() { return id; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return String.format("ID: %s | Name: %s | Net Pay: $%.2f", id, name, calculatePay());
    }
}

// Subclass implementing a specific payroll model
class SalariedEmployee extends Employee {
    private final double performanceBonus;

    public SalariedEmployee(String id, String name, double baseSalary, double performanceBonus) 
            throws InvalidSalaryException {
        super(id, name, baseSalary);
        if (performanceBonus < 0) {
            throw new InvalidSalaryException("Performance bonus cannot be negative.");
        }
        this.performanceBonus = performanceBonus;
    }

    @Override
    public double calculatePay() {
        return baseSalary + performanceBonus;
    }
}

// Subclass implementing an hourly wage calculation model
class HourlyEmployee extends Employee {
    private final double hourlyRate;
    private final int hoursWorked;

    public HourlyEmployee(String id, String name, double hourlyRate, int hoursWorked) 
            throws InvalidSalaryException {
        super(id, name, 0); // Base salary handled dynamically via hours worked
        if (hourlyRate < 0 || hoursWorked < 0) {
            throw new InvalidSalaryException("Hourly rate and hours worked must be non-negative.");
        }
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculatePay() {
        // Standard hours + time-and-a-half overtime calculation
        if (hoursWorked <= 40) {
            return hoursWorked * hourlyRate;
        } else {
            return (40 * hourlyRate) + ((hoursWorked - 40) * hourlyRate * 1.5);
        }
    }
}

// Registry Manager to orchestrate application workflow
class HRDepartment {
    private final List<Employee> staffDirectory;

    public HRDepartment() {
        this.staffDirectory = new ArrayList<>();
    }

    public void onboardEmployee(Employee emp) {
        for (Employee existing : staffDirectory) {
            if (existing.getId().equalsIgnoreCase(emp.getId())) {
                System.out.println("Error: Employee ID " + emp.getId() + " already exists.");
                return;
            }
        }
        staffDirectory.add(emp);
        System.out.println("Success: Onboarded " + emp.getName());
    }

    public void processingPayroll() {
        System.out.println("\n================ EXECUTING COMPANY PAYROLL ================");
        if (staffDirectory.isEmpty()) {
            System.out.println("No registered records found inside corporate registry.");
            return;
        }
        double totalExpense = 0;
        for (Employee emp : staffDirectory) {
            System.out.println(emp);
            totalExpense += emp.calculatePay();
        }
        System.out.println("-----------------------------------------------------------");
        System.out.printf("Total Corporate Monthly Outflow: $%.2f\n", totalExpense);
        System.out.println("===========================================================");
    }
}

public class CorporatePayrollEngine {
    public static void main(String[] args) {
        HRDepartment hr = new HRDepartment();

        System.out.println("--- Processing Corporate Onboarding Registries ---");
        try {
            hr.onboardEmployee(new SalariedEmployee("E001", "Marcus Vance", 5500.00, 750.00));
            hr.onboardEmployee(new HourlyEmployee("E002", "Elena Rostova", 25.50, 45)); // Includes 5hrs overtime
            hr.onboardEmployee(new SalariedEmployee("E003", "Jonathan Wick", 7200.00, 0.00));
            
            // Testing duplication boundary constraint checks
            hr.onboardEmployee(new SalariedEmployee("E001", "Imposter Duplicate", 4000, 0));
        } catch (InvalidSalaryException ex) {
            System.out.println("Validation Error on processing onboarding: " + ex.getMessage());
        }

        // Executing mathematical workflow processes
        hr.processingPayroll();

        System.out.println("\n--- Testing Exception Guardrails ---");
        try {
            // This transaction item will actively fail validation boundaries
            hr.onboardEmployee(new SalariedEmployee("E004", "Broken Record", -1500, 200));
        } catch (InvalidSalaryException ex) {
            System.out.println("System successfully intercepted error: " + ex.getMessage());
        }
    }
}

