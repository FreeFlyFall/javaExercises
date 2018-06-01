/*
Revise the 38.9.4 Example: Registering Students, to register staff information
in Staff table using Java Server Pages.
*/

package Chapter38;

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StoreData {

    // Use a prepared statement to store a student into the database
    private PreparedStatement pstmt;

    public StoreData() {
        initializeJdbc();
    }

    /**
     * Initialize database connection
     */
    private void initializeJdbc() {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");

            // Establish a connection
            Connection conn = DriverManager.getConnection
        ("jdbc:mysql://localhost/test", "INFO211", "java2");
            System.out.println("Database connected");

            // Create a Statement
            pstmt = conn.prepareStatement("insert into Staff "
                    + "(id, lastName, firstName, mi, address, city, state, "
                    + "telephone, email) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /**
     * Store an address to the database
     */
    public void storeData(Address address) throws SQLException {
        pstmt.setString(1, address.getId());
        pstmt.setString(2, address.getLastName());
        pstmt.setString(3, address.getFirstName());
        pstmt.setString(4, address.getMi());
        pstmt.setString(5, address.getLocation());
        pstmt.setString(6, address.getCity());
        pstmt.setString(7, address.getState());
        pstmt.setString(8, address.getTelephone());
        pstmt.setString(9, address.getEmail());
        pstmt.executeUpdate();
    }
}
