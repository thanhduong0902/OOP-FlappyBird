
package flappybirds;

import java.awt.Rectangle;
import pkg2dgamesframework.Objects;

public class Chimney extends Objects{
    
    private Rectangle rect;
    private float vt = 0.5f;
    private boolean isBehindBird = false;
    private boolean move = false;
    
    public Chimney(int x, int y, int w, int h){
        super(x, y, w, h);
        rect = new Rectangle(x, y, w, h);
    }
    public void update(){
        setPosX(getPosX()-2);
        
        rect.setLocation((int) this.getPosX(), (int) this.getPosY());
    }
    
    public void update2(){
        setPosY(getPosY()+4*vt);
        
        rect.setLocation((int) this.getPosX(), (int) this.getPosY());
    }
    public void update3(){
        setPosY(getPosY()-6*vt);
        
        rect.setLocation((int) this.getPosX(), (int) this.getPosY());
    }
    
    public void setMove(boolean b ){
        move = b;
    }
    
    public boolean getMove(){
        return move;
    }
    
    public Rectangle getRect(){
        return rect;
    }
    public void setIsBehindBird(boolean b){
        isBehindBird = b;
    }
    public boolean getIsBehindBird(){
        return isBehindBird;
    }
}