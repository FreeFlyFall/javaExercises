/*Write a program that prompts the user to enter a lowercase or an uppercase letter and
displays its corresponding digit. For a nonletter input, display invalid input.*/


package phonekeypads;
import java.util.Scanner;

public class PhoneKeyPads {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        int number = 0; //Number is set to zero to prevent an invalid print
        
        //Prompt user to enter an uppercase or lowercase letter
        System.out.print("Enter a lowercase or an uppercase letter: ");
        //set the character "letter" as the first letter of an input by user
        char letter = input.nextLine().charAt(0);
        //If the first char is lowercase in ASCII, convert it to uppercase
        if (letter >= 97 && letter <= 122) {
            letter -= 32;
        }
        
        /*Switch statement changing the value of "number" based on the value of
        "letter"
        */
        switch (letter) {
            case 'A':
            case 'B':
            case 'C':
                number = 2;
                break;
            case 'D':
            case 'E':
            case 'F':
                number = 3;
                break;
            case 'G':
            case 'H':
            case 'I':
                number = 4;
                break;
            case 'J':
            case 'K':
            case 'L':
                number = 5;
                break;
            case 'M':
            case 'N':
            case 'O':
                number = 6;
                break;
            case 'P':
            case 'Q':
            case 'R':
            case 'S':
                number = 7;
                break;
            case 'T':
            case 'U':
            case 'V':
                number = 8;
                break;
            case 'W':
            case 'X':
            case 'Y':
            case 'Z':
                number = 9;
                break;
            //Default to error message if no case is fulfilled
            default:
                System.out.println(letter + " is an invalid input");
                break;
        }
        
        /* If the integer "number" was changed to another value throughout the
        program, it will not be 0. Print the valid "number"
        */
        if (number != 0) {
            System.out.println("The corresponding number is " + number);
        }
    }
}
