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
        Integer[] array = new Integer [n];
        
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

    //Method to sort an arraylist
    public static void sortArrayList(ArrayList<Integer> list) {
        //Loop to select each value in the array list
        for (int i = 0; i < list.size(); i++) { 
            int selectedIndex = i;
            Integer selected = list.get(i); 
            
            
            /*Compare the next value in the arraylist to the currently selected
            value. If the currently selected value is greater, the next value
            becomes the selected value.*/
            for (int k = i + 1; k < list.size(); k++) {
                if (selected > list.get(k)) {
                    selected = list.get(k);
                    selectedIndex = k;
                }
            }   
            
            /*If the selected value is no longer the originally selected value
            put the selected value in the index of the original value, and
            set the original value as the selected value again before
            repeating the loop.*/
            if (selectedIndex != i) {
                list.set(selectedIndex, list.get(i));
                list.set(i, selected);
            }
        }
    }
}
