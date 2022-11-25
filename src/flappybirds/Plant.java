
package flappybirds;

import java.awt.Rectangle;
import pkg2dgamesframework.Objects;

/**
 *
 * @author DUONG THANH
 */
public class Plant extends Objects {
    private Rectangle rect;
    private float vt = 0.3f;
    private boolean grow = false;
    private boolean stop = false;

    public Plant(int x, int y, int w, int h) {
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
        setPosY(getPosY() - 5 * vt);

        rect.setLocation((int) this.getPosX(), (int) this.getPosY());
    }
    public void update3(){
        setPosY(getPosY()+5*vt);
        
        rect.setLocation((int) this.getPosX(), (int) this.getPosY());
    }
      
    public void stop() {
        setPosY(getPosY());
        rect.setLocation((int) this.getPosX(), (int) this.getPosY());
    }

    public void setGrow(boolean b) {
        grow = b;
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
