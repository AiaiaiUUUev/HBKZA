package com.example.d.healthbook.UI;

public class Size
{
    private double x;
    private double y;

    public Size(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public double getWidth() {return (x >= 0 && x < Double.MAX_VALUE) ? x : 0; }
    public double getHeight() {return (y >= 0 && y < Double.MAX_VALUE) ? y : 0; }
}
