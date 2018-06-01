/*
Revise Listing 35.2, CopyFileToTable.java, to always copy the
data from a text file named staff to staff table in test database, as shown in the following
figures.

Assume the file contains the following three records:
'5','Fred','Arcen','A','address1','Richmond','IN','7659999999','email1@iu.edu'
'6','Thomas','Arthur','B','address2','Richmond','IN','7659999999','email2@iu.edu'
'9','Suzette','Austin','D','address3','Richmond','IN','7659999999','email3@iu.edu'

Revised to clear fields for aesthetic reasons.
*/

package copyfiletostaff;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CopyFileToStaff extends Application {

    // Text file directory including file name goes here
    private TextField tfFilename = new TextField();
    //Area to display both file and database content
    private TextArea taFile = new TextArea();

    // JDBC and table info
    private ComboBox<String> cboURL = new ComboBox<>();
    private ComboBox<String> cboDriver = new ComboBox<>();
    private TextField tfUsername = new TextField();
    private PasswordField pfPassword = new PasswordField();

    // Statement to execute SQL commands
    private Statement statement;

    // Create buttons and labels
    private Button btViewFile = new Button("View File");
    private Button btView = new Button("View");
    private Button btCopy = new Button("Copy");
    private Label lblStatus = new Label();

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        cboURL.getItems().addAll(FXCollections.observableArrayList(
                "jdbc:mysql://localhost/test",
                "jdbc:mysql://liang.armstrong.edu/javabook",
                "jdbc:odbc:exampleMDBDataSource",
                "jdbc:oracle:thin:@liang.armstrong.edu:1521:orcl"));
        cboURL.getSelectionModel().selectFirst();

        cboDriver.getItems().addAll(FXCollections.observableArrayList(
                "com.mysql.jdbc.Driver", "sun.jdbc.odbc.dbcOdbcDriver",
                "oracle.jdbc.driver.OracleDriver"));
        cboDriver.getSelectionModel().selectFirst();

        // Create UI for connecting to the database 
        GridPane gridPane = new GridPane();
        gridPane.add(new Label("JDBC Driver"), 0, 0);
        gridPane.add(new Label("Database URL"), 0, 1);
        gridPane.add(new Label("Username"), 0, 2);
        gridPane.add(new Label("Password"), 0, 3);
        gridPane.add(cboURL, 1, 0);
        gridPane.add(cboDriver, 1, 1);
        gridPane.add(tfUsername, 1, 2);
        gridPane.add(pfPassword, 1, 3);

        HBox hBoxConnection = new HBox(10);
        hBoxConnection.getChildren().addAll(lblStatus, btView, btCopy);
        hBoxConnection.setAlignment(Pos.CENTER_RIGHT);

        VBox vBoxConnection = new VBox(5);
        vBoxConnection.getChildren().addAll(
                new Label("Target Database Table"),
                gridPane, hBoxConnection);

        gridPane.setStyle("-fx-border-color: black;");

        BorderPane borderPaneFileName = new BorderPane();
        borderPaneFileName.setLeft(new Label("Filename"));
        borderPaneFileName.setCenter(tfFilename);
        borderPaneFileName.setRight(btViewFile);

        BorderPane borderPaneFileContent = new BorderPane();
        borderPaneFileContent.setTop(borderPaneFileName);
        borderPaneFileContent.setCenter(taFile);

        BorderPane borderPaneFileSource = new BorderPane();
        borderPaneFileSource.setTop(new Label("Source Text File"));
        borderPaneFileSource.setCenter(borderPaneFileContent);

        SplitPane sp = new SplitPane();
        sp.getItems().addAll(borderPaneFileSource, vBoxConnection);

        // Create a scene and place it in the stage
        Scene scene = new Scene(sp, 1050, 230);
        primaryStage.setTitle("CopyFileToTable"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage    

        // Link events to buttons
        btViewFile.setOnAction(e -> showFile());
        btCopy.setOnAction(e -> {
            try {
                copyFile();
                taFile.setText("");
            } catch (Exception ex) {
                lblStatus.setText(ex.toString());
            }
        });
        btView.setOnAction(e -> {
            try {
                taFile.setText("");
                processSQLSelect();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    /**
     * Display the file in the text area
     */
    private void showFile() {
        Scanner input = null;
        try {
            taFile.setText("");
            // Use a Scanner to read text from the file
            input = new Scanner(new File(tfFilename.getText().trim()));

            // Read a line and append the line to the text area
            while (input.hasNext()) {
                taFile.appendText(input.nextLine() + '\n');
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found: " + tfFilename.getText());
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                input.close();
            }
        }
    }

    /**
     * Execute SQL SELECT commands
     */
    private void processSQLSelect() throws Exception {
        String sqlCommand = "Select * from staff;";
        try {

            // Establish a connection
            Connection conn = DriverManager.getConnection(
                    cboURL.getSelectionModel().getSelectedItem().trim(),
                    tfUsername.getText().trim(),
                    String.valueOf(pfPassword.getText()).trim());
            System.out.println("Database connected");

            // Get a new statement for the current connection
            statement = conn.createStatement();

            // Execute a SELECT SQL command
            ResultSet resultSet = statement.executeQuery(sqlCommand);

            // Find the number of columns in the result set
            int columnCount = resultSet.getMetaData().getColumnCount();
            String row = "";

            // Display column names
            for (int i = 1; i <= columnCount; i++) {
                row += resultSet.getMetaData().getColumnName(i) + "\t";
            }

            taFile.appendText(row + '\n');

            while (resultSet.next()) {
                // Reset row to empty
                row = "";

                for (int i = 1; i <= columnCount; i++) {
                    // A non-String column is converted to a string
                    row += resultSet.getString(i) + "\t";
                }

                taFile.appendText(row + '\n');
            }
        } catch (SQLException ex) {
            taFile.setText(ex.toString());
        }
    }

    // Method to connect to the database and call the method to insert the data
    private void copyFile() throws Exception {
        // Load the JDBC driver
        Class.forName(cboDriver.getSelectionModel()
                .getSelectedItem().trim());
        System.out.println("Driver loaded");

        // Establish a connection
        Connection conn = DriverManager.getConnection(
                cboURL.getSelectionModel().getSelectedItem().trim(),
                tfUsername.getText().trim(),
                String.valueOf(pfPassword.getText()).trim());
        System.out.println("Database connected");

        // Read each line from the text file and insert it to the table
        insertRows(conn);
    }

    // Method to insert data into the database
    private void insertRows(Connection connection) {
        // Build the SQL INSERT statement
        String sqlInsert = "insert into staff values (";

        // Use a Scanner to read text from the file
        Scanner input = null;

        // Get file name from the text field
        String filename = tfFilename.getText().trim();

        try {
            // Create a scanner
            input = new Scanner(new File(filename));

            // Create a statement
            Statement statement = connection.createStatement();

            System.out.println("Driver major version? "
                    + connection.getMetaData().getDriverMajorVersion());

            // Determine if batchUpdatesSupported is supported 
            boolean batchUpdatesSupported = false;

            try {
                if (connection.getMetaData().supportsBatchUpdates()) {
                    batchUpdatesSupported = true;
                    System.out.println("batch updates supported");
                } else {
                    System.out.println("The driver "
                            + "does not support batch updates");
                }
            } catch (UnsupportedOperationException ex) {
                System.out.println("The operation is not supported");
            }

            // Determine if the driver is capable of batch updates
            if (batchUpdatesSupported) {
                // Read a line and add the insert table command to the batch
                while (input.hasNext()) {
                    statement.addBatch(sqlInsert + input.nextLine() + ")");
                }

                statement.executeBatch();

                lblStatus.setText("Batch updates completed");
            } else {
                // Read a line and execute insert table command
                while (input.hasNext()) {
                    statement.executeUpdate(sqlInsert + input.nextLine() + ")");
                }

                lblStatus.setText("Single row update completed");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (FileNotFoundException ex) {
            System.out.println("File not found: " + filename);
        } finally {
            if (input != null) {
                input.close();
            }
        }
    }

    /**
     * The main method is only needed for the IDE with limited JavaFX support.
     * Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
