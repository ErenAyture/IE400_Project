import org.w3c.dom.css.Rect;

import java.awt.*;

public class Block {
    private Rectangle rect;

    Block(int x, int y, int width, int height){
        rect = new Rectangle();
        rect.setBounds(x+width, y+height, width, height);
    }

    public Rectangle getBlock() {
        return rect;
    }
}
