package geometry;

import java.util.*;
public class RadialGraph extends Shape {
    private Point center;
    private List<Point> neighbors;

    public RadialGraph(Point center, List<Point> neighbors) throws IllegalArgumentException {
          this.center=center;
          double current=distance(neighbors.get(0), center);
          for(int i=1; i<neighbors.size(); i++)
          {
              if(distance(neighbors.get(i), center)!=current)
              {
                  throw new IllegalArgumentException("Invalid Radial Graph. Edges are different lengths.");
              }
          }
          this.neighbors=neighbors;
    }

    public RadialGraph(Point center) {
          this.center=center;
    }

    @Override
    public RadialGraph rotateBy(int degrees) {
        double radians= Math.toRadians(degrees);
        if(neighbors==null)
        {
            return this;
        }
        RadialGraph newGraph=new RadialGraph(center, neighbors);
        double offsetInX=0-center.x;
        double offsetInY=0-center.y;
        newGraph=newGraph.translateBy(offsetInX,offsetInY);
        offSet(newGraph,newGraph.neighbors, radians);
        newGraph=newGraph.translateBy(-offsetInX,-offsetInY);
        return newGraph;
    }

    private void offSet( RadialGraph graph, List<Point> newList, double radians)
    {
        for(int i=0; i<graph.neighbors.size(); i++)
        {
            Point current= graph.neighbors.get(i);
            double newX= current.x * Math.cos(radians) - current.y*Math.sin(radians);
            double newY= current.x * Math.sin(radians)+ current.y*Math.cos(radians);
            newList.set(i,new Point(current.name,round(newX), round(newY)));
        }
    }
    private double distance(Point a, Point b)
    {
        double x=(b.x-a.x)*(b.x-a.x);
        double y=(b.y-a.y)*(b.y-a.y);
       return Math.sqrt(x+y);
    }
    @Override
    public RadialGraph translateBy(double x, double y) {
        if(neighbors==null)
        {
            return new RadialGraph(changed(center, x, y));
        }
       RadialGraph newGraph=new RadialGraph(center, neighbors);
       newGraph.center=changed(center, x, y);
       for(int i=0; i<newGraph.neighbors.size(); i++)
       {
           newGraph.neighbors.set(i,changed(newGraph.neighbors.get(i), x, y));
       }
       return newGraph;

    }
    private double round(double in)
    {
       return Math.round(in*100)/100.00;
    }
    @Override
    public String toString() {
        String output="["+ center.toString();
        if (neighbors==null)
        {
            return "[" + center.toString() + "]";
        }
        else
        {
            double offsetInX=0-center.x;
            double offsetInY=0-center.y;
            RadialGraph x=translateBy(offsetInX,offsetInY);
            Collections.sort(x.neighbors, new PointOrderingComparator());
            x.translateBy(-offsetInX,-offsetInY);

            for(Point a: x.neighbors)
            {
                output += "; " + a.toString();
            }
        }
        return output+"]" ;
    }
    @Override
    public Point center() {
        return this.center;
    }

    private Point changed(Point curr, double changeInX, double changeInY)
    {
        double newX= curr.x + changeInX;
        double newY=curr.y +changeInY;
        return new Point(curr.name, newX, newY) ;
    }

    /* Driver method given to you as an outline for testing your code. You can modify this as you want, but please keep
     * in mind that the lines already provided here as expected to work exactly as they are (some lines have additional
     * explanation of what is expected) */
    public static void main(String... args) {
        Point center = new Point("center", 0, 0);
        Point east = new Point("east", 5, 0);
        Point west = new Point("west",3, 4);
        Point north = new Point("north", 0, 5);
        Point south = new Point("south", -5, 0);
        Point toofarsouth = new Point("toosouth", -3, -4);
        Point last=new Point("last", 0,-5);
        // A single node is a valid radial graph.
        RadialGraph lonely = new RadialGraph(center);

        // Must print: [(center, 0.0, 0.0)]
        //  System.out.println(lonely);
        //  lonely= lonely.rotateBy(90);
         // System.out.println(lonely);

        // This line must throw IllegalArgumentException, since the edges will not be of the same length
        //RadialGraph nope = new RadialGraph(center, Arrays.asList(north, toofarsouth, east, west));

       Shape g = new RadialGraph(center, Arrays.asList(north, south, east, west,toofarsouth, last));

        // Must follow the documentation in the Shape abstract class, and print the following string:
        // [(center, 0.0, 0.0); (east, 1.0, 0.0); (north, 0.0, 1.0); (west, -1.0, 0.0); (south, 0.0, -1.0)]
        System.out.println(g);

        // After this counterclockwise rotation by 90 degrees, "north" must be at (-1, 0), and similarly for all the
        // other radial points. The center, however, must remain exactly where it was.
         g = g.rotateBy(90);
        System.out.println(g);

        // you should similarly add tests for the translateBy(x, y) method
    }
}
