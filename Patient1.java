import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient1 {
    private Connection connection;
    private Scanner scanner;

    // Constructor to initialize the connection and scanner
    public Patient1(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }

    // Method to add a patient to the database
    public void addPatient() {
        System.out.println("Enter patient name:");
        String name = scanner.next();
        System.out.println("Enter patient age:");
        int age = scanner.nextInt();
        System.out.println("Enter patient gender:");
        String gender = scanner.next();

        try {
            // Correct SQL query for inserting a patient record
            String query = "INSERT INTO patient (name, age, gender) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, gender);

            // Execute the update and check if successful
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Patient added successfully!");
            } else {
                System.out.println("Failed to add patient.");
            }

        } catch (SQLException e) {
            // Proper exception handling
            e.printStackTrace();
        }
    }

    // Method to view all patients from the database
    public void viewPatients() {
        String query = "SELECT * FROM patient";
        try {
            // Correct SQL query and PreparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Printing the results
            System.out.println("Patients:");
            System.out.println("+------------+------------+-----+--------+");
            System.out.println("| Patient ID | Name       | Age | Gender |");
            System.out.println("+------------+------------+-----+--------+");

            // Loop through all records and print
            while (resultSet.next()) {
                int id = resultSet.getInt("id");        // Assuming the patient ID is 'id'
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");

                System.out.println("| " + id + "         | " + name + " | " + age + "  | " + gender + "   |");
            }

            System.out.println("+------------+------------+-----+--------+");
            resultSet.close(); // Close the ResultSet
            preparedStatement.close(); // Close the PreparedStatement

        } catch (SQLException e) {
            // Proper exception handling
            e.printStackTrace();
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        // Example: Assuming 'conn' is an already established database connection
        // Create a scanner object
        Scanner scanner = new Scanner(System.in);

        // Assuming you already have a valid Connection object (conn)
        // Here, you need to establish a connection to your database
        Connection connection = null;  // Replace with actual database connection code

        // Create Patient object
        Patient1 patient = new Patient1(connection, scanner);

        // Add a patient or view patients based on user choice
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Add Patient");
            System.out.println("2. View Patients");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    patient.addPatient();
                    break;
                case 2:
                    patient.viewPatients();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
