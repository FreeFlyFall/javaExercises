/*Write a method that validates a user email format. The email format is as follows:
username@***.*** To validate the email format: First, you need to check whether 
the email contains ‘@’ and ‘.’ characters in the right order. Second, you need 
to ensure that the username (i.e., substring before @ character) consists of 
alphanumeric characters (a-z/A-Z, 0-9) only (i.e., combination of letters and/or
digits, NO special characters)*/

package validateemailformat;
import java.util.Scanner;

public class ValidateEmailFormat {
    public static void main(String[] args) {
        //Create a scanner and ask the user to input email address
        Scanner input = new Scanner(System.in);
        System.out.print("Enter an email address: ");
        //Assign the input to a string
        String email = input.nextLine();
        //Call the validation method to validate the email input
        ValidateEmailFormat(email);
    }
        
    //Create the validation method
    public static void ValidateEmailFormat(String email) {
        
        //Create strings to use for indexes
        String at = "@";
        String per = ".";
        
        
        //Determine whether the email string contains the @ character
        if (email.indexOf(at) < 0){
            System.out.println("@ character is missing");
            System.exit(1);
        }
        //Determine whether the email string contains the . character
        if (email.indexOf(per) < 0) {
            System.out.println(". character is missing");
            System.exit(1);
        }
        //Determine whether the email string contains @ before .
        if (email.indexOf(per) != -1 && email.indexOf(per) < email.indexOf(at)) {
            System.out.println("@ should appear before .");
            System.exit(1);
        }
        
        //Set an integer to the value of the @ sign's index
        int atLocation = email.indexOf(at);
        //Make a string containing the letters of the username before the @ sign
        String sub = email.substring(0, atLocation);
        
        //Count the occurences of numbers and letters in the username
        int countNumeric = (countLetters(sub) + countNumbers(sub));
        //Set the value of the length of the username to an integer
        int sublength = sub.length();
        
        /*Calculate whether the occurences of numbers and letters matches the
        length of the username. Display whether the format is correct based on
        the results.
        */
        if(countNumeric == sublength)
            System.out.println("Correct Email Address");
        else
            System.out.println("The username should include only letters and/or"
             + " digits");
            
    }
    // Create a method to count the occurrences of letters in the username
    public static int countLetters(String s) {
        int numberOfLetters = 0;
        
        //Loop to count the length of the username
        for (int i = 0; i < s.length(); i++) {
            /*Increase the letter count if a character in the username is a 
            letter
            */
           if(Character.isLetter(s.charAt(i)))
                numberOfLetters++;
        }
        //return the count of letters
        return numberOfLetters;
    }
    
    //Create a method to count the occurrences of numbers in the username
    public static int countNumbers(String s) {
        int numberOfNumbers = 0;
        
        //Loop to count the length of the username
        for (int i = 0; i < s.length(); i++) {
            /*Increase the number count if a character in the username is a 
            number
            */
            if(Character.isDigit(s.charAt(i)))
                numberOfNumbers++;
        }   
        //return the count of numbers 
        return numberOfNumbers;
    }
}
