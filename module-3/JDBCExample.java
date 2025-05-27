import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class JDBCExample {
    // JDBC URL, username, and password of MySQL server
    private static final String URL = "jdbc:mysql://localhost:3306/school";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        try {
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Open a connection
            System.out.println("Connecting to database...");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            
            if (conn != null) {
                System.out.println("Successfully connected to the database!");
                
                // Create statement
                Statement stmt = conn.createStatement();
                
                // Create table if not exists
                String createTableSQL = "CREATE TABLE IF NOT EXISTS students " +
                    "(id INT PRIMARY KEY AUTO_INCREMENT, " +
                    " name VARCHAR(50), " +
                    " age INT, " +
                    " grade FLOAT)";
                stmt.execute(createTableSQL);
                
                // Insert sample data
                String insertSQL = "INSERT INTO students (name, age, grade) VALUES " +
                    "('John Doe', 20, 85.5), " +
                    "('Jane Smith', 22, 92.0), " +
                    "('Bob Johnson', 21, 78.5)";
                stmt.execute("DELETE FROM students"); // Clear existing data
                stmt.executeUpdate(insertSQL);
                
                // Execute SELECT query
                String selectSQL = "SELECT * FROM students";
                ResultSet rs = stmt.executeQuery(selectSQL);
                
                // Process the result set
                System.out.println("\nStudent Records:");
                System.out.println("ID\tName\t\tAge\tGrade");
                System.out.println("----------------------------------------");
                
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    float grade = rs.getFloat("grade");
                    
                    System.out.printf("%d\t%-12s\t%d\t%.1f%n", 
                        id, name, age, grade);
                }
                
                // Close resources
                rs.close();
                stmt.close();
                conn.close();
            }
            
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed! Check output console");
            e.printStackTrace();
        }
    }
} 