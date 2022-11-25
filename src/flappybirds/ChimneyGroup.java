/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package flappybirds;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import pkg2dgamesframework.QueueList;

/**
 *
 * @author DUONG THANH
 */
public class ChimneyGroup {
    
    private QueueList<Chimney> chimneys;
    private QueueList<Plant>plants;
    private QueueList<Coin> coins;
    private BufferedImage chimneyImage,chimneyImage2,plantImage,plantImage2, coin;
    public static int SIZE = 6;
    
    public Chimney getChimney(int i){
        return chimneys.get(i);
    }
    public Plant getPlant(int i){
        return plants.get(i);
    }
    public Coin getCoin (int i){
        return coins.get(i);
    }
    
    public int distance = 300;
    private int topChimneyY = -350;
    private int bottomChimneyY = 250;
    
    
    public int getRandomY(){
        Random random = new Random();
        int a;
        a = random.nextInt(10)+1;
        
        return a*20;
    }
    public int getRandomX(){
        Random random = new Random();
        int z;
        z = random.nextInt(6);
        
        return z;
    }
    public int getRandomZ(){
        Random random = new Random();
        int z;
        z = random.nextInt(6);
        
        return z;
    }
    public ChimneyGroup(){
        
        try{
            chimneyImage = ImageIO.read(new File("Assets/chimney.png"));
            chimneyImage2 = ImageIO.read(new File("Assets/chimney2.png"));
            plantImage = ImageIO.read(new File("Assets/plant.png"));
            plantImage2 = ImageIO.read(new File("Assets/plant_.png"));
            coin = ImageIO.read(new File("Assets/coin.png"));

        } catch (IOException ex){}
        chimneys = new QueueList<Chimney>();
        Chimney cn;
        
        plants = new QueueList<Plant>();
        Plant pl;
        
        coins = new QueueList<Coin>();
        Coin ci;
        
        for (int i=0; i<SIZE/2; i++) {
            int deltaY = getRandomY();
            cn = new Chimney(830+i*distance,bottomChimneyY +deltaY,74,400);
            chimneys.push(cn);
            pl = new Plant(830+i*distance-10,bottomChimneyY+deltaY,74,100);
            plants.push(pl);
            ci = new Coin(830+i*distance,bottomChimneyY+deltaY-100,74,100);
            ci.setDisabled(Math.random() > 0.5);
            coins.push(ci);
            
            cn = new Chimney(830+i*distance,topChimneyY +deltaY,74,400);
            chimneys.push(cn);
            pl = new Plant(830+i*distance-10, topChimneyY+deltaY+250, 74, 100);
            plants.push(pl);
            ci = new Coin(830+i*distance,bottomChimneyY+deltaY-100,74,100);
            ci.setDisabled(Math.random() > 0.5);
            coins.push(ci);
        }
        
    }
    public void resetChimneys(){
         chimneys = new QueueList<Chimney>();
        Chimney cn;
        
        plants = new QueueList<Plant>();
        Plant pl;
        
        coins = new QueueList<Coin>();
        Coin ci;
        for (int i=0;i<SIZE/2;i++){
            int deltaY = getRandomY();
            cn = new Chimney(830+i*distance,bottomChimneyY +deltaY,74,400);
            chimneys.push(cn);
            pl = new Plant(830+i*distance-10,bottomChimneyY+deltaY,74,100);
            plants.push(pl);
            ci = new Coin(830+i*distance,bottomChimneyY+deltaY-100,74,100);
            ci.setDisabled(Math.random() > 0.5);
            coins.push(ci);
            
            cn = new Chimney(830+i*distance,topChimneyY +deltaY,74,400);
            chimneys.push(cn);
            pl = new Plant(830+i*distance-10, topChimneyY+deltaY+250, 74, 100);
            plants.push(pl);
            ci = new Coin(830+i*distance,topChimneyY+deltaY+30,74,100);
            ci.setDisabled(Math.random() > 0.5);
            coins.push(ci);
        }
    }
    
    public void update(){
        for(int i=0;i<SIZE;i++){

            chimneys.get(i).update();
            plants.get(i).update();
            coins.get(i).update();
            if (plants.get(i).getStop()){
                if(i%2==0) plants.get(i).update3();
                else plants.get(i).update2();
                int z = getRandomX();
                if(z%2==0) chimneys.get(i).update3();
                else chimneys.get(i).update2();
            }
            if((int)plants.get(i).getPosY()+100==(int)chimneys.get(i).getPosY()) plants.get(i).setGrow(false);
            if(i%2==0 && plants.get(i).getGrow())
                plants.get(i).update2();
            if(i%2!=0 && plants.get(i).getGrow())
                plants.get(i).update3();
        }
        
        
            if(chimneys.get(0).getPosX()<-74){
            int deltaY = getRandomY();
            
            Chimney cn;
            cn = chimneys.pop();
            cn.setPosX(chimneys.get(4).getPosX() + distance);
            cn.setPosY(bottomChimneyY + deltaY);
            cn.setIsBehindBird(false);
            chimneys.push(cn);
            
            Plant pl;
            pl = plants.pop();
            pl.setPosX(chimneys.get(4).getPosX()+distance-10);
            pl.setPosY(bottomChimneyY+deltaY);
            pl.setGrow(false);
            pl.setStop(false);
            plants.push(pl);
            
            Coin ci;
            ci = coins.pop();
            ci.setPosX(chimneys.get(4).getPosX()+distance);
            ci.setPosY(bottomChimneyY+deltaY-130);
            ci.setDisabled(Math.random() > 0.5);
            coins.push(ci);
            
            cn = chimneys.pop();
            cn.setPosX(chimneys.get(4).getPosX());
            cn.setPosY(topChimneyY + deltaY);
            cn.setIsBehindBird(false);
            chimneys.push(cn);
            
            
            pl = plants.pop();
            pl.setPosX(plants.get(4).getPosX());
            pl.setPosY(topChimneyY+deltaY+250);
            pl.setGrow(false);
            pl.setStop(false);
            plants.push(pl);
             
            ci = coins.pop();
            ci.setPosX(chimneys.get(4).getPosX());
            ci.setPosY(bottomChimneyY+deltaY-130);
            ci.setDisabled(Math.random() > 0.5);
            coins.push(ci);
        }
    }
    
    public void paint (Graphics2D g2){
//         g2.drawImage(plantImage, (int)plants.get(i).getPosX(), (int)plants.get(i).getPosY(),null);
        for(int i=0;i<SIZE;i++){
            if(i%2==0){
                g2.drawImage(plantImage, (int)plants.get(i).getPosX(), (int)plants.get(i).getPosY(),null);
                if (!coins.get(i).isDisabled()) {
                    g2.drawImage(coin, (int)coins.get(i).getPosX(), (int)coins.get(i).getPosY(),null);
                }
                g2.drawImage(chimneyImage, (int )chimneys.get(i).getPosX(), (int)chimneys.get(i).getPosY(), null);
            }
            else{
                g2.drawImage(plantImage2, (int)plants.get(i).getPosX(), (int)plants.get(i).getPosY(),null);
//                  g2.drawImage(coin, (int)coins.get(i).getPosX(), (int)coins.get(i).getPosY(),null);
                g2.drawImage(chimneyImage2, (int )chimneys.get(i).getPosX(), (int)chimneys.get(i).getPosY(), null);
            }
        }
    }

    Object getCoin() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
