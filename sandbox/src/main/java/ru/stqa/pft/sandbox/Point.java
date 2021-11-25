package ru.stqa.pft.sandbox;

public class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
     public double distance(Point p2){
        return Math.sqrt((p2.x - this.x) * (p2.x - this.x) + (p2.y - this.y) * (p2.y - this.y));
     }
}

class PointSt {
    public static void main(String[] args) {

        Point p1 = new Point(1, -7);
        Point p2 = new Point(-4, 5);
        System.out.println(p1.distance(p2));
    }
}