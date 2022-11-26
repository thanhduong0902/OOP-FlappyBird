
package flappybirds;

import java.awt.Rectangle;
import pkg2dgamesframework.Objects;

/**
 *
 * @author DUONG THANH
 */
public class Coin extends Objects {
    private Rectangle rect;
    private float vt = 0.3f;
    private boolean disabled = false;

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    

    public Coin(int x, int y, int w, int h) {
        super(x, y, w, h);
        rect = new Rectangle(x, y, w, h);
    }



    public void update() {

        setPosX(getPosX() - 2);


        rect.setLocation((int) this.getPosX(), (int) this.getPosY());
    }


    public Rectangle getRect() {
        return rect;
    }
}
