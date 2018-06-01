/*Write a program that reads student scores, gets the best score, and then assigns grades
based on the following scheme:
Grade is A if score is >= best - 10
Grade is B if score is >= best - 20;
Grade is C if score is >= best - 30;
Grade is D if score is >= best - 40;
Grade is F otherwise.
*/

package computegrades;
import java.util.Scanner;

public class ComputeGradesJava {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); //Create scanner
        //Prompt user to input # of students and assign the value to variable n
        System.out.print("Enter the number of students: ");
        int n = input.nextInt();
        
        //Prompt user to input each of the students scores as an integer
        System.out.print("Enter " + n + " scores: ");
        
        //Create an array with the size of variable n to hold the score values
        int[] score = new int [n];
        
        //Loop to assign the scores to each consecutive index of the array
        for(int i = 0; i < n; i++) {
            score[i] = input.nextInt();
        }
        
        //Call the method that determines the high score and assign it to an int
        int highScore = findHighScore(score);
        
        /*Loop to print the output message for each student, their score,
        and their letter grade. Calls the method to get the grade's character,
        sending it each index value of the array and the high score. The index
        value is incremented for each loop iteration.
        */
        for (int i = 0; i < score.length; i++) {
            System.out.println("Student " + i + " score is " + score[i] + 
                    " and grade is " + getLetterGrade(score[i], highScore));
        } 
    }
    
    //Finds the high score and returns it as the largest value in an array
    public static int findHighScore(int[] score) {
        
        //Set the high score as the first index value of the array
        int highScore = score[0];
        
        /*For each element "i" in the array that is passed to this method, if
        "i" is greater than the high score, it becomes the new high score
        */
        for(int i: score) {
            if (i > highScore) {
                highScore = i;
            }
        }
        //Return the high score
        return highScore;
    }
    
    /*Finds the character for each score using and returns it. Needs to be
    passed each index value of an array as an integer, and the high score.
    */
    public static char getLetterGrade(int score, int highScore) {
        
        /*Determine the character that corresponds to each score depending
        on how each score compares to the highscore. Return the corresponding
        character.
        */
        if (score >= highScore - 10) {
            return 'A';
        } else if (score >= highScore - 20) {
            return 'B';
        }else if (score >= highScore - 30) {
            return 'C';
        }else if (score >= highScore - 40) {
            return 'D';
        }else {
            return 'F';
        }  
    }
}
