import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DisplayHighSalaryEmployees {

    public static void main(String[] args) {
        final String FILE_NAME = "employees.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line by commas
                String[] parts = line.split(", ");
                if (parts.length == 3) {
                    try {
                        // Extract the salary part and parse it
                        String salaryPart = parts[2].trim(); // e.g., "Salary: 12000"
                        String salaryString = salaryPart.substring(salaryPart.indexOf(':') + 1).trim(); // Extract number part
                        int salary = Integer.parseInt(salaryString);

                        // Check if the salary is greater than 10,000
                        if (salary > 170000) {
                            System.out.println(line); // Print the whole line (employee details)
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing salary: " + e.getMessage());
                    }
                } else {
                    System.err.println("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
    }
}