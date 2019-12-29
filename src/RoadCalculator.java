import java.awt.*;
import java.util.ArrayList;

public class RoadCalculator {
    private ArrayList<Block> rectangeles;
    private ArrayList<Point> points;
    private Point initialPoint;
    public RoadCalculator(){
        RectangelPointsCreater generator = new RectangelPointsCreater();
        rectangeles = new ArrayList<Block>(generator.getRects());
        points = new ArrayList<Point>(generator.getPoints());
        for (int i = 0; i < rectangeles.size(); i++){
            for (int j =  0; j < points.size(); j++){
                if (PointIsInRectangle(rectangeles.get(i),points.get(j))){
                    points.remove(j);
                }
            }
        }
        initialPoint = new Point((int) points.get(0).getX(),(int) points.get(0).getY());
    }
    public int manhattanDistance(Point a, Point b){
        return (int) (Math.abs(a.getX()-b.getX()) + Math.abs(a.getY()-b.getY()));
    }

    

    public boolean isThereAWay(Point a, Point b){

        int x1 = 0;//smallest x
        int y1 = 0;//smallest y
        int x2 = 0;//biggest  x
        int y2 = 0;//biggest  y

        if(a.x < b.x){
            x1 = a.x;
            x2 = b.x;
        }
        else {
            x2 = a.x;
            x1 = b.x;
        }
        if(a.y < b.y){
            x1 = a.y;
            x2 = b.y;
        }
        else {
            x2 = a.y;
            x1 = b.y;
        }

        boolean found = false;

        Point number1 = new Point(x1,y1);
        Point number2 = new Point(x1,y2);
        Point number3 = new Point(x2,y1);
        Point number4 = new Point(x2,y2);

        for (int i = 0; i < rectangeles.size(); i++){
            if (LineIntersectsRect(number1,number2,rectangeles.get(i))){
                found = true;
                break;
            }
            if (LineIntersectsRect(number2,number4,rectangeles.get(i))){
                found = true;
                break;
            }
            if (LineIntersectsRect(number1,number3,rectangeles.get(i))){
                found = true;
                break;
            }
            if (LineIntersectsRect(number3,number4,rectangeles.get(i))){
                found = true;
                break;
            }
        }
    return found;
    }

    public boolean LineIntersectsRect(Point p1, Point p2, Block r)
    {
        return LineIntersectsLine(p1, p2, new Point(r.getX(), r.getY()), new Point(r.getX() + r.getWidth(), r.getY())) ||
                LineIntersectsLine(p1, p2, new Point(r.getX() + r.getWidth(), r.getY()), new Point(r.getX() + r.getWidth(), r.getY() + r.getHeight())) ||
                LineIntersectsLine(p1, p2, new Point(r.getX() + r.getWidth(), r.getY() + r.getHeight()), new Point(r.getX(), r.getY() + r.getHeight())) ||
                LineIntersectsLine(p1, p2, new Point(r.getX(), r.getY() + r.getHeight()), new Point(r.getX(), r.getHeight())) ||
                (r.Contains(p1) && r.Contains(p2));
    }
    private static boolean LineIntersectsLine(Point l1p1, Point l1p2, Point l2p1, Point l2p2)
    {
        float q = (l1p1.y - l2p1.y) * (l2p2.x - l2p1.y) - (l1p1.x - l2p1.x) * (l2p2.y - l2p1.y);
        float d = (l1p2.y - l1p1.y) * (l2p2.y - l2p1.y) - (l1p2.y - l1p1.y) * (l2p2.x - l2p1.x);

        if( d == 0 )
            return false;

        float r = q / d;

        q = (l1p1.y - l2p1.y) * (l1p2.x - l1p1.x) - (l1p1.x - l2p1.x) * (l1p2.y - l1p1.y);
        float s = q / d;

        if( r < 0 || r > 1 || s < 0 || s > 1 )
            return false;

        return true;
    }

    @Deprecated
    public int computeCode(Block a, double x,double y)
    {
        int INSIDE = 0; // 0000
        int LEFT = 1;   // 0001
        int RIGHT = 2;  // 0010
        int BOTTOM = 4; // 0100
        int TOP = 8;    // 1000
        int x1 = (int) a.getX();
        int y1 = (int) a.getY();
        int x2 = x1 + a.getWidth();
        int y2 = y1 + a.getHeight();
        // initialized as being inside
        int code = INSIDE;

        if (x < x1)       // to the left of rectangle
            code |= LEFT;
        else if (x > x2)  // to the right of rectangle
            code |= RIGHT;
        if (y < y1)       // below the rectangle
            code |= BOTTOM;
        else if (y > y2)  // above the rectangle
            code |= TOP;

        return code;
    }

    @Deprecated
    public boolean pointsAreHorizontallySymetric(Point a, Point b){
        return a.y == b.y;
    }
    @Deprecated
    public boolean pointsAreVerticallySymetric(Point a, Point b){
        return a.x == b.x;
    }
    @Deprecated
    public boolean pointsAreHorizontallySymetricAndThereIsABlock(Block a, Point b, Point c)
    {

        int code1 = computeCode(a,b.getX(),b.getY());
        int code2 = computeCode(a,c.getX(),c.getY());

        if( (code1 == 1) && (code2 == 2) || (code1 == 2) && (code2 == 1) )
            return true;

        return false;

    }
    @Deprecated
    public boolean pointsAreVerticallySymetricAndThereIsABlock(Block a, Point b, Point c)
    {

        int code1 = computeCode(a,b.getX(),b.getY());
        int code2 = computeCode(a,c.getX(),c.getY());

        if( (code1 == 4) && (code2 == 8) || (code1 == 8) && (code2 == 4) )
            return true;

        return false;

    }

    public boolean PointIsInRectangle(Block a, Point b)
    {
        // bottom-left and top-right
        // corners of rectangle
        int x1 = (int) a.getX();
        int y1 = (int) a.getY();
        int x2 = x1 + a.getWidth();
        int y2 = y1 + a.getHeight();

        if (b.x > x1 && b.x < x2 &&
                b.y > y1 && b.y < y2)
            return true;

        return false;
    }
}
