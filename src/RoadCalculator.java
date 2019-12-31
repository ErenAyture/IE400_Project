import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class RoadCalculator {
    private ArrayList<Block> rectangeles;
    private ArrayList<Point> points;
    private Point initialPoint;
    private RectangelPointsCreater generator;


    public RoadCalculator(){
        this.generator = new RectangelPointsCreater();
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
        return (Math.abs(a.x-b.x) + Math.abs(a.y-b.y));
    }


    public String create_path(){
        Queue<Point> traverse = new LinkedList<>();
        traverse.add(points.remove(0));

        for (int i = 1; i < points.size(); i++){
            i = find_closest_point_Index(i)-1;
            traverse.add(new Point(points.remove(find_closest_point_Index(i))));
        }

        String str = "";
        ArrayList<Integer> distances = new ArrayList<Integer>();
        ArrayList<Point> distance = new ArrayList<Point>(traverse);
        for (int i = 0; i <  distance.size()-1; i++){
            distances.add(manhattanDistance(distance.get(i),distance.get(i+1)));
        }
        int i = 0;
        while (!(traverse.size()==1)){

            str =str.concat(traverse.poll().toString().substring(14));
            str =str.concat("\n");
            str = str.concat("Distance : "+distances.get(i++));
            str =str.concat("\n");
        }
        str =str.concat(traverse.poll().toString().substring(14));
        str =str.concat("\n");

        System.out.println(str );

        return str;
    }



    int find_closest_point_Index(int index){
        int smallest = Integer.MAX_VALUE;
        int indexPosition = 0;
        for (int i = 1; i < points.size(); i++){
            if(manhattanDistance(points.get(index),points.get(i))<smallest
                    && ( ( !( points.get(index).equals( points.get(i) ) ) ) &&generator.calculate_number_of_ways(points.get(index),points.get(i))> 0)){
                indexPosition = i;
                smallest = manhattanDistance(points.get(index),points.get(i));
            }
        }

        return indexPosition;
    }
    @Deprecated
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
            y1 = a.y;
            y2 = b.y;
        }
        else {
            y2 = a.y;
            y1 = b.y;
        }

        boolean found1 = true;
        boolean found2 = true;
        Point number1 = new Point(x1,y1);
        Point number2 = new Point(x1,y2);
        Point number3 = new Point(x2,y1);
        Point number4 = new Point(x2,y2);

        for (int i = 0; i < rectangeles.size(); i++) {
            if (LineIntersectsRect(number1, number2, rectangeles.get(i))) {
                found1 = false;
                break;
            }
            if (LineIntersectsRect(number2, number4, rectangeles.get(i))) {
                found1 = false;
                break;
            }
        }
        for (int i = 0; i < rectangeles.size(); i++) {
            if (LineIntersectsRect(number1, number3, rectangeles.get(i))) {
                found2 = false;
                break;
            }
            if (LineIntersectsRect(number3, number4, rectangeles.get(i))) {
                found2 = false;
                break;
            }
        }

    return found1 || found2;
    }
    @Deprecated
    public boolean LineIntersectsRect(Point p1, Point p2, Block r)
    {
        return LineIntersectsLine(p1, p2, new Point(r.getX(), r.getY()), new Point(r.getX() + r.getWidth(), r.getY())) ||
                LineIntersectsLine(p1, p2, new Point(r.getX() + r.getWidth(), r.getY()), new Point(r.getX() + r.getWidth(), r.getY() + r.getHeight())) ||
                LineIntersectsLine(p1, p2, new Point(r.getX() + r.getWidth(), r.getY() + r.getHeight()), new Point(r.getX(), r.getY() + r.getHeight())) ||
                LineIntersectsLine(p1, p2, new Point(r.getX(), r.getY() + r.getHeight()), new Point(r.getX(), r.getHeight())) ||
                (r.Contains(p1) && r.Contains(p2));
    }
    @Deprecated
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
