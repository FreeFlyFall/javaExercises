/*Write a program that calculates the future value of an investment at a given interest rate
for a specified number of years. The formula for the calculation is as follows:
futureValue = investmentAmount * (1 + monthlyInterestRate)years * 12
Use text fields for the investment amount, number of years, and annual interest rate.
Display the future amount in a text field when the user clicks the Calculate button*/

package investmentvaluecalculator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.stage.Stage;

public class InvestmentValueCalculator extends Application {

    private TextField tfInvestmentAmount = new TextField();
    private TextField tfNumberOfYears = new TextField();
    private TextField tfAnnualInterestRate = new TextField();
    private TextField tfFutureValue = new TextField();
    private Button btCalculate = new Button("Calculate");

    @Override //Override the start method in the Application class
    public void start(Stage primaryStage) {
        //Create UI
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.add(new Label("Investment Amount:"), 0, 0);
        gridPane.add(tfInvestmentAmount, 1, 0);
        gridPane.add(new Label("Number of Years:"), 0, 1);
        gridPane.add(tfNumberOfYears, 1, 1);
        gridPane.add(new Label("Annual Interest Rate:"), 0, 2);
        gridPane.add(tfAnnualInterestRate, 1, 2);
        gridPane.add(new Label("Future value:"), 0, 3);
        gridPane.add(tfFutureValue, 1, 3);
        gridPane.add(btCalculate, 1, 4);

        //Set UI properties
        gridPane.setAlignment(Pos.CENTER);
        tfInvestmentAmount.setAlignment(Pos.BOTTOM_LEFT);
        tfNumberOfYears.setAlignment(Pos.BOTTOM_LEFT);
        tfAnnualInterestRate.setAlignment(Pos.BOTTOM_LEFT);
        tfFutureValue.setAlignment(Pos.BOTTOM_LEFT);
        tfFutureValue.setEditable(false);
        GridPane.setHalignment(btCalculate, HPos.RIGHT);

        //Process events
        btCalculate.setOnAction(e -> {
            double investmentAmount
                    = Double.parseDouble(tfInvestmentAmount.getText());
            int years = Integer.parseInt(tfNumberOfYears.getText());
            double monthlyInterestRate
                    = Double.parseDouble(tfAnnualInterestRate.getText()) / 1200;
            tfFutureValue.setText(String.format("$%.2f",
                    (investmentAmount * Math.pow(1 + monthlyInterestRate, years
                            * 12))));
        });
        /*To calculate the investment amount, could also define a method and
        call it in the event:
        ******************************************************
        btCalculate.setOnAction(e -> futureValue());
        
            private void futureValue() {
        double investmentAmount = Double.parseDouble
            (tfInvestmentAmount.getText());
        int years = Integer.parseInt(tfNumberOfYears.getText());
        double monthlyInterestRate = 
            Double.parseDouble(tfAnnualInterestRate.getText()) / 1200;
        tfFutureValue.setText(String.format("$%.2f", 
            (investmentAmount * Math.pow(1 + monthlyInterestRate, years *
            12))));
        }
        ******************************************************/

        //Create a scene and place it in the stage
        Scene scene = new Scene(gridPane, 400, 250);
        primaryStage.setTitle("InvestmentValueCalculator"); //Set stage title
        primaryStage.setScene(scene); //Place the scene in the stage
        primaryStage.show(); //Display the stage
    }
}
