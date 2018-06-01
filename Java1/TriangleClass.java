/*Design a class named Triangle that extends GeometricObject. The class contains:
• Three double data fields named side1, side2, and side3 with default values 0.0 to
denote three sides of the triangle.
• A no-arg constructor that creates a default triangle with default values 1.0 to each
of the three sides of the triangle.
• A constructor that creates a triangle with the specified side1, side2, and side3.
• The accessor methods for all three data fields
• A method named getArea() that returns the area of this triangle.
Hint: The area of a triangle could be computed using the following formula
• A method named getPerimeter() that returns the perimeter of this triangle.
Hint: The Perimeter of a triangle is the sum of its sides.
• A method named toString() that returns a string description for the triangle. For
the description returned by toString().*/


package triangleclass;

public class TriangleClass {
    public static void main(String[] args) {
        Triangle triangle = new Triangle (1, 1.5, 1);
        triangle.setColor("yellow");
        triangle.setFilled(true);
        
        System.out.println("The area is " + triangle.getArea());
        System.out.println("The perimeter is " + triangle.getPerimeter());
        System.out.println(triangle.toString());
    }
}

//Geometric object class from pg. 413
class GeometricObject {
    private String color = "white";
    private boolean filled;
    private java.util.Date dateCreated;
    
    //Construct default geometric object
    public GeometricObject() {
        dateCreated = new java.util.Date();  
    }
    
    //Construct a geometric object with the specified color and filled value
    public GeometricObject(String color, boolean filled) {
        dateCreated = new java.util.Date();
        this.color = color;
        this.filled = filled;
    }
    
    //Return color
    public String getColor() {
        return color;
    }
    
    //Set a new color
    public void setColor (String color) {
        this.color=color;
    }
    
    //Return filled. since filled is boolean, getter method is named isFilled
    public boolean isFilled() {
        return filled;
    }
    
    //Set a new filled
    public void setFilled(boolean filled) {
        this.filled = filled;
    }
    
    //Get dateCreated
    public java.util.Date getDateCreated() {
        return dateCreated;
    }
    
    //Return a string representation of this object
    public String toString() {
        return "created on " + dateCreated + "\ncolor: " + color +
                " and filled: " + filled;
    }
}

class Triangle extends GeometricObject {
    //Sides 1, 2, and 3 with default values 0.0
    private double side1 = 0.0;
    private double side2 = 0.0;
    private double side3 = 0.0;
    
    //Set each side length
    public Triangle(double side1, double side2, double side3){
            this.side1 = side1;
            this.side2 = side2;
            this.side3 = side3;
    }
    
    //Default no-arg constructor
    public Triangle() {
        this.side1 = 1.0;
        this.side2 = 1.0;
        this.side3 = 1.0;
    }
    
    //Accessor methods for each side
    public double getSide1() {
        return side1;
    }
    public double getSide2() {
        return side2;
    }
    public double getSide3() {
        return side3;
    }
    
    //Accessor method for area using given formula
    public double getArea() {
        double p = (side1 + side2 + side3) / 2.0;
        return Math.sqrt(p * (p - side1) * (p - side2) * (p - side3));
    }
    
    //Accessor method for perimeter
    public double getPerimeter() {
        return side1 + side2 + side3;
    }
    
    //Override the toString method in Geometric object
    //Return triangle details and details from the superclass
    @Override
    public String toString() {
        return "Triangle: side 1 = " + side1 + " side 2 = " + side2 +
                " side 3 = " + side3 + "\n" + super.toString();
    }
}
