/*Write a program using tab panes for performing simple and advanced calculations.
In the first tab, Simple Calculator, there are four operations, add,
subtract, multiply and divide. In the second tab, Advanced Calculator, the operations are
square root (Sqrt) (Sqrt(9)=3), absolute value (Abs) (Abs(-9)=9), Floor (floor(2.2)=2,
floor(2.9)=2) and Ceil (ceil(2.2)=3, ceil(2.9)=3). For these math functions, please refer to
chapter 4 in your textbook (section 4.2).

Note that in the Simple Calculator tab, you need
to create a menu bar that includes two menus, Operation and Exit. Under the menu
operation, you should have four menu items: Add, Subtract, Multiply, and Divide. To
perform an operation, for example addition, users have two options: 1) click Add button or
2) select Add from the operation menu. The Exit menu should include only one menu item,
Close to close (exit) the program. Please see Listing 31.9 in chapter 31.*/

package calculatortabpanes;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CalculatorTabPanes extends Application {

    //Create text fields for the I/O
    private TextField tfNumber1 = new TextField();
    private TextField tfNumber2 = new TextField();
    private TextField tfResult = new TextField();
    private TextField tfNumber3 = new TextField();
    private TextField tfResult2 = new TextField();

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {

        //Create a TabPane to hold the simple and advanced tabs
        TabPane tabPane = new TabPane();
        Tab tab1 = new Tab("Simple Calculator");
        Tab tab2 = new Tab("Advanced Calculator");
        //Add the tabs to the TabPane
        tabPane.getTabs().addAll(tab1, tab2);

        //Create a menu bar for the menu operations
        MenuBar menuBar = new MenuBar();
        //Create menus for operation and exit
        Menu menuOperation = new Menu("Operation");
        Menu menuExit = new Menu("Exit");
        //Add menus to their menu bar
        menuBar.getMenus().addAll(menuOperation, menuExit);

        //Create menu items and add them to the menus
        MenuItem menuItemAdd = new MenuItem("Add");
        MenuItem menuItemSubtract = new MenuItem("Subtract");
        MenuItem menuItemMultiply = new MenuItem("Multiply");
        MenuItem menuItemDivide = new MenuItem("Divide");
        menuOperation.getItems().addAll(menuItemAdd, menuItemSubtract,
                menuItemMultiply, menuItemDivide);
        MenuItem menuItemClose = new MenuItem("Close");
        menuExit.getItems().add(menuItemClose);

        //Create the shortcuts(accelerators) for the operation menu items
        menuItemAdd.setAccelerator(
                KeyCombination.keyCombination("Ctrl+A"));
        menuItemSubtract.setAccelerator(
                KeyCombination.keyCombination("Ctrl+S"));
        menuItemMultiply.setAccelerator(
                KeyCombination.keyCombination("Ctrl+M"));
        menuItemDivide.setAccelerator(
                KeyCombination.keyCombination("Ctrl+D"));

        //Create and format an HBox to hold the simple calculator tab's I/O
        //labels
        HBox hBox1 = new HBox(5);
        tfNumber1.setPrefColumnCount(2);
        tfNumber2.setPrefColumnCount(2);
        tfResult.setPrefColumnCount(2);
        hBox1.getChildren().addAll(new Label("Number 1:"), tfNumber1,
                new Label("Number 2:"), tfNumber2, new Label("Result:"),
                tfResult);
        hBox1.setAlignment(Pos.CENTER);

        //Create and format an Hbox to hold the simple calculator tab's
        //operation buttons
        HBox hBox2 = new HBox(5);
        Button btAdd = new Button("Add");
        Button btSubtract = new Button("Subtract");
        Button btMultiply = new Button("Multiply");
        Button btDivide = new Button("Divide");
        hBox2.getChildren().addAll(btAdd, btSubtract, btMultiply, btDivide);
        hBox2.setAlignment(Pos.CENTER);

        //Create a VBox to hold the simple calculator tab's menubar and HBoxes
        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(menuBar, hBox1, hBox2);

        //Create and format an HBox to hold the advanced calculator tab's I/O
        //labels
        HBox hBox3 = new HBox(5);
        tfNumber3.setPrefColumnCount(2);
        tfResult2.setPrefColumnCount(2);
        hBox3.getChildren().addAll(new Label("Number 1:"), tfNumber3,
                new Label("Result:"), tfResult2);
        hBox3.setAlignment(Pos.CENTER);

        //Create and format an HBox to hold the advanced calculator tab's
        //operation buttons
        HBox hBox4 = new HBox(5);
        Button btSqrt = new Button("Sqrt");
        Button btAbs = new Button("Abs");
        Button btFloor = new Button("Floor");
        Button btCeiling = new Button("Ceiling");
        hBox4.getChildren().addAll(btSqrt, btAbs, btFloor, btCeiling);
        hBox4.setAlignment(Pos.CENTER);

        //Create a VBox to hold the advanced calculator tab's menubar and HBoxes
        VBox vBox2 = new VBox(10);
        vBox2.getChildren().addAll(hBox3, hBox4);

        //Set the contents of the simple and advanced calculator tabs
        tab1.setContent(vBox);
        tab2.setContent(vBox2);

        //Create the Scene and set it's contents.
        Scene scene = new Scene(tabPane, 300, 250);
        primaryStage.setTitle("Tab Panes"); // Set the window title
        primaryStage.setScene(scene); // Place the scene in the window
        primaryStage.show(); // Display the window

        //Handle simple calculator menu actions
        menuItemAdd.setOnAction(e -> perform('+'));
        menuItemSubtract.setOnAction(e -> perform('-'));
        menuItemMultiply.setOnAction(e -> perform('*'));
        menuItemDivide.setOnAction(e -> perform('/'));
        menuItemClose.setOnAction(e -> System.exit(0));

        //Handle simple calculator button actions
        btAdd.setOnAction(e -> perform('+'));
        btSubtract.setOnAction(e -> perform('-'));
        btMultiply.setOnAction(e -> perform('*'));
        btDivide.setOnAction(e -> perform('/'));

        //Handle advanced calculator button actions
        btSqrt.setOnAction(e -> performAdvanced("Sqrt"));
        btAbs.setOnAction(e -> performAdvanced("Abs"));
        btFloor.setOnAction(e -> performAdvanced("Floor"));
        btCeiling.setOnAction(e -> performAdvanced("Ceil"));
    }

    //Method to return the result for the simple operations
    private void perform(char operator) {
        double number1 = Double.parseDouble(tfNumber1.getText());
        double number2 = Double.parseDouble(tfNumber2.getText());

        double result = 0;
        switch (operator) {
            case '+':
                result = number1 + number2;
                break;
            case '-':
                result = number1 - number2;
                break;
            case '*':
                result = number1 * number2;
                break;
            case '/':
                result = number1 / number2;
                break;
        }

        tfResult.setText(result + "");
    }

    //Method to return the result for the advanced operations
    private void performAdvanced(String operator) {
        double number3 = Double.parseDouble(tfNumber3.getText());

        double result2 = 0;
        switch (operator) {

            case "Sqrt":
                result2 = Math.sqrt(number3);
                break;
            case "Abs":
                result2 = Math.abs(number3);
                break;
            case "Floor":
                result2 = Math.floor(number3);
                break;
            case "Ceil":
                result2 = Math.ceil(number3);
                break;
        }

        tfResult2.setText(result2 + "");
    }

    /**
     * The main method is only needed for the IDE with limited JavaFX support.
     * Not needed for running from the command line.
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
