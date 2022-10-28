package geometry;
import java.util.Comparator;
public class PointOrderingComparator implements Comparator<Point>
{
    public int compare(Point firstPoint, Point secondPoint)
    {
        double one=(Math.atan2(firstPoint.y, firstPoint.x));
        double two=(Math.atan2(secondPoint.y, secondPoint.x));
        if(one<0)
        {
            one=one+2*(3.14);
        }
        if(two<0)
        {
            two=two+2*(3.14);
        }
        if(one>two)
        {
            return 1;
        }
        else if(one==two)
        {
            return 0;
        }
        else
        {
            return -1;
        }
    }
}
