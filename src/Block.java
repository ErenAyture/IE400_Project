import org.w3c.dom.css.Rect;

import java.awt.*;

public class Block {
    private Rectangle rect;
    private int x, y, width, height;
    Block(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.height =height;
        this.width = width;
        rect = new Rectangle();
        rect.setBounds(x+width, y+height, width, height);

    }

    public boolean Contains(Point x){
        return rect.contains(x);
    }
    public Rectangle getBlock() {
        return rect;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return  y;
    }

    public int getWidth() {
        return  width;
    }

    public int getHeight() {
        return  height;
    }
}
