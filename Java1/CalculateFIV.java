/*Write a program that reads in investment amount, annual interest rate, and
number of years, and displays the future investment value using the following
formula:
futureInvestmentValue =
investmentAmount * (1 + monthlyInterestRate)numberOfYears*12.*/

package calculatefiv;

//Import Scanner
import java.util.Scanner;

public class CalculateFIV {
    public static void main(String[] args) {
        //Create a scanner
        Scanner input = new Scanner(System.in);
    
        //Enter investment amount
        System.out.print("Enter investment amount: ");
        double investmentAmount = input.nextDouble();
        
        //Enter annual interest rate
        System.out.print("Enter annual interest rate in %: ");
        double annualInterestRate = input.nextDouble();
        
        //Find monthly interest rate
        double monthlyInterestRate = annualInterestRate / 1200;
        
        //Enter number of years
        System.out.print("Enter number of years as an integer: ");
        int numberOfYears = input.nextInt();
        
        //Calculate future investment amount
        double futureInvestmentValue = investmentAmount * Math.pow(1 +
                monthlyInterestRate, numberOfYears * 12);
        
        //Display future investment amount in dollars while rounding up to two
        //decimal places
        System.out.println("The future value of the investment is: $" +
                (int)(futureInvestmentValue * 100 + 0.5) / 	100.0);
        
    }
    
}
