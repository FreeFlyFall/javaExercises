/*A university posts its employees’ salaries at http://cs.armstrong.edu/liang/data/Salary.txt.
Each line in the file consists of a faculty member’s first name, last name, rank, and salary.
Write a program to display the total salary for assistant professors, associate professors,
full professors, and faculty (all professors), respectively, and display the average salary for
assistant professors, associate professors, full professors, and faculty (all professors),
respectively as well as write these displayed information to a file named
“Salaries_Summary.txt”.
Note: Your program needs to access the file directly from the Website (don’t download it).*/

package processwebdatafile;

import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class ProcessWebDataFile {

    //Create final integers representing each of the types of professors
    private static final int ASSISTANT = 0;//Assistant professor
    private static final int ASSOCIATE = 1;//Associate professor
    private static final int FULL = 2;//Full professor

    //Create the main method that throws an exception for malformed URL
    public static void main(String[] args) throws Exception {
        
        //Create a new file to be written to named "Salaries_Summary.txt"
        java.io.File file = new java.io.File("Salaries_Summary.txt");
        //If the file already exists, tell the user and terminate the program
        if(file.exists()) {
            System.out.println("File already exists");
            System.exit(1);
        }
        
        //Create arraylists to hold the 3 professor types and their salaries
        ArrayList<ArrayList<Double>> allRanks = new ArrayList<>(3);
        allRanks.add(ASSISTANT, new ArrayList<>());
        allRanks.add(ASSOCIATE, new ArrayList<>());
        allRanks.add(FULL, new ArrayList<>());

        //Create a URL object
        URL url = new URL("http://cs.armstrong.edu/liang/data/Salary.txt");
        //Create a Scanner object
        Scanner input = new Scanner(url.openStream());

        /*If there is still data to be read from the URL page, input it to a
        particular arraylist based on its type*/
        while (input.hasNext()) {
            String firstName = input.next();
            String lastName = input.next();
            String rank = input.next();
            if (rank.contains("assistant")) {
                allRanks.get(ASSISTANT).add(input.nextDouble());
            } else if (rank.contains("associate")) {
                allRanks.get(ASSOCIATE).add(input.nextDouble());
            } else {
                allRanks.get(FULL).add(input.nextDouble());
            }
        }

        //Set the total salary for each type of professor
        double assistantTotal = 
                (double)getTotal(allRanks.get(ASSISTANT).toArray());
        double associateTotal = 
                (double)getTotal(allRanks.get(ASSOCIATE).toArray());
        double fullTotal = (double)getTotal(allRanks.get(FULL).toArray());
        //Set the total salary of all professors
        double total = assistantTotal + associateTotal + fullTotal;
        
        
        //Create PrintWriter
        java.io.PrintWriter output = new java.io.PrintWriter(file);
            //Print salary summary to the previously created file
            output.println("Total salary for assistant professor is " +
                    assistantTotal);
            output.println("Total salary for associate professor is " +
                    associateTotal);
            output.println("Total salary for full professor is " + fullTotal);
            output.println("Total salary for all professors is " + total);
            output.println("Average salary for assistant professor is " +
                    getAverage(assistantTotal, allRanks.get(ASSISTANT).size()));
            output.println("Average salary for associate professor is " +
                    getAverage(associateTotal, allRanks.get(ASSOCIATE).size()));
            output.println("Average salary for full professor is " +
                    getAverage(fullTotal, allRanks.get(FULL).size()));
            output.printf("Average salary for all professors is %.11f \n", 
                    getAverage(total, getInternalSize(allRanks)));
        //Close the file
        output.close();
        
        //Print the salary summary to the screen
        System.out.printf("Total salary for assistant professor is %.2f \n", 
                assistantTotal);
        System.out.printf("Total salary for associate professor is %.2f \n", 
                associateTotal);
        System.out.printf("Total salary for full professor is %.2f \n", 
                fullTotal);
        System.out.printf("Total salary for all professors is %.2f \n", total);
        System.out.printf("Average salary for assistant professor is %.2f \n", 
                getAverage(assistantTotal, allRanks.get(ASSISTANT).size()));
        System.out.printf("Average salary for associate professor is %.2f \n", 
                getAverage(associateTotal, allRanks.get(ASSOCIATE).size()));
        System.out.printf("Average salary for full professor is %.2f \n", 
                getAverage(fullTotal, allRanks.get(FULL).size()));
        System.out.printf("Average salary for all professors is %.2f \n", 
                getAverage(total, getInternalSize(allRanks)));
    }

    //Method to get the total salaries from the object array
    private static double getTotal(Object[] objects) {
        //Set size to 0
        double total = 0;
        //for each object, increase the total by the salary amount
        for (Object o : objects) {
            total += (double)o;
        }
        /*Return the total of all salaries corresponding to the type of
        professor that was passed*/
        return total;
    }
    
    //Method to find the total number of all types of professors
    private static double getInternalSize(ArrayList<ArrayList<Double>> lists) {
        //Set size to 0
        double size = 0;
        //For each element in the arraylist, increase size
        for (ArrayList<Double> list : lists) {
            size += list.size();
        }
        //Return the size of the arraylist
        return size;
    }
    
    /*Method to find the average salaries for each type of professor or all
    types of profossors. This average is based on the total salary for each
    type and the size of each type*/
    private static double getAverage(double total, double size) {
        //Return the average
        return total / size;
    }
}
