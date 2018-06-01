/*
Revise both the servlet file named RegistrationWithHttpSession.java in Listing 37.11 and
RegistrationWithHttpSession.html file, to register staff information in Staff table
using session tracking.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class Ass7StaffRegistrationWithHttpSession extends HttpServlet {

    // Use a prepared statement to store an employee into the database
    private PreparedStatement pstmt;

    /**
     * Initialize variables
     */
    public void init() throws ServletException {
        initializeJdbc();
    }

    /**
     * Process the HTTP Get request
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set response type and output stream to the browser
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Obtain data from the form
        String id = request.getParameter("id");
        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        String mi = request.getParameter("mi");
        String addresstf = request.getParameter("address");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String telephone = request.getParameter("telephone");
        String email = request.getParameter("email");

        if (id.length() == 0 || lastName.length() == 0 || firstName.length() == 0) {
            out.println("ID, Last Name, and First Name are required");
        } else {
            // Create an Address object
            Address address = new Address();
            address.setId(id);
            address.setLastName(lastName);
            address.setFirstName(firstName);
            address.setMi(mi);
            address.setAddresstf(addresstf);
            address.setCity(city);
            address.setState(state);
            address.setTelephone(telephone);
            address.setEmail(email);

            // Get an HttpSession or create one if it does not exist
            HttpSession httpSession = request.getSession();

            // Store staff object to the session
            httpSession.setAttribute("address", address);

            // Ask for confirmation
            out.println("You entered the following data");
            out.println("<p>ID: " + id);
            out.println("<p>Last name: " + lastName);
            out.println("<p>First name: " + firstName);
            out.println("<p>MI: " + mi);
            out.println("<p>address: " + addresstf);
            out.println("<p>City: " + city);
            out.println("<p>State: " + state);
            out.println("<p>Telephone: " + telephone);
            out.println("<p>Email: " + email);

            // Set the action for processing the answers
            out.println("<p><form method=\"post\" action="
                    + "Ass7StaffRegistrationWithHttpSession>");
            out.println("<p><input type=\"submit\" value=\"Confirm\" >");
            out.println("</form>");
        }

        out.close(); // Close stream
    }

    /**
     * Process the HTTP Post request
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set response type and output stream to the browser
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Obtain the HttpSession
        HttpSession httpSession = request.getSession();

        // Get the Address object in the HttpSession
        Address address = (Address) (httpSession.getAttribute("address"));

        try {
            storeStaff(address);

            out.println(address.getFirstName() + " " + address.getLastName()
                    + " is now registered in the database");
            out.close(); // Close stream
        } catch (Exception ex) {
            out.println("Error: " + ex.getMessage());
        }
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
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "INFO211", "java2");
            System.out.println("Database connected");

            // Create a Statement
            pstmt = conn.prepareStatement("insert into Staff "
                    + "(id, lastName, firstName, mi, address, city, state, telephone, "
                    + "email) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /**
     * Store an address to the database
     */
    private void storeStaff(Address address) throws SQLException {
        pstmt.setString(1, address.getId());
        pstmt.setString(2, address.getLastName());
        pstmt.setString(3, address.getFirstName());
        pstmt.setString(4, address.getMi());
        pstmt.setString(5, address.getAddresstf());
        pstmt.setString(6, address.getCity());
        pstmt.setString(7, address.getState());
        pstmt.setString(8, address.getTelephone());
        pstmt.setString(9, address.getEmail());
        pstmt.executeUpdate();
    }
}

class Address {

    private String id;
    private String lastName;
    private String firstName;
    private String mi;
    private String address;
    private String city;
    private String state;
    private String telephone;
    private String email;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddresstf() {
        return this.address;
    }

    public void setAddresstf(String addresstf) {
        this.address = addresstf;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMi() {
        return this.mi;
    }

    public void setMi(String mi) {
        this.mi = mi;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
