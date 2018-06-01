/*The United States federal personal income tax is calculated based on filing status and
taxable income. There are four filing statuses: single filers, married filing jointly,
married filing separately, and head of household. The tax rates vary every year. Table 1
below shows the rates for 2009. If you are, say, single with a taxable income of $10,000,
the first $8,350 is taxed at 10% and the other $1,650 is taxed at 15%. So, your tax is
$1,082.5.

You need to write a program to compute personal income tax. Your program should
prompt the user to enter the filing status and taxable income and compute the tax. Enter 0
for single filers, 1 for married filing jointly, 2 for married filing separately, and 3 for head
of household.*/
package computingtax;

import java.util.Scanner;

public class ComputingTax {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //Prompt input of filing status
        System.out.print("Enter filing status: (0 - Single filer, 1 -"
                + " Married filing jointly or qualified widow(er), "
                + "2 - Married filing separately, 3 - Head of household): ");
        int status = input.nextInt();

        //Prompt input of taxable income
        System.out.print("Enter the taxable income: ");
        double income = input.nextDouble();

        //Compute tax
        double tax = 0;

        //Single filers
        switch (status) { //Switch statement
            case 0:
                if (income <= 8350) {
                    tax = income * 0.10;
                } else if (income <= 33950) {
                    tax = 8350 * 0.10 + (income - 8350) * 0.15;
                } else if (income <= 82250) {
                    tax = 8350 * 0.10 + (33950 - 8350) * 0.15
                            + (income - 33950) * 0.25;
                } else if (income <= 171550) {
                    tax = 8350 * 0.10 + (33950 - 8350) * 0.15
                            + (82250 - 33950) * 0.25 + (income - 82250) * 0.28;
                } else if (income <= 372950) {
                    tax = 8350 * 0.10 + (33950 - 8350) * 0.15
                            + (82250 - 33950) * 0.25 + (171550 - 82250) * 0.28
                            + (income - 171550) * 0.33;
                } else {
                    tax = 8350 * 0.10 + (33950 - 8350) * 0.15
                            + (82250 - 33950) * 0.25 + (171550 - 82250) * 0.28
                            + (372950 - 171550) * 0.33 + (income - 372951)
                            * 0.35;
                }
                break;
            //Married filing jointly or qualified widow(er)
            case 1:
                if (income <= 16700) {
                    tax = income * 0.10;
                } else if (income <= 67900) {
                    tax = 16700 * 0.10 + (income - 16700) * 0.15;
                } else if (income <= 137050) {
                    tax = 16700 * 0.10 + (67900 - 16700) * 0.15
                            + (income - 67900) * 0.25;
                } else if (income <= 208850) {
                    tax = 16700 * 0.10 + (67900 - 16700) * 0.15
                            + (137050 - 67900) * 0.25 + (income - 137050)
                            * 0.28;
                } else if (income <= 372950) {
                    tax = 16700 * 0.10 + (67900 - 16700) * 0.15
                            + (137050 - 67900) * 0.25 + (208850 - 137050) * 0.28
                            + (income - 208850) * 0.33;
                } else {
                    tax = 16700 * 0.10 + (67900 - 16700) * 0.15
                            + (137050 - 67900) * 0.25 + (208850 - 137050) * 0.28
                            + (372951 - 208850) * 0.33 + (income - 372951)
                            * 0.35;
                }
                break;
            //Married filing separately
            case 2:
                if (income <= 8350) {
                    tax = income * 0.10;
                } else if (income <= 33950) {
                    tax = 8350 * 0.10 + (income - 8350) * 0.15;
                } else if (income <= 68525) {
                    tax = 8350 * 0.10 + (33950 - 8350) * 0.15
                            + (income - 33950) * 0.25;
                } else if (income <= 104425) {
                    tax = 8350 * 0.10 + (33950 - 8350) * 0.15
                            + (68525 - 33950) * 0.25 + (income - 68525) * 0.28;
                } else if (income <= 186475) {
                    tax = 8350 * 0.10 + (33950 - 8350) * 0.15
                            + (68525 - 33950) * 0.25 + (104425 - 68525) * 0.28
                            + (income - 104425) * 0.33;
                } else {
                    tax = 8350 * 0.10 + (33950 - 8350) * 0.15
                            + (68525 - 33950) * 0.25 + (104425 - 68525) * 0.28
                            + (186475 - 104425) * 0.33 + (income - 186475)
                            * 0.35;
                }
                break;
            //Head of household
            case 3:
                if (income <= 11950) {
                    tax = income * 0.10;
                } else if (income <= 45500) {
                    tax = 11950 * 0.10 + (income - 11950) * 0.15;
                } else if (income <= 117450) {
                    tax = 11950 * 0.10 + (45500 - 11950) * 0.15
                            + (income - 45500) * 0.25;
                } else if (income <= 190200) {
                    tax = 11950 * 0.10 + (45500 - 11950) * 0.15
                            + (117450 - 45500) * 0.25 + (income - 117450)
                            * 0.28;
                } else if (income <= 372950) {
                    tax = 11950 * 0.10 + (45500 - 11950) * 0.15
                            + (117450 - 45500) * 0.25 + (190200 - 117450) * 0.28
                            + (income - 190200) * 0.33;
                } else {
                    tax = 11950 * 0.10 + (45500 - 11950) * 0.15
                            + (117450 - 45500) * 0.25 + (190200 - 117450) * 0.28
                            + (372950 - 190200) * 0.33 + (income - 372950)
                            * 0.35;
                }
                break;
            default:
                System.out.println("Error: invalid status");
                System.exit(1);
        }
        System.out.println("Tax is: $" + (int) (tax * 100) / 100.0);
    }

}
