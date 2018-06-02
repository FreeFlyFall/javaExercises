/*Modify the example 14.10 in Section 14.10.1 (ShowFlowPane.java in page 559 in your
textbook) to prompt user to enter his/her first, middle (if any) and last name and display
them in three Text Fields as shown in the sample runs and figures below:
Note: If the user has a middle name, only the initial letter need to be displayed.*/

package textfields;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;// default imports
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import java.util.Scanner;
import static javafx.application.Application.launch;

public class TextFields extends Application {
    
    public void start(Stage primaryStage) {
        
    //Create a pane and set its properties
        FlowPane pane = new FlowPane();
        pane.setPadding(new Insets(11, 12, 13, 14));
        pane.setHgap(5);
        pane.setVgap(5);
        
        //Place nodes in the pane
        pane.getChildren().addAll(new Label("First Name:"), 
            new TextField(findFirstName()), new Label("MI:"));
        TextField tfMi = new TextField(findMiddleName()); //middleName.charAt(0)
        tfMi.setPrefColumnCount(1);
        pane.getChildren().addAll(tfMi, new Label("Last Name:"), 
                new TextField(findLastName()));
        
        //Create a scene and place it in the stage
        Scene scene = new Scene(pane, 300, 250);
        primaryStage.setTitle("ShowFlowPane");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    //Method to return the input of the user's first name
    public static String findFirstName() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your first name: ");
        String a = input.nextLine();
        String b = a.substring(0, 1).toUpperCase() + a.substring(1);
        return b;
    }
    
    //Method to return the input of the user's middle name, if they have one
    public static String findMiddleName() {
        Scanner input = new Scanner(System.in);
        //Ask whether the user has a middle name
        String yes = "Y";
        System.out.println("Do you have a middle name: "
                + "Type Y if yes, or N if no:");
        String a = input.nextLine().substring(0,1);
        //If the user has a middle name, ask the user to enter it
        if (a.equalsIgnoreCase(yes)) {
            System.out.println("Enter your middle name: "); 
            String b = input.nextLine().substring(0,1).toUpperCase();
            return b;
        }
        return "";
    }
    
    //Method to return the input of the user's last name
    public static String findLastName() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your last name: ");
        String a = input.nextLine();
        String b = a.substring(0, 1).toUpperCase() + a.substring(1);
        return b;   
    }
    
  /**
   * The main method is only needed for the IDE with limited JavaFX
   * support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
