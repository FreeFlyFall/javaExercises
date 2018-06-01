/*Create a user interface, as shown in the following figure. Your program should let the user
enter the loan amount and loan period in number of years from a text field, and should
display the monthly and total payments for each annual interest rate starting from 5
percent to 8 percent, with increments of one-fourth (quarter), in a text area when the user
click show Table button.
Note: monthlyPayment = loanAmount * monthlyInterestRate / (1
- (1 / (1 + monthlyInterestRate)^(numberofyears*12)));*/

package computeloans;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ComputeLoans extends Application {
    
    TextField tfLoanAmount = new TextField();
    TextField tfNumberOfYears = new TextField();
    TextArea textArea = new TextArea();
    
    @Override//Override the start method in the Application class
    public void start(Stage primaryStage) {
        
        //Create and format HBox and BorderPane
        HBox controlPane = new HBox();
        controlPane.setSpacing(10);
        controlPane.setPadding(new Insets(5));
        controlPane.setAlignment(Pos.CENTER);
        
        BorderPane borderPane = new BorderPane(textArea);
        textArea.setEditable(false);
        borderPane.setTop(controlPane);
        
        //Create labels for loan amount and number of years
        Label lblLoanAmount = new Label("Loan Amount ", tfLoanAmount);
        lblLoanAmount.setContentDisplay(ContentDisplay.RIGHT);
        tfLoanAmount.setPrefColumnCount(7);
        
        Label lblNumberOfYears = new Label("Number of Years ", tfNumberOfYears);
        lblNumberOfYears.setContentDisplay(ContentDisplay.RIGHT);
        tfNumberOfYears.setPrefColumnCount(2);
        
        //Create button for showing the table
        Button btShowTable = new Button("Show Table");
        
        //Add labels and button to the HBox
        controlPane.getChildren().addAll(lblLoanAmount, lblNumberOfYears,
                btShowTable);
        
        //Process events
        btShowTable.setOnAction(e -> {
            Compute();
        });
        
        //Create a scene and place it in the stage
        primaryStage.setScene(new Scene(borderPane, 500, 225));
        primaryStage.setTitle("Compute Loans");
        primaryStage.show();
    }

    private void Compute() {
        //Create variables from the data in the text fields
        double loanAmount = Double.parseDouble(tfLoanAmount.getText());
        double numberOfYears = Double.parseDouble(tfNumberOfYears.getText());
        
        //Initialize a string to display the header in the text area
        String s = String.format("%-1s%20s%18s\n", "Interest Rate",
                "Monthly Payment", "Total Payment");
        
        //Create a loop to calculate the different interest rates
        for(double annualInterestRate =
            5.0; annualInterestRate <= 8.00; annualInterestRate += 0.25) {
            
            //Create new values for monthly and total rates
            double monthlyInterestRate = annualInterestRate / 1200;
            double monthlyPayment =
                loanAmount * monthlyInterestRate / (1 - 1 / Math.pow(1 +
                monthlyInterestRate, numberOfYears * 12));
            double totalPayment = monthlyPayment * numberOfYears * 12;
            
            //Format the data for display
            s += String.format("%-1s\t%23.2f\t%27.2f\n",
                    (Math.round(annualInterestRate * 100) / 100.0),
                    (monthlyPayment * 100) / 100.0,
                    (totalPayment * 100) / 100.0);
        }
        //Set the string to display the data
        textArea.setText(s);
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
}
