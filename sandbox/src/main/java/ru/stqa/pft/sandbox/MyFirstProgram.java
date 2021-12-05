package ru.stqa.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        System.out.println("Hello");

        Point p1 = new Point(1, -7);
        Point p2 = new Point(-4, 5);

        System.out.println("Расстояние между двумя точками функцией P1(" + p1.x + "," + p1.y + ")" + " и P2(" + p2.x + "," + p2.y + ")" + " равно: " + p2.distance(p1, p2));
        System.out.println("Расстояние между двумя точками методом P1(" + p1.x + "," + p1.y + ")" + " и P2(" + p2.x + "," + p2.y + ")" + " равно: " + p1.distance(p2));

    }

}