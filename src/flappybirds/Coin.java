
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
    private boolean grow = false;
    private boolean stop = false;

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    private boolean disabled = false;

    public Coin(int x, int y, int w, int h) {
        super(x, y, w, h);
        rect = new Rectangle(x, y, w, h);
    }

    public void setVt(float vt) {
        this.vt = vt;
    }

    public void update() {
        // float dis = getPosY();
        setPosX(getPosX() - 2);
        // setPosY(getPosY()+4*vt);

        rect.setLocation((int) this.getPosX(), (int) this.getPosY());
    }

    public void update2() {
        // setPosX(getPosX()-2);
//        setPosY(getPosY() - 5 * vt);

        rect.setLocation((int) this.getPosX(), (int) this.getPosY());
    }

    public void stop() {
        setPosY(getPosY());
        rect.setLocation((int) this.getPosX(), (int) this.getPosY());
    }

    public void setGrow(boolean b) {
        grow = b;
    }

    public int settLocY(int y) {
        return y;
    }

    public void setStop(boolean b) {
        stop = b;
    }

    public boolean getStop() {
        return stop;
    }

    public boolean getGrow() {
        return grow;
    }

    public Rectangle getRect() {
        return rect;
    }
}
