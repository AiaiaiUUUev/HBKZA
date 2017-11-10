package com.example.d.healthbook.UI;

public class Rect
{
    private double x;
    private double y;
    private double width;
    private double height;

    public Rect(double x, double y, double width, double height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public double getX() {return (x >= -Double.MAX_VALUE && x < Double.MAX_VALUE) ? x : 0; }
    public double getY() {return (y >= -Double.MAX_VALUE && y < Double.MAX_VALUE) ? y : 0; }
    public double getWidth() {return (width >= 0 && width < Double.MAX_VALUE) ? width : 0; }
    public double getHeight() {return (height >= 0 && height < Double.MAX_VALUE) ? height : 0; }
}
