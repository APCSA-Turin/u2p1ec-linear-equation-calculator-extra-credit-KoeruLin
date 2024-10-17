package com.example.project;
import java.text.DecimalFormat;

public class LinearCalculator
{
        //INSTANCE VARIABLES 
    //4 INTEGER variables (name them: x1,x2,y1,y2) 

    private int x1;
    private int x2;
    private int y1;
    private int y2;

    DecimalFormat format = new DecimalFormat("#.##");

    //CONSTRUCTOR
    //1 constructor with 2 String parameters. Each parameter represents a coordinate. 
    //For example, "(1,-2)" and "(3,4)" would be two parameter values 
    //You will have to parse the string into 4 integers, representing the 2 points.
    public LinearCalculator(String firstCoordinate, String secondCoordinate)
    {
        String[] firstCoordinateArray = firstCoordinate.replaceAll("[()]", "").split(",");
        String[] secondCoordinateArray = secondCoordinate.replaceAll("[()]", "").split(",");

        x1 = Integer.parseInt(firstCoordinateArray[0]);
        y1 = Integer.parseInt(firstCoordinateArray[1]);
        x2 = Integer.parseInt(secondCoordinateArray[0]);
        y2 = Integer.parseInt(secondCoordinateArray[1]);

        // split the coordinates at , and removes () so only the number remains in the array

    }
    //METHODS
    //getters and setters for the 4 instance variables (8 methods total) 
    public int getX1()
    {
        return x1;
    }
    public int getY1()
    {
        return y1;
    }
    public int getX2()
    {
        return x2;
    }
    public int getY2()
    {
        return y2;
    }
    public void setX1(int x1)
    {
        this.x1 = x1;
    }
    public void setY1(int y1)
    {
        this.y1 = y1;
    }
    public void setX2(int x2)
    {
        this.x2 = x2;
    }
    public void setY2(int y2)
    {
        this.y2 = y2;
    }


    //distance() -> returns a double. 
    //calculates the distance between the two points to the nearest HUNDREDTH and returns the value.
    public double distance()
    {
        String stringDistance = format.format((double)Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
        return Double.parseDouble(stringDistance);

        // distance formula
    }
    //yInt() -> returns a double.
    //calculates the y intercept of the equation and returns the value to the nearest HUNDREDTH
    //if y-int is undefined, should return -999.99
    public double yInt()
    {
        double slope = slope();
        String stringIntercept = format.format((double)y1 - (slope * x1));
        return (slope == -999.99) ? -999.99 : Double.parseDouble(stringIntercept);

        // solved for b
    }

    //slope() -> returns a double. 
    //calculates the slope of the equations and returns the value to the nearest HUNDREDTH
    //if slope is undefined, should return -999.99
    public double slope()
    {
        String stringSlope = format.format((double)(y2 - y1) / (x2 - x1));
        return (x2 - x1 == 0) ? -999.99 : Double.parseDouble(stringSlope);
    }

    //equations() -> returns a String.
    //calculates the final equation in y=mx+b form and returns the string
    //if the equation has no slope, the equation should return -> "undefined"
    //HINT: You may need other custom methods to decrease the amount of code in the equations() method
    public String equation()
    {
        double slope = slope();
        String slopeFormat = (yInt() < 0) ? "y=" + slope +"x" + yInt() : "y=" + slope +"x+" + yInt();
        slopeFormat = (yInt() == 0) ? "y=" + slope +"x" : slopeFormat;
        slopeFormat = (slope == 0) ? "y=" + yInt() : slopeFormat;
        return (slope == -999.99) ? "undefined" : slopeFormat;

        // basically a bunch of statements to remove mx or b if either is 0.
    }


    //roundedToHundredth(double x)-> returns double
    //calculates the input to the nearest hundredth and returns that value
    public double roundedToHundredth(double x)
    {
        String stringRounded = format.format(x);
        return Double.parseDouble(stringRounded);
    }

    //You will need to concatenate to the string 
    //the results from findSymmetry() and Midpoint()
    public String printInfo()
    {
        String str = "The two points are: (" + x1 + "," + y1 + ")";
        str += " and " + "(" + x2 + "," + y2 + ")";
        str += "\nThe equation of the line between these points is: " + equation();
        str += "\nThe slope of this line is: " + slope();
        str += "\nThe y-intercept of the line is: " + yInt();
        str += "\nThe distance between the two points is: " + distance();
        str += "\n"+ findSymmetry();
        str += "\n"+ Midpoint();
 
        return str;

        // add Midpoint and findSymmetry
    }

    //findSymmetry()-> returns a string 
    //the method should determine if there is symmetry between the two points
    // there should be  4 return statements 
    // return "Symmetric about the x-axis";
    // return "Symmetric about the y-axis";
    //return "Symmetric about the origin";
    //return "No symmetry";
    public String findSymmetry()
    {
        String result = 
        (x1 == -x2 && y1 == -y2) ? "Symmetric about the origin" : 
        (x1 ==  -x2) ? "Symmetric about the y-axis" :
        (y1 == -y2) ? "Symmetric about the x-axis" : "No symmetry";
        
        // triple nested ternary operands checking if it's symmetric about origin then y-axis then x-axis before saying no symmetr
        return result;
    }

    //Midpoint()->return a string 
    //the method should calculate the midpoint between the two points
    //it should return "The midpoint of this line is: (0,0)";
    public String Midpoint()
    {
        double xMidpoint = (double)(x1 + x2) / 2;
        double yMidpoint = (double)(y1 + y2) / 2;
        return "The midpoint of this line is: (" + xMidpoint + "," + yMidpoint + ")";
    }

    public static void main(String[] args)
    {
        LinearCalculator c = new LinearCalculator("(1,2)", "(3,4)");
        
        System.out.println(c.printInfo());
    }

}



