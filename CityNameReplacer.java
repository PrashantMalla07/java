import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CityNameReplacer {

    public static void main(String[] args) {
        final String INPUT_FILE = "employees.txt";
        final String OUTPUT_FILE = "updated_employees.txt";
        final String OLD_CITY = "kathmandu";
        final String NEW_CITY = "jhapa";

        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE));
             BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE))) {

            String line;
            while ((line = reader.readLine()) != null) {
                // Replace occurrences of OLD_CITY with NEW_CITY
                String updatedLine = line.replace("City." + OLD_CITY, "City." + NEW_CITY);
                writer.write(updatedLine);
                writer.newLine(); // Write a newline character after each line
            }

            System.out.println("City names successfully updated. Check the file: " + OUTPUT_FILE);

        } catch (IOException e) {
            System.err.println("An error occurred while processing the files: " + e.getMessage());
        }
    }
}
