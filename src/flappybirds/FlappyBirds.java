
package flappybirds;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import pkg2dgamesframework.AFrameOnImage;
import pkg2dgamesframework.Animation;
import pkg2dgamesframework.GameScreen;


public class FlappyBirds extends GameScreen{

    private BufferedImage birds;
    private Animation bird_anim;
    
    
    public static float g = 0.1f;
    
    private Bird bird;
    private Ground ground;
    private ChimneyGroup chimneyGroup;
    private BackGround background;
    private GameOver gameover;
    private Play playgame;
    private int Point = 0;
    
    private int BEGIN_SCREEN = 0;
    private int GAMEPLAY_SCREEN = 1;
    private int GAMEOVER_SCREEN = 2;
    
    private int CurrentScreen = BEGIN_SCREEN;
    
    public FlappyBirds(){
        super(800,600);
        
        try {
            birds = ImageIO.read(new File("Assets/bird_sprite.png"));
        } catch (IOException ex) {}
        
        bird_anim = new Animation(70);
        AFrameOnImage f;
        f = new AFrameOnImage(0,0,60,60);
        bird_anim.AddFrame(f);
        f = new AFrameOnImage(60,0,60,60);
        bird_anim.AddFrame(f);
        f = new AFrameOnImage(120,0,60,60);
        bird_anim.AddFrame(f);
        f = new AFrameOnImage(60,0,60,60);
        bird_anim.AddFrame(f);
        
        bird = new Bird(350, 250, 50, 50);
        ground = new Ground();
        background=new BackGround();
        gameover=new GameOver();
        chimneyGroup = new ChimneyGroup();
        playgame=new Play();
        BeginGame();
    }
    
    public static void main(String[] args) {
        new FlappyBirds();
    }
    
    private void resetGame(){
        bird.setPos(350, 250);
        bird.setVt(0);
        bird.setLive(true);
        Point = 0;
//        Bird.velocity = 2;
        chimneyGroup.resetChimneys();
    }
    
    @Override
    public void GAME_UPDATE(long deltaTime) {
        
        if(CurrentScreen == BEGIN_SCREEN){
            resetGame();
        }else if(CurrentScreen == GAMEPLAY_SCREEN){
            
            if(bird.getLive()) bird_anim.Update_Me(deltaTime);
            bird.update(deltaTime);
            ground.Update();
            chimneyGroup.update();
            
            for(int i = 0;i<ChimneyGroup.SIZE;i++){
                if(bird.getRect().intersects(chimneyGroup.getChimney(i).getRect())||bird.getRect().intersects(chimneyGroup.getPlant(i).getRect()) ){
                    if(bird.getLive()) bird.bupSound.play();
                    bird.setLive(false);
                    bird.setVt(20);
                } else if(!chimneyGroup.getCoin(i).isDisabled() && bird.getRect().intersects(chimneyGroup.getCoin(i).getRect())) {
                    Point += 2;
                    bird.getMoneySound.play();
                    chimneyGroup.getCoin(i).setDisabled(true);
                }
            }
//            
            for(int i = 0;i<ChimneyGroup.SIZE;i++){
                if((int)chimneyGroup.getPlant(i).getPosX()-bird.getPosX()==50&&Point>1&&Math.random()>0.5){
                    chimneyGroup.getPlant(i).setGrow(true);
                }
             
                if((int)chimneyGroup.getPlant(i).getPosY()==((int)bird.getPosY()+bird.getH())||(int)chimneyGroup.getPlant(i).getPosX()+
                        (int)chimneyGroup.getPlant(i).getW()<(int)bird.getPosX()) {
                    chimneyGroup.getPlant(i).setGrow(false);
                }
                if(Point>=10&&Point<=20) {
                    chimneyGroup.getPlant(i).setStop(true);
//                    chimneyGroup.getChimney(i).setMove(true);
                }
                
                if(bird.getPosX() > chimneyGroup.getChimney(i).getPosX() && !chimneyGroup.getChimney(i).getIsBehindBird()
                        && i%2== 0){
                    Point ++;
                    bird.getMoneySound.play();
                    chimneyGroup.getChimney(i).setIsBehindBird(true);
                }
                if(Point>=10){
                    background.setChange(true);
                    try{
                        birds = ImageIO.read(new File("Assets/bird_sprite2.png"));
                    }
                    catch(IOException ex){}
                }
            }
            if(bird.getPosY()+bird.getH()>ground.getYGround()) bird.setVt(20);
            if(bird.getPosY() + bird.getH() >800) CurrentScreen = GAMEOVER_SCREEN;
            
            }else{
            
        }
        
        
    }

    @Override
    public void GAME_PAINT(Graphics2D g2) {
        
        background.Paint2(g2);
        chimneyGroup.paint(g2);
        
        ground.Paint(g2);
        
        
        if(bird.getIsFlying())
            bird_anim.PaintAnims((int) bird.getPosX(), (int) bird.getPosY(), birds, g2, 0, -1);
        else 
            bird_anim.PaintAnims((int) bird.getPosX(), (int) bird.getPosY(), birds, g2, 0, 0);
        
        
        
        if(CurrentScreen == BEGIN_SCREEN){
            playgame.Paint4(g2);
        }
        if(CurrentScreen == GAMEOVER_SCREEN){
            gameover.Paint3(g2);
        }
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial",Font.TYPE1_FONT,50));
        g2.drawString(" "+Point, 350, 50);
    }

    @Override
    public void KEY_ACTION(KeyEvent e, int Event) {
        if(Event == KEY_PRESSED){
            
            if(CurrentScreen == BEGIN_SCREEN){
                
                CurrentScreen = GAMEPLAY_SCREEN;
                
            }else if(CurrentScreen == GAMEPLAY_SCREEN){
                if(bird.getLive()) bird.fly();
            }else if(CurrentScreen == GAMEOVER_SCREEN){
                CurrentScreen = BEGIN_SCREEN;
            }
        }
    }
}
