/*Implement the following method that sorts an ArrayList of integers:

    public static void sort(ArrayList<Integer> list){
    }

Write a test program that prompts the user to enter a number of integers, stores them in an
array list (i.e., ArrayList), call the sort method to have this list of integers sorted in
increasing order, and displays them.
Note: You are not allowed to use any predefined sorting method in your implementation
of the sort method.*/

package sortarraylist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SortArrayList {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in); //Create scanner
        //Prompt user to input the number of integers
        System.out.print("How many integers you want to enter? ");
        int n = input.nextInt();
        
        //Prompt user to input each of the integers
        System.out.print("Enter " + n + " integers: ");
        
        //Create an array with the size of variable n to hold the values
        Integer[] array = new Integer[n];
        
        //Loop to assign the values to each consecutive index of an array
        for (int i = 0; i < n; i++) {
            array[i] = input.nextInt();
        }
        
        //Create an arraylist from the array using the asList method
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(array));
        
        //Sort the arraylist using a created method
        sortArrayList(list);
                
        //Print the arraylist, excluding the brackets and commas
        System.out.println
        (list.toString().substring(1).replaceFirst("]", "").replace(", ", " "));
    }    

    //Method to sort an arraylist by placing the lowest value of an array first
    public static void sortArrayList(ArrayList<Integer> list) {
        /*Loop through the next loop(the sorting loop) a number of times, 
         *according to the number of values the arraylist holds holds.
         */
        for (int i = 0; i < list.size(); i++) {
            /*Variable extracted from the array to serve as a substitute value
             *(Starts by holding the initial index's value for each loop, i).
             */
            int sub = list.get(i);
            //Selected index (starts at i)
            int selectedIndex = i;
            
            /*Loop to compare the value in the next index of the arraylist to
             *sub. If the value in the next index of the arraylist is greater than
             *sub, then sub becomes the value of the next index(value at index of 
             *k), and the selected index becomes the next index of the array(k).
             */
            for (int k = i + 1; k < list.size(); k++) {
                if (sub > list.get(k)) {
                    sub = list.get(k);
                    selectedIndex = k;
                }
            }   
            
            /*If the selected index is not the index of the array that it was 
             *initially, set the selected index's element(k) to be the initially
             *selected element of the arraylist(value at index of i), and set the
             *initially selected element to be the value of sub.
             */
            if (selectedIndex != i) {
                list.set(selectedIndex, list.get(i));
                list.set(i, sub);
            }
        }
    }
}
