package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testdistance() {
        Point p1 = new Point(1, -7);
        Point p2 = new Point(-4, 5);
        Assert.assertEquals(p1.distance(p2), 13.0);

    }

    @Test
    public void testdistance2() {
        Point p1 = new Point(1, 7);
        Point p2 = new Point(4, 3);
        Assert.assertEquals(p1.distance(p2), 5.0);
    }

    @Test
    public void testdistance3() {
        Point p1 = new Point(2, 7);
        Point p2 = new Point(-2, 7);
        Assert.assertEquals(p1.distance(p2), 4.0);
    }
}
