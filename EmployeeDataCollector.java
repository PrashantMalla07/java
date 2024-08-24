import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class EmployeeDataCollector {

    public static void main(String[] args) {
        final String FILE_NAME = "employees.txt";
        Scanner scanner = new Scanner(System.in);

        // Use FileWriter in append mode by passing 'true' as the second argument
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, true))) {
            boolean continueInput = true;

            while (continueInput) {
                System.out.print("Enter employee ID: ");
                String id = scanner.nextLine();
                
                System.out.print("Enter employee name: ");
                String name = scanner.nextLine();
                
                System.out.print("Enter employee salary: ");
                String salary = scanner.nextLine();
                
                System.out.print("Enter employee city: ");
                String city = scanner.nextLine();

                writer.printf("ID: %s, Name: %s, Salary: %s, City: %s%n", id, name, salary, city);

                System.out.print("Do you want to enter another employee? (yes/no): ");
                String response = scanner.nextLine().trim().toLowerCase();

                if (response.equals("no") || response.equals("n")) {
                    continueInput = false;
                }
            }

            System.out.println("Employee data successfully appended to " + FILE_NAME);

        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
