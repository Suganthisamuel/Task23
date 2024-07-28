package SQLDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcInsertExample {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Load JDBC driver (optional for newer versions)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            String url = "jdbc:mysql://localhost:3306/EmployeeDB";
            String username = "root";
            String password = "Suganthi@0425";
            connection = DriverManager.getConnection(url, username, password);

            // Prepare SQL insert statement
            String insertSQL = "INSERT INTO EmployeeSQL (empcode, empname,empage, esalary) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(insertSQL);

            // Insert data
            insertEmployeeSQL(preparedStatement, 101, "Jenny", 25, 10000);
            insertEmployeeSQL(preparedStatement, 102, "Jacky", 30, 20000);
            insertEmployeeSQL(preparedStatement, 103, "Joe", 20, 40000);
            insertEmployeeSQL(preparedStatement, 104, "John", 40, 80000);
            insertEmployeeSQL(preparedStatement, 105, "Shame", 25, 90000);

            System.out.println("Data inserted successfully.");
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        } finally {
            // Close resources
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
    }

    private static void insertEmployeeSQL(PreparedStatement preparedStatement, int empcode, String empname, int empage, double esalary) throws SQLException {
        preparedStatement.setInt(1, empcode);
        preparedStatement.setString(2, empname);
        preparedStatement.setInt(3, empage);
        preparedStatement.setDouble(4, esalary);
        preparedStatement.executeUpdate();
    }
}
