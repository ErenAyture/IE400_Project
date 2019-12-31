import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RectangelPointsCreater {

    private ArrayList<Block> rects;
    private ArrayList<Point> points;
    private int grid[][];
    Point biggestPo;
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


        this.biggestPo = new Point(biggestPoint());
        grid = new int[biggestPo.x][biggestPo.y];
        fill_grid();
        for(int r=0; r<grid.length; r++) {
            for(int c=0; c<grid[r].length; c++)
                System.out.print(grid[r][c] + " ");
            System.out.println();
        }
    }

    public ArrayList<Block> getRects() {
        return rects;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }
    public Point biggestPoint(){
        int x = 0;
        int y = 0;

        for (int i = 0; i < points.size(); i++ ){
            if(x < points.get(i).x){
                x = points.get(i).x;
            }
            if(y < points.get(i).y){
                y = points.get(i).y;
            }
        }
        for (int i = 0; i < rects.size(); i++ ){
            if(x < rects.get(i).getX()+rects.get(i).getWidth()){
                x = rects.get(i).getX()+rects.get(i).getWidth();
            }
            if(y < rects.get(i).getY()+rects.get(i).getHeight()){
                y = rects.get(i).getY()+rects.get(i).getHeight();
            }
        }

        return new Point(x,y);
    }
    public void fill_grid(){
        for (int i = 0; i < biggestPo.x; i++ ){
            for (int j = 0; j < biggestPo.y; j++ ){
                grid[i][j] = 0;
            }
        }

        for (int i = 0; i < rects.size(); i++ ){
            for (int j = rects.get(i).getX(); j < rects.get(i).getX()+rects.get(i).getWidth(); j++ ){
                for (int k = rects.get(i).getY(); k < rects.get(i).getY()+rects.get(i).getHeight(); k++ ){
                    grid[j][k] = 1;
                }
            }
        }
    }

    public int[][] make_destination_grid(Point a, Point b){
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


        int[][] smallGrid = new int[x2-x1+1][y2-y1+1];




        for (int i = x1, k= 0; i < x2; i++,k++){

            for (int j = y1, l= 0; j <y2; j++,l++){

                    smallGrid[k][l] = grid[i][j];

            }
        }
        return smallGrid;
    }
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int R = obstacleGrid.length;
        int C = obstacleGrid[0].length;

        // If the starting cell has an obstacle, then simply return as there would be
        // no paths to the destination.
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }

        // Number of ways of reaching the starting cell = 1.
        obstacleGrid[0][0] = 1;

        // Filling the values for the first column
        for (int i = 1; i < R; i++) {
            obstacleGrid[i][0] = (obstacleGrid[i][0] == 0 && obstacleGrid[i - 1][0] == 1) ? 1 : 0;
        }

        // Filling the values for the first row
        for (int i = 1; i < C; i++) {
            obstacleGrid[0][i] = (obstacleGrid[0][i] == 0 && obstacleGrid[0][i - 1] == 1) ? 1 : 0;
        }

        // Starting from cell(1,1) fill up the values
        // No. of ways of reaching cell[i][j] = cell[i - 1][j] + cell[i][j - 1]
        // i.e. From above and left.
        for (int i = 1; i < R; i++) {
            for (int j = 1; j < C; j++) {
                if (obstacleGrid[i][j] == 0) {
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
                } else {
                    obstacleGrid[i][j] = 0;
                }
            }
        }

        // Return value stored in rightmost bottommost cell. That is the destination.
        return obstacleGrid[R - 1][C - 1];
    }

    int calculate_number_of_ways(Point a, Point b){
        return uniquePathsWithObstacles(make_destination_grid(a,b));
    }
}
