
package flappybirds;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Play {

    private BufferedImage playgame,logo;

    public Play() {
        try {
            playgame = ImageIO.read(new File("Assets/play.png"));
            logo = ImageIO.read(new File("Assets/logo-remove.png"));
        } catch (IOException ex) {
        }
    }

    public void Paint4(Graphics2D g2) {
        g2.drawImage(playgame, 200, 300, null);
        g2.drawImage(logo, 210, 100, null);
    }
}