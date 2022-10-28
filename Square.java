package geometry;
import java.util.*;
public class Square extends Shape {
    private List<Point> sides;
    private Point center;

    @Override
    public Point center() {
        double midpointX = (sides.get(0).x + sides.get(2).x) / 2;
        double midpointY = (sides.get(0).y + sides.get(2).y) / 2;
        return new Point("center", midpointX, midpointY);
    }

    @Override
    public Square rotateBy(int degrees) {
        double radians = Math.toRadians(degrees);
        Square newGraph = this;
        double offsetInX = 0 - center.x;
        double offsetInY = 0 - center.y;
        newGraph = (Square) newGraph.translateBy(offsetInX, offsetInY);
        offSet(newGraph, newGraph.sides, radians);
        newGraph = (Square) newGraph.translateBy(-offsetInX, -offsetInY);
        return newGraph;
    }

    private void offSet(Square s, List<Point> newList, double radians) {
        for (int i = 0; i < s.sides.size(); i++) {
            Point current = s.sides.get(i);
            double newX = current.x * Math.cos(radians) - current.y * Math.sin(radians);
            double newY = current.x * Math.sin(radians) + current.y * Math.cos(radians);
            newList.set(i, new Point(current.name, round(newX), round(newY)));
        }
    }

    private double round(double in) {
        return Math.round(in * 100) / 100.00;
    }

    public List<Point> getSides() {
        return sides;
    }

    @Override
    public Shape translateBy(double x, double y) {
        Square newSquare = this;
        newSquare.center = changed(center, x, y);
        for (int i = 0; i < newSquare.sides.size(); i++) {
            newSquare.sides.set(i, changed(newSquare.sides.get(i), x, y));
        }
        return newSquare;
    }

    private Point changed(Point curr, double changeInX, double changeInY) {
        double newX = curr.x + changeInX;
        double newY = curr.y + changeInY;
        return new Point(curr.name, newX, newY);
    }

    @Override
    public String toString() {
        String output = "[";
        Point center = center();
        double offsetInX = 0 - center.x;
        double offsetInY = 0 - center.y;
        Square newS = (Square) translateBy(offsetInX, offsetInY);
        Collections.sort(newS.sides, new PointOrderingComparator());
        newS.translateBy(-offsetInX, -offsetInY);
        for (Point a : newS.sides)
        {
            output += a.toString()+ "; " ;
        }

        return output.substring(0,output.length()-2) + "]";
    }

    public Square(Point a, Point b, Point c, Point d) throws IllegalArgumentException {
        List<Point> points = Arrays.asList(a, b, c, d);
        double dist = distance(d, a);
        for (int i = 0; i < points.size()-1; i++) {
            if (distance(points.get(i), points.get(i + 1)) != dist) {
                throw new IllegalArgumentException("Not a square");
            }
        }
        if (distance(b, d) == dist * Math.sqrt(2) && distance(a, c) == dist * Math.sqrt(2)) {
            this.sides = points;
        }
        this.center = center();
    }
    private double distance(Point a, Point b) {
        double x = (b.x - a.x) * (b.x - a.x);
        double y = (b.y - a.y) * (b.y - a.y);
        return Math.sqrt(x + y);
    }

    public static void main(String... args) {
        Point first = new Point("first", 3,3);
        Point second = new Point("second",2,3);
        Point third = new Point("third", 2,2);
        Point fourth = new Point("fourth", 3,2);
        Shape square= new Square(first,second, third, fourth);
        square=square.rotateBy(180);
        System.out.println(square);

    }
}