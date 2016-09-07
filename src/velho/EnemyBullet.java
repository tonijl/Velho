
package velho;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;


public class EnemyBullet {
    
    
    
    double x=100, y=0, velx=1, vely=1;
    double pLeveys = 40;
    double pKorkeus = 40;
    double damage;
    int laskuri=0;
    int bulletType;
    Color bulletColor;
    boolean stop=false;
    double duration = 500;
    
    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }
   
    
    public EnemyBullet(){
 
    }
  
     public EnemyBullet(double x, double y, double velx, double vely, double duration,
            double pLeveys, double pKorkeus, double damage, int bulletType, Color bulletColor) {
        this.x=x;
        this.y=y;
        this.velx = velx;
        this.vely = vely;
        this.duration = duration;
        this.pLeveys = pLeveys;
        this.pKorkeus = pKorkeus;
        this.damage = damage;
        this.bulletType = bulletType;
        this.bulletColor = bulletColor;
    }

    public int getBulletType() {
        return bulletType;
    }

    public void setBulletType(int bulletType) {
        this.bulletType = bulletType;
    }
     
    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }


    public void draw(Graphics g){
        
        Graphics2D g2 = (Graphics2D) g;
        
        if(bulletType!=2){
            System.out.println(bulletColor);
         g2.setColor(bulletColor);
         g2.fillOval((int)x, (int)y, (int)pLeveys, (int)pKorkeus);
        }

        /*if(bulletType==1){
        g2.setColor(Color.GREEN);
         g2.fillOval((int)x, (int)y, (int)pLeveys, (int)pKorkeus);
        }else if(bulletType==3){
            g2.setColor(Color.ORANGE);
            g2.fillOval((int)x, (int)y, (int)pLeveys, (int)pKorkeus);
        }else if(bulletType==4){
            g2.setColor(Color.CYAN);
            g2.fillOval((int)x, (int)y, (int)pLeveys, (int)pKorkeus);
        }else if(bulletType==5){
            g2.setColor(Color.RED);
            g2.fillOval((int)x, (int)y, (int)pLeveys, (int)pKorkeus);
        }else if (bulletType==6||bulletType==7){
            g2.setColor(Color.WHITE);
            g2.fillOval((int)x, (int)y, (int)pLeveys, (int)pKorkeus);
        }else if (bulletType==6||bulletType==7){
            g2.setColor(Color.WHITE);
            g2.fillOval((int)x, (int)y, (int)pLeveys, (int)pKorkeus);
        }else if (bulletType==8){
            g2.setColor(Color.YELLOW);
            g2.fillOval((int)x, (int)y, (int)pLeveys, (int)pKorkeus);
        }else if(bulletType==9){
            g2.setColor(Color.BLUE);
            g2.fillOval((int)x, (int)y, (int)pLeveys, (int)pKorkeus);
        }*/
        
        //g2.fill(new Rectangle.Double(x, y, pLeveys, pKorkeus));
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }
    
    

    
    public int getLaskuri(){
        return laskuri;
    }
    

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getVelx() {
        return velx;
    }

    public void setVelx(double velx) {
        this.velx = velx;
    }

    public double getVely() {
        return vely;
    }

    public void setVely(double vely) {
        this.vely = vely;
    }

    public double getpLeveys() {
        return pLeveys;
    }

    public void setpLeveys(double pLeveys) {
        this.pLeveys = pLeveys;
    }

    public double getpKorkeus() {
        return pKorkeus;
    }

    public void setpKorkeus(double pKorkeus) {
        this.pKorkeus = pKorkeus;
    }
    
    public void liiku(double ruutuK, double ruutuL){
        
     x+=velx;
     y+=vely;
     
     
     
    /* if(x>ruutuL||x<0){
         velx=velx*(-1);
         
     }
     
     if(y>ruutuK||y<0){
         vely=vely*(-1);
     }*/
        
      
      duration--;  
        
       
        }
    
   /* public void tormays(double a, double b){
        
             if(y+pKorkeus<b){
       y+=vely;
        }else{
           x+=velx; 
        }
             
             if(x+pLeveys>a||x<a+pLeveys){
            velx=velx*(-1);
        }
        
        
    }*/
        

    public void reset(){
    
    
    }
    
    
    
    
    
    
    
}

