/*
Revise the 39.9 Example: Registering Students (Listings 39.16, 39.17, 39.18, and 39.19)
to register staff information in Staff table using Managed Beans in JavaServer
Faces Technology
*/

package Ass10;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.sql.*;
import java.io.*;

/**
 *
 * @author moaabdel
 */
@Named(value = "staffRegistration")
@SessionScoped
public class StaffRegistrationJSFBean implements Serializable {
  private String id;
  private String firstName;
  private String mi;
  private String lastName;
  private String telephone;
  private String address;
  private String city;
  private String state;
  private String email;
  private String status = "Nothing stored";
  
  // Use a prepared statement to store a student into the database
  private PreparedStatement pstmt;

  public StaffRegistrationJSFBean() {
    initializeJdbc();
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

  public String getAddress()  {
    return this.address;
  }
    
  public void setAddress(String address)  {
    this.address = address;
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
  public String getId() {
    return this.id;
  }  
  public void setId(String id) {
    this.id = id;
  }

  private boolean isRquiredFieldsFilled() {
    return !(lastName == null || firstName == null
            || lastName.trim().length() == 0
            || firstName.trim().length() == 0);
  }

  public String processSubmit() {
    if (isRquiredFieldsFilled()) {
      return "ConfirmStaff";
    } else {
      return "";
    }
  }

  public String getRequiredFields() {
    if (isRquiredFieldsFilled()) {
      return "";
    } else {
      return "Last Name and First Name are required";
    }
  }

  public String getInput() {
    return "<p style=\"color:red\">You entered <br />"
            + "ID: " + id + "<br />"
            + "Last Name: " + lastName + "<br />"
            + "First Name: " + firstName + "<br />"
            + "MI: " + mi + "<br />"
            + "Telephone: " + telephone + "<br />"
            + "Address: " + address + "<br />"
            + "City: " + city + "<br />"
            + "State: " + state + "<br />"
            + "Email: " + email + "<br />";
  }

  /** Initialize database connection */
  private void initializeJdbc() {
    try {
	 // Load the JDBC driver
	 Class.forName("com.mysql.jdbc.Driver");
	 System.out.println("Driver loaded");
	
	 // Establish a connection
	 Connection conn = DriverManager.getConnection
	   ("jdbc:mysql://localhost/test" , "INFO211", "java2");
	 System.out.println("Database connected");

      // Create a Statement
      pstmt = conn.prepareStatement("insert into Staff " +
        "(id, lastName, firstName, mi, telephone, email, address, city, "
        + "state) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
    }
    catch (Exception ex) {
      System.out.println(ex);
    }
  }

  /** Store an address to the database */
  public String storeStaff() {
    try {
      pstmt.setString(1, id);
      pstmt.setString(2, lastName);
      pstmt.setString(3, firstName);
      pstmt.setString(4, mi);
      pstmt.setString(5, telephone);
      pstmt.setString(6, email);
      pstmt.setString(7, address);
      pstmt.setString(8, city);
      pstmt.setString(9, state);
      
      pstmt.executeUpdate();
      
      status = firstName + " " + lastName
              + " is now registered in the database.";
      
   } catch (Exception ex) {
      status = ex.getMessage();
    }
    return "StaffStoredStatus";
  }

  public String getStatus() {
    return status;
  }
}