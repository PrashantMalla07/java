import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class EmployeeDataDeleter {

    public static void main(String[] args) {
        final String FILE_NAME = "employees.txt";
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the employee ID to delete: ");
        String idToDelete = scanner.nextLine().trim();

        // Temporary file to store updated data
        String tempFileName = "temp_employees.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
             PrintWriter writer = new PrintWriter(new FileWriter(tempFileName))) {

            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                // Check if the current line contains the ID to delete
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

        scanner.close();
    }
}
