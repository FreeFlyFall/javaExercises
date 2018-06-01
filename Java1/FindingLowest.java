/*Write the following method that returns the smallest element in a
two-dimensional array along with its location
(i.e., the row and column indices).*/

package findinglowest;

import java.util.Scanner;

public class FindingLowest {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);//Create scanner
        
        /*Prompt user to enter the number of rows and columns in the array.
        Assign those values to two variables
        */
        System.out.print("Enter the number of rows and columns of the array: ");
        int rowlength = input.nextInt();
        int columnlength = input.nextInt();
        
        /*Initialize a two-dimensional array with the row and column values
        input by the user
        */
        int[][] matrix = new int[rowlength][columnlength];
        
        /*Prompt the user to input the array and assign the values to each index
        using a for loop*/
        System.out.println("Enter the array: ");
        for (int r = 0; r < rowlength; r++)
            for (int c = 0; c < columnlength; c++)
                matrix[r][c] = input.nextInt();

        /*Initialize an integer array and call a method to assign the
        appropriate values to each index
        */
        int[] location = findingSmallest(matrix);
        
        //Print the smallest value and its location as an ordered pair
        System.out.printf("The smallest element in the array is: %d",
                location[0]);
        System.out.printf("%nThe location of the smallest element is at " +
                "(%d, %d)", location[1], location[2]);
    }

    //Create a method that finds the smallest element in a two-dimensional array
    public static int[] findingSmallest(int [][] a) {
        
        /*Initialize an integer array to hold variables representing the
        smallest value along with the row and column indices for its location in
        the two-dimensional array*/
        int[] location = new int[] {0, 0, 0};
        
        //Set a value equal to the first element in the array
        int value = a[0][0];
        //Make a for loop that selects the rows of the array
        for (int r = 0; r < a.length; r++) {
            
            //Make a for loop that selects the elements in each row of the array
            for (int c = 0; c < a[r].length; c++) {
                
                /*If the value selected by the for loops is less than the
                current contents of the variable "value" then the row and
                column indices of the selected value are assigned to the second
                and third elements in the location array*/
                if (a[r][c] < value) {
                    location[1] = r;
                    location[2] = c;
                }
            }
        }
        /*Assign the value of the smallest element to the first element in
        the location array*/
        location[0] = a[location[1]][location[2]];
        
        //Return the Location array
        return location;
    }
}
