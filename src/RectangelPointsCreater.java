import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RectangelPointsCreater {

    private ArrayList<Block> rects;
    private ArrayList<Point> points;
    RectangelPointsCreater(){
        rects = new ArrayList<Block>();
        points = new ArrayList<Point>();
        rects.add(new Block(29,14,1,1));
        rects.add(new Block(18,42,1,3));
        rects.add(new Block(37,20,3,3));
        rects.add(new Block(24,9,1,3));
        rects.add(new Block(16,41,3,1));
        rects.add(new Block(43,45,3,1));
        rects.add(new Block(40,20,2,2));
        rects.add(new Block(25,6,2,3));
        rects.add(new Block(29,12,1,1));
        rects.add(new Block(27,19,2,3));
        rects.add(new Block(10,27,3,1));
        rects.add(new Block(14,12,2,2));
        rects.add(new Block(22,28,2,2));
        rects.add(new Block(11,33,1,3));
        rects.add(new Block(38,10,2,3));
        rects.add(new Block(9,6,2,3));
        rects.add(new Block(11,14,3,3));
        rects.add(new Block(8,15,2,2));
        rects.add(new Block(11,20,2,3));
        rects.add(new Block(20,23,3,1));


        points.add(new Point(41,9));
        points.add(new Point(46,40));
        points.add(new Point(7,16));
        points.add(new Point(46,27));
        points.add(new Point(32,9));
        points.add(new Point(5,31));
        points.add(new Point(28,33));
        points.add(new Point(48,35));
        points.add(new Point(49,38));
        points.add(new Point(8,23));
        points.add(new Point(49,5));
        points.add(new Point(48,12));
        points.add(new Point(25,46));
        points.add(new Point(41,8));
        points.add(new Point( 8,42));
        points.add(new Point(22,27));
        points.add(new Point(46,50));
        points.add(new Point(40,4));
        points.add(new Point(48,23));
        points.add(new Point(33,6));
        points.add(new Point(2,49));
        points.add(new Point(43,1));
        points.add(new Point(47,39));
        points.add(new Point(34,41));
        points.add(new Point(38,44));
        points.add(new Point(38,5));
        points.add(new Point(20,20));
        points.add(new Point(33,13));
        points.add(new Point(9,41));
        points.add(new Point(36,22));
        points.add(new Point(2,46));
        points.add(new Point(14,10));
        points.add(new Point(3,14));
        points.add(new Point(5,8));
        points.add(new Point(42,7));
        points.add(new Point(35,44));
        points.add(new Point(16,29));
        points.add(new Point( 48,28));
        points.add(new Point(2,8));
        points.add(new Point(22,43));
        points.add(new Point(20,32));
        points.add(new Point(39,18));
        points.add(new Point(40,26));
        points.add(new Point(10,21));
        points.add(new Point(25,4));
        points.add(new Point( 23,12));
        points.add(new Point(33,7));
        points.add(new Point(36,10));
        points.add(new Point(14,21));
        points.add(new Point(34,3));

    }

    public ArrayList<Block> getRects() {
        return rects;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }
}
