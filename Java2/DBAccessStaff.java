/*Create a Mysql table "Staff" in the database "test" to store staff information:

create table Staff (
 id char(9) not null,
 lastName varchar(15),
 firstName varchar(15),
 mi char(1),
 address varchar(20),
 city varchar(20),
 state char(2),
 telephone char(10),
 email varchar(40),     //email isn't used in this exercise, but is used later
 primary key (id)
);

Write a JavaFX program that views, inserts, and updates staff information stored in
MySQL database, as shown in the following figures. The view button displays a record
with a specified ID. The insert button inserts a new record. The Update button updates
the record for the specified ID. The clear button clears the text fields.

*/
package dbaccessstaff;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.sql.*;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DBAccessStaff extends Application {

    //PreparedStatement for executing queries
    private PreparedStatement preparedStatement;
    //Create user interface components
    private TextField tfID = new TextField();
    private TextField tfLastName = new TextField();
    private TextField tfFirstName = new TextField();
    private TextField tfMI = new TextField();
    private TextField tfAddress = new TextField();
    private TextField tfCity = new TextField();
    private TextField tfState = new TextField();
    private TextField tfTelephone = new TextField();
    private Label lblStatus = new Label();

    @Override
    public void start(Stage primaryStage) throws ClassNotFoundException {
        //Load mysql driver
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver loaded");

        //Format user interface components
        HBox hBox1 = new HBox(5);
        hBox1.getChildren().addAll(new Label("ID"), tfID);
        HBox hBox2 = new HBox(5);
        tfLastName.setPrefColumnCount(8);
        tfFirstName.setPrefColumnCount(8);
        tfMI.setPrefColumnCount(1);
        hBox2.getChildren().addAll(new Label("Last Name"), tfLastName,
                new Label("First Name"), tfFirstName,
                new Label("MI"), tfMI);
        HBox hBox3 = new HBox(5);
        hBox3.getChildren().addAll(new Label("Address"), tfAddress);
        HBox hBox4 = new HBox(5);
        tfState.setPrefColumnCount(2);
        hBox4.getChildren().addAll(new Label("City"), tfCity,
                new Label("State"), tfState);
        HBox hBox5 = new HBox(5);
        hBox5.getChildren().addAll(new Label("Telephone"), tfTelephone);
        //Create and format buttons
        Button btView = new Button("View");
        Button btInsert = new Button("Insert");
        Button btUpdate = new Button("Update");
        Button btClear = new Button("Clear");
        HBox hBoxButtonBox = new HBox(5);
        hBoxButtonBox.getChildren().addAll(btView, btInsert, btUpdate, btClear);
        hBoxButtonBox.setAlignment(Pos.CENTER);

        //Create action events for buttons
        btView.setOnAction(e -> view());
        btInsert.setOnAction(e -> insert());
        btUpdate.setOnAction(e -> update());
        btClear.setOnAction(e -> clear());

        //Format user interface
        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(lblStatus, hBox1, hBox2, hBox3, hBox4, hBox5,
                hBoxButtonBox);
        Scene scene = new Scene(vBox, 400, 250);

        //Set the title and scene. Show the stage.
        primaryStage.setTitle("Ass6DBAccessStaff");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //Method for viewing a record
    private void view() {
        //Set strings to the values of the text fields
        String id = tfID.getText();
        String lastName = tfLastName.getText();
        String firstName = tfFirstName.getText();
        String mi = tfMI.getText();
        String address = tfAddress.getText();
        String city = tfCity.getText();
        String state = tfState.getText();
        String telephone = tfTelephone.getText();

        //Create a query String
        String queryString = "select lastName, firstName, mi, address, "
                + "city, state, telephone from Staff where id = ?;";
        try {
            //Establish a connection
            Connection connection
                    = DriverManager.getConnection("jdbc:mysql://localhost/test",
                            "INFO211", "java2");
            System.out.println("Database connected");

            //Use the prepared statement and execute the query
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, id);
            ResultSet rset = preparedStatement.executeQuery();

            //If the record is found, set the strings to the values of the
            //result set
            if (rset.next()) {
                lastName = rset.getString(1);
                firstName = rset.getString(2);
                mi = rset.getString(3);
                address = rset.getString(4);
                city = rset.getString(5);
                state = rset.getString(6);
                telephone = rset.getString(7);
                //Display the results
                tfLastName.setText(lastName);
                tfFirstName.setText(firstName);
                tfMI.setText(mi);
                tfAddress.setText(address);
                tfCity.setText(city);
                tfState.setText(state);
                tfTelephone.setText(telephone);
                lblStatus.setText("Record found");
                //Otherwise, show that the record was not found
            } else {
                lblStatus.setText("Record not found");
            }
            //Error feedback
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //Method for inserting a record
    private void insert() {
        //Create a query string
        String queryString = "insert into Staff (id, lastName, firstName, "
                + "mi, address, city, state, telephone) values (?, ?, ?, "
                + "?, ?, ?, ?, ?);";
        try {
            //Establish a connection
            Connection connection
                    = DriverManager.getConnection("jdbc:mysql://localhost/test",
                            "INFO211", "java2");
            System.out.println("Database connected");

            //Use the prepared statement to insert the record according to the
            //text field values, and execute the update
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, tfID.getText());
            preparedStatement.setString(2, tfLastName.getText());
            preparedStatement.setString(3, tfFirstName.getText());
            preparedStatement.setString(4, tfMI.getText());
            preparedStatement.setString(5, tfAddress.getText());
            preparedStatement.setString(6, tfCity.getText());
            preparedStatement.setString(7, tfState.getText());
            preparedStatement.setString(8, tfTelephone.getText());
            preparedStatement.executeUpdate();
            //Show that the record was inserted
            lblStatus.setText("Record inserted");
            //Error feedback
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //Method for updating a record
    private void update() {

        //Create a query string
        String queryString = "update Staff set lastName = ?, "
                + "firstName = ?, mi = ?, address = ?, city = ?, "
                + "state = ?, telephone = ? where id = ?;";
        try {
            //Establish a connection
            Connection connection
                    = DriverManager.getConnection("jdbc:mysql://localhost/test",
                            "INFO211", "java2");
            System.out.println("Database connected");

            //Use the prepared statement to update the record according to the
            //text field values, where the id matches. Execute the update.
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, tfLastName.getText());
            preparedStatement.setString(2, tfFirstName.getText());
            preparedStatement.setString(3, tfMI.getText());
            preparedStatement.setString(4, tfAddress.getText());
            preparedStatement.setString(5, tfCity.getText());
            preparedStatement.setString(6, tfState.getText());
            preparedStatement.setString(7, tfTelephone.getText());
            preparedStatement.setString(8, tfID.getText());
            preparedStatement.executeUpdate();
            //Show that the record was updated
            lblStatus.setText("Record updated");
            //Error feedback
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //Method for clearing the textfields and deleting all records in the table
    private void clear() {
        try {
            //Establish a connection
            Connection connection
                    = DriverManager.getConnection("jdbc:mysql://localhost/test",
                            "INFO211", "java2");
            System.out.println("Database connected");

            //Create a statement
            Statement stmt = connection.createStatement();
            
            /*Execute the update with the query to delete all records in "Staff"
            //Note: uncommenting the following delete query will delete the
            entire table when the clear button is pressed. Only use it for
            testing.*/
            
//        //stmt.executeUpdate("delete from Staff");

            //set all text fields to empty strings
            tfID.setText("");
            tfLastName.setText("");
            tfFirstName.setText("");
            tfMI.setText("");
            tfAddress.setText("");
            tfCity.setText("");
            tfState.setText("");
            tfTelephone.setText("");
            
            //If it's desired, clear the status label
//          //lblStatus.setText("");
            
            //Error feedback
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}


