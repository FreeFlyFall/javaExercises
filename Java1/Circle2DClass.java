/*Define the Circle2D class that contains:
•	Two double data fields named x and y that specify the center of the circle with get
methods.
•	A data field radius with a get method.
•	A no-arg constructor that creates a default circle with (0, 0) for (x, y) and 1 for
radius.
•	A constructor that creates a circle with the specified x, y, and radius.
•	A method getArea() that returns the area of the circle.
Hint: The area of a circle is A=?r2
(you can use Math.PI to get the value of ?)
•	A method getPerimeter() that returns the perimeter of the circle.
Hint: The Perimeter of a circle is 2?r (you can use Math.PI to get the value of ?)
•	A method contains(double x, double y) that returns true if the specified point (x, y)
is inside this circle. See Figure 1 (a).
Hint: The point ?x, y? is inside the circle if the distance between that point and the
center of the circle is less than the circle radius (d<r). It’s on the circle if d=r. Please
see this link to know how to compute the distance between two points:
https://math.stackexchange.com/questions/198764/how-to-know-if-a-point-isinside-a-circle
•	A method contains(Circle2D circle) that returns true if the specified circle is inside
this circle. See Figure 1(b).
Hint: Please see this link for the math of how to check if a circle is contained within
another circle:
https://stackoverflow.com/questions/33490334/check-if-a-circle-is-contained-inanother-circle
•	A method overlaps(Circle2D circle) that returns true if the specified circle overlaps
with this circle. See Figure 1(c).
Hint: Two circles overlap if the distance between the two centers are less than or
equal the sum of their radii.
•	(a) A point is inside the circle. (b) A circle is inside another circle. (c) A circle overlaps another circle.
Implement the class. Write a test program that creates a Circle2D object c1 (new
Circle2D(2, 2, 5.5)), displays its area and perimeter, and displays the result of
c1.contains(3, 3), c1.contains(new Circle2D(4, 5, 10.5)), and c1.overlaps(new Circle2D(3,
5, 2.3)).*/

package circle2dclass;

public class Circle2DClass {
    public static void main (String[] args) {
        
        //Create a new Circle named c1
        Circle2D c1 = new Circle2D(2, 2, 5.5);
        //Print the Area and Perimeter of c1
        System.out.println("Area is " + c1.getArea());
        System.out.println("Perimeter is " + c1.getPerimeter());
        //Determine whether c1 contains a specified point
        System.out.println(c1.contains(3, 3));
        //Determine whether this new circle is contained within c1
        System.out.println(c1.contains(new Circle2D(4, 5, 10.5)));
        //Determine whether this new circle overlaps c1
        System.out.println(c1.overlaps(new Circle2D(3, 5, 2.3)));
 }
}

class Circle2D {
    // Implement the class here
    private double x;
    private double y;
    private double radius;
    
    //Set x, y, and radius values from constructed circle
    public Circle2D(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
    
    //No-arg constructor creating a default circle, (0,0), r = 1
    public Circle2D() {
        this(0,0,1);
    }
    
    //Getter and setter(accessor and mutator) methods for x, y, and the radius
    public void setX(double x) {
        this.x = x;
    }
    public double getX() {
        return x;
    }
    public void setY(double y) {
        this.y  = y;
    }
    public double getY() {
        return y;
    }
    public void setRadius(double radius) {
        this.radius = radius;
    }
    public double getRadius() {
        return radius;
    }
    
    //Getter(accessor) methods for the Area and Perimeter
    public double getArea() {
        return radius * radius * Math.PI;
    }
    public double getPerimeter() {
        return 2 * radius * Math.PI;
    }
    
    //Return true if the point (x, y) is inside c1
    public boolean contains(double x, double y) {
        return Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y-this.y, 2))
                < radius;
    }
    
    //Return true if this circle is enveloped by c1
    public boolean contains(Circle2D circle) {
        return  Math.sqrt(Math.pow(circle.getX() - x, 2) +
                Math.pow(circle.getY() - y, 2)) + radius
                > circle.getRadius();
    
    }
    
    //Return true if this circle overlaps c1
    public boolean overlaps(Circle2D circle) {
        return Math.sqrt(Math.pow(circle.getX() - x, 2) +
                Math.pow(circle.getY() - y, 2))
                <= radius + circle.getRadius();
    }
}
