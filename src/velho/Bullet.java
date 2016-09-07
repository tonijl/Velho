
package velho;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;


public class Bullet {
    
    double x, y, velx, vely;
    double pLeveys;
    double pKorkeus;
    double damage;
    int laskuri=0;
    double duration = 100;
    boolean stop=false;
    boolean delete = false;

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }
    
    
    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }
   
    
    public Bullet(){
 
    }

    public Bullet(double x, double y, double velx, double vely, double pLeveys, double pKorkeus, double damage, int duration) {
        this.x = x;
        this.y = y;
        this.velx = velx;
        this.vely = vely;
        this.pLeveys = pLeveys;
        this.pKorkeus = pKorkeus;
        this.damage = damage;
        this.duration = duration;

    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }



    
    
    
    public void draw(Graphics g){
        
        Graphics2D g2 = (Graphics2D) g;
        
        
        g2.setColor(Color.YELLOW);
        
        g2.fillOval((int)x, (int)y, (int)pLeveys, (int)pKorkeus);
        
        //g2.fill(new Rectancle.Double(x, y, pLeveys, pKorkeus));
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
     duration--;
     
     /*if(x>ruutuL||x<0){
         velx=velx*(-1);
         
     }
     
     if(y>ruutuK||y<0){
         vely=vely*(-1);
     }*/
        

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

