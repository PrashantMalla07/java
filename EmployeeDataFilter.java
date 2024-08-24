import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class EmployeeDataFilter {

    public static void main(String[] args) {
        final String INPUT_FILE = "employees.txt";
        final String OUTPUT_FILE = "filtered_employees.txt";
        final String EXCLUDED_CITY = "kathmandu";

        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE));
             BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.contains("City." + EXCLUDED_CITY)) {
                    writer.write(line);
                    writer.newLine(); // Write a newline character after each line
                }
            }

            System.out.println("Filtered data successfully written to " + OUTPUT_FILE);

        } catch (IOException e) {
            System.err.println("An error occurred while processing the files: " + e.getMessage());
        }
    }
}
