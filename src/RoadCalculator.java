import java.awt.*;
import java.util.ArrayList;

public class RoadCalculator {
    private ArrayList<Block> rectangeles;
    private ArrayList<Point> points;

    public RoadCalculator(){
        RectangelPointsCreater generator = new RectangelPointsCreater();
        rectangeles = new ArrayList<Block>(generator.getRects());
        points = new ArrayList<Point>(generator.getPoints());
    }
    public int manhattanDistance(Point a, Point b){
        return (int) (Math.abs(a.getX()-b.getX()) + Math.abs(a.getY()-b.getY()));
    }

    public boolean isThereABlock(Point a, Point b){
        Point x = new Point();
        Point y = new Point();
        if(a.getX() <= b.getX()) {
            if (a.getY() <= b.getY()) {
                x.setLocation(a.getX(), a.getY());
                y.setLocation(b.getX(),b.getY());
            } else {
                x.setLocation(a.getX(), b.getY());
                y.setLocation(b.getX(),a.getY());
            }
        }
        else if (a.getX()>=b.getX()){
            if(a.getY()>=b.getY()){
                y.setLocation(a.getX(), a.getY());
                x.setLocation(b.getX(),b.getY());
            }else {
                y.setLocation(a.getX(), b.getY());
                x.setLocation(b.getX(),a.getY());
            }
        }

       // for (int i = 0; )
    }
}
