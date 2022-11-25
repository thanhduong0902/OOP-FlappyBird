
package flappybirds;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackGround {

    private BufferedImage background,background2;
    private boolean change = false;
    public void setChange(boolean a){
        change = a;
    }
    public boolean getChange(){
        return change;
    }
    public BackGround() {
        try {
            background = ImageIO.read(new File("Assets/background.png"));
            background2 = ImageIO.read(new File("Assets/background2.jpg"));
        } catch (IOException ex) {
        }
    }

    public void Paint2(Graphics2D g2) {
        if(!getChange())
        g2.drawImage(background, 0, 0, null);
        else g2.drawImage(background2, 0, 0, null);
    }
}