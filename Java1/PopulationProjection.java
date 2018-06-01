/*"The U.S. Census Bureau projects populations based on the following assumptions:
- One birth every 7 seconds
- One death every 13 seconds
- One new immigrant every 45 seconds
Create a program using NetBeans IDE to display the population for each of the next five years.
Assume that the current population is 312,032,486, and one year has 365 days."
 */

public class PopulationProjection {

    public static void main(String[] args) {
        System.out.print("The rate of growth in population per year = ");
        //The following is the equation to determine the rate of population change over a year.
        System.out.println((60.0 / 7.0 + 60.0 / 45.0) * 60.0 * 24.0 * 365.0 - 60.0 / 13.0 * 60.0 * 24.0 * 365.0);
        System.out.println("If the current population is 312,032,486, the population for each of the next five years is:");
        /*Below, the growth/yr figure is added on to current population at the end of each year year.
            This is found by muliplying the growth/yr figure by the number of years that have passed, and adding it on to the original population.
         */
        System.out.print("Year one: ");
        System.out.println(312032486.0 + ((60.0 / 7.0 + 60.0 / 45.0) * 60.0 * 24.0 * 365.0 - 60.0 / 13.0 * 60.0 * 24.0 * 365.0));
        System.out.print("Year two: ");
        System.out.println(312032486.0 + (2.0 * ((60.0 / 7.0 + 60.0 / 45.0) * 60.0 * 24.0 * 365.0 - 60.0 / 13.0 * 60.0 * 24.0 * 365.0)));
        System.out.print("Year three: ");
        System.out.println(312032486.0 + (3.0 * ((60.0 / 7.0 + 60.0 / 45.0) * 60.0 * 24.0 * 365.0 - 60.0 / 13.0 * 60.0 * 24.0 * 365.0)));
        System.out.print("Year four: ");
        System.out.println(312032486.0 + (4.0 * ((60.0 / 7.0 + 60.0 / 45.0) * 60.0 * 24.0 * 365.0 - 60.0 / 13.0 * 60.0 * 24.0 * 365.0)));
        System.out.print("Year five: ");
        System.out.println(312032486.0 + (5.0 * ((60.0 / 7.0 + 60.0 / 45.0) * 60.0 * 24.0 * 365.0 - 60.0 / 13.0 * 60.0 * 24.0 * 365.0)));
    }
}
