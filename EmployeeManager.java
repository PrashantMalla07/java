import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class EmployeeManager {

    private static final String FILE_NAME = "employees.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEmployee Manager");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Delete Employee by ID");
            System.out.println("4. Update Employee by ID");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addEmployee(scanner);
                    break;
                case 2:
                    viewEmployees();
                    break;
                case 3:
                    deleteEmployeeById(scanner);
                    break;
                case 4:
                    updateEmployeeById(scanner);
                    break;
                case 5:
                    System.out.println("Exiting program.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Method to add an employee
    private static void addEmployee(Scanner scanner) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, true))) {
            System.out.print("Enter employee ID: ");
            String id = scanner.nextLine();
            
            System.out.print("Enter employee name: ");
            String name = scanner.nextLine();
            
            System.out.print("Enter employee salary: ");
            String salary = scanner.nextLine();
            
            System.out.print("Enter employee city: ");
            String city = scanner.nextLine();

            writer.printf("ID: %s, Name: %s, Salary: %s, City: %s%n", id, name, salary, city);

            System.out.println("Employee data added successfully.");

        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    // Method to view all employees
    private static void viewEmployees() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.println("\nEmployee Data:");
            System.out.println("==============");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    // Method to delete an employee by ID
    private static void deleteEmployeeById(Scanner scanner) {
        System.out.print("Enter the employee ID to delete: ");
        String idToDelete = scanner.nextLine().trim();

        String tempFileName = "temp_employees.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
             PrintWriter writer = new PrintWriter(new FileWriter(tempFileName))) {

            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                if (!line.contains("ID: " + idToDelete + ",")) {
                    writer.println(line);
                } else {
                    found = true;
                }
            }

            if (found) {
                System.out.println("Employee with ID " + idToDelete + " deleted successfully.");
            } else {
                System.out.println("Employee ID " + idToDelete + " not found.");
            }

        } catch (IOException e) {
            System.err.println("An error occurred while processing the file: " + e.getMessage());
        }

        // Replace the original file with the updated file
        try {
            java.nio.file.Files.delete(java.nio.file.Paths.get(FILE_NAME));
            java.nio.file.Files.move(java.nio.file.Paths.get(tempFileName), java.nio.file.Paths.get(FILE_NAME));
        } catch (IOException e) {
            System.err.println("An error occurred while updating the file: " + e.getMessage());
        }
    }

    // Method to update an employee by ID
    private static void updateEmployeeById(Scanner scanner) {
        System.out.print("Enter the employee ID to update: ");
        String idToUpdate = scanner.nextLine().trim();

        String tempFileName = "temp_employees.txt";
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
             PrintWriter writer = new PrintWriter(new FileWriter(tempFileName))) {

            String line;

            while ((line = reader.readLine()) != null) {
                if (line.contains("ID: " + idToUpdate + ",")) {
                    // Prompt user for new data
                    System.out.println("Updating employee with ID " + idToUpdate);
                    
                    System.out.print("Enter new employee name: ");
                    String name = scanner.nextLine();
                    
                    System.out.print("Enter new employee salary: ");
                    String salary = scanner.nextLine();
                    
                    System.out.print("Enter new employee city: ");
                    String city = scanner.nextLine();

                    // Write the updated record
                    writer.printf("ID: %s, Name: %s, Salary: %s, City: %s%n", idToUpdate, name, salary, city);
                    found = true;
                } else {
                    // Write the old record
                    writer.println(line);
                }
            }

        } catch (IOException e) {
            System.err.println("An error occurred while processing the file: " + e.getMessage());
        }

        if (found) {
            // Replace the original file with the updated file
            try {
                java.nio.file.Files.delete(java.nio.file.Paths.get(FILE_NAME));
                java.nio.file.Files.move(java.nio.file.Paths.get(tempFileName), java.nio.file.Paths.get(FILE_NAME));
                System.out.println("Employee with ID " + idToUpdate + " updated successfully.");
            } catch (IOException e) {
                System.err.println("An error occurred while updating the file: " + e.getMessage());
            }
        } else {
            System.out.println("Employee ID " + idToUpdate + " not found.");
            // Delete temp file if no update was made
            try {
                java.nio.file.Files.delete(java.nio.file.Paths.get(tempFileName));
            } catch (IOException e) {
                System.err.println("An error occurred while deleting the temporary file: " + e.getMessage());
            }
        }
    }
}
