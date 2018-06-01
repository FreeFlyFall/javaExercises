/*Write a program that reads integers, finds the smallest of them, and counts its occurrences.
Assume that the input ends with number -1. Suppose that you entered 4 2 9 2 2 -1; the
program finds that the smallest is 2 and the occurrence count for 2 is 3. (Hint: Maintain
two variables, min and count. min stores the current min number, and count stores its
occurrences. Initially, assign the first number to min and 1 to count. Compare each
subsequent number with min. If the number is smaller than min, assign it to min and reset
count to 1. If the number is equal to min, increment count by 1.)*/

//** -1 shouldn't be input except as the last number **

package occurenceofmin;
import java.util.Scanner;

public class OccurenceOfMin{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); //create scanner object
        
        System.out.print("Enter numbers:"); //prompt input of integers
        
        // declare class variables
        int number, min;
        number = input.nextInt();
        min = number;
        int count = 1;
        
        //input user data except when user enters the sentinel value
        while (number != -1) {
            number = input.nextInt();
            //check if number is smalter than min and not the sentinel value
            if (number < min && number != -1){
                //if yes, set the number to min
                min = number;
                count = 1; }
            
            //if the number is equal to max, increment occurrence counter
            else if (number == min) {
                count++;
            }
        }
        //Display the minimum and its occurrence count
        System.out.println("The smallest number is " + min);
        System.out.println("The occurence count of the smallest number is " +
                count);
    }
}
