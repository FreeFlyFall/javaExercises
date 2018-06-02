/*Rewrite Listing 35.1, SQLClient.java, to display the query result in a
TableView.

For example, a user can type two SQL commands like select and Insert statements. Please
note that the new inserted record should be displayed in the TableView when “Execute
SQL Command” button is clicked. (Note: for the TableView class, refer to Ch. 31).
In addition, you need to re-write the clear button to clear the TableView.

In this version, the clear button clears the TableView instead of the SQL commmand text area.
*/

package dbrevisesqlclienttable;

import java.sql.*;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class DBReviseSQLClientTable extends Application {

    // Connection to the database
    private Connection connection;

    // Statement to execute SQL commands
    private Statement statement;

    // Text area to enter SQL commands
    private TextArea tasqlCommand = new TextArea();

    // DBC info for a database connection
    private TextField tfUsername = new TextField();
    private PasswordField pfPassword = new PasswordField();
    private ComboBox<String> cboURL = new ComboBox<>();
    private ComboBox<String> cboDriver = new ComboBox<>();

    private Button btExecuteSQL = new Button("Execute SQL Command");
    private Button btClearTableView = new Button("Clear");
    private Button btConnectDB = new Button("Connect to Database");
    private Label lblConnectionStatus
            = new Label("No connection now");

    //Table View to display results from SQL commands
    private TableView<ObservableList> tableView = new TableView<>();

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
        gridPane.add(cboURL, 1, 0);
        gridPane.add(cboDriver, 1, 1);
        gridPane.add(tfUsername, 1, 2);
        gridPane.add(pfPassword, 1, 3);
        gridPane.add(new Label("JDBC Driver"), 0, 0);
        gridPane.add(new Label("Database URL"), 0, 1);
        gridPane.add(new Label("Username"), 0, 2);
        gridPane.add(new Label("Password"), 0, 3);

        HBox hBoxConnection = new HBox();
        hBoxConnection.getChildren().addAll(
                lblConnectionStatus, btConnectDB);
        hBoxConnection.setAlignment(Pos.CENTER_RIGHT);

        VBox vBoxConnection = new VBox(5);
        vBoxConnection.getChildren().addAll(
                new Label("Enter Database Information"),
                gridPane, hBoxConnection);

        gridPane.setStyle("-fx-border-color: black;");

        HBox hBoxSQLCommand = new HBox(5);
        hBoxSQLCommand.getChildren().addAll(
                btClearTableView, btExecuteSQL);
        hBoxSQLCommand.setAlignment(Pos.CENTER_RIGHT);

        BorderPane borderPaneSqlCommand = new BorderPane();
        borderPaneSqlCommand.setTop(
                new Label("Enter an SQL Command"));
        borderPaneSqlCommand.setCenter(
                new ScrollPane(tasqlCommand));
        borderPaneSqlCommand.setBottom(
                hBoxSQLCommand);
        tasqlCommand.setPrefHeight(110);
        tasqlCommand.setPrefWidth(400);

        HBox hBoxConnectionCommand = new HBox(10);
        hBoxConnectionCommand.getChildren().addAll(
                vBoxConnection, borderPaneSqlCommand);

        BorderPane borderPaneExecutionResult = new BorderPane();
        borderPaneExecutionResult.setTop(
                new Label("SQL Execution Result"));
        borderPaneExecutionResult.setCenter(tableView);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(hBoxConnectionCommand);
        borderPane.setCenter(borderPaneExecutionResult);

        // Create a scene and place it in the stage
        Scene scene = new Scene(borderPane, 800, 450);
        primaryStage.setTitle("SQLClient"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage    

        //Set the events for the buttons
        btConnectDB.setOnAction(e -> connectToDB());
        btExecuteSQL.setOnAction(e -> executeSQL());
        btClearTableView.setOnAction(e -> tableView.getItems().clear());
    }

    /**
     * Connect to DB
     */
    private void connectToDB() {
        // Get database information from the user input
        String driver = cboDriver
                .getSelectionModel().getSelectedItem();
        String url = cboURL.getSelectionModel().getSelectedItem();
        String username = tfUsername.getText().trim();
        String password = pfPassword.getText().trim();

        // Connection to the database
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(
                    url, username, password);
            lblConnectionStatus.setText("Connected to " + url);
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Execute SQL commands
     */
    private void executeSQL() {
        if (connection == null) {
            System.out.println("Please connect to a database first");
            return;
        } else {
            String sqlCommands = tasqlCommand.getText().trim();
            String[] commands = sqlCommands.replace('\n', ' ').split(";");

            for (String aCommand : commands) {
                if (aCommand.trim().toUpperCase().startsWith("SELECT")) {
                    processSQLSelect(aCommand);
                } else {
                    processSQLNonSelect(aCommand);
                }
            }
        }
    }

    /**
     * Execute SQL SELECT commands
     */
    private void processSQLSelect(String sqlCommand) {
        try {
            // Get a new statement for the current connection
            statement = connection.createStatement();

            // Execute a SELECT SQL command
            ResultSet resultSet = statement.executeQuery(sqlCommand);

            //Call the method to populate the Table View
            populateTableView(resultSet, tableView);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Execute SQL DDL, and modification commands. Update the table view.
     */
    private void processSQLNonSelect(String sqlCommand) {
        try {
            // Get a new statement for the current connection
            statement = connection.createStatement();

            // Execute a non-SELECT SQL command
            statement.executeUpdate(sqlCommand);
            
            /**
            Show the table view with updated database information.
            "Select * from staff;" is used in this method to allow select
            statements in the text area to be processed if they come after
            update statements
            */
            processSQLSelect("Select * from Staff;");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //Populate Table View
    private void populateTableView(ResultSet rs, TableView tableView) {
//empty the table view from the content of the previous statement
        tableView.getColumns().clear();
        ObservableList<ObservableList> data
                = FXCollections.observableArrayList();
        try {
            /**
             * ********************************
             * TABLE COLUMN ADDED DYNAMICALLY * ********************************
             */
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
//We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(
                        rs.getMetaData().getColumnName(i + 1));
//              col.setCellValueFactory(TextFieldTableCell.forTableColumn());

                col.setCellValueFactory(new Callback<CellDataFeatures<
                        ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<
                            ObservableList, String> param) {
                        if (param == null || param.getValue() == null
                                || param.getValue().get(j) == null) {
                            return null;
                        }
                        return new SimpleStringProperty(param.getValue().get(
                                j).toString());
                    }
                });
                tableView.getColumns().addAll(col);
//System.out.println("Column [" + i + "] ");
            }
            /**
             * ****************************
             * Data added to ObservableList
             */
            while (rs.next()) {
//Iterate Row
                ObservableList<String> row
                        = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
//Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);
            }
//FINALLY ADDED TO TableView
            tableView.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
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
