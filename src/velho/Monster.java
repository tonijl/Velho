
package velho;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;


public class Monster {

    double x, y, velx, vely;
    double maxspeed;
    double speed;
    double life;
    double pLeveys = 75;
    double pKorkeus = 75;
    int laskuri=0;
    boolean stop=false;
    double firerate;
    int combo=0;
    String kuva1,kuva2;
    int keskitysX, keskitysY;
    Color bulletColor;

    double shoot=100;
    int movetype;
    int type;
    
    int burnDuratio;
    double burnDamage;
    int iceDuratio;
    double iceLevel;

    public String getKuva1() {
        return kuva1;
    }

    public void setKuva1(String kuva1) {
        this.kuva1 = kuva1;
    }

    public String getKuva2() {
        return kuva2;
    }

    public void setKuva2(String kuva2) {
        this.kuva2 = kuva2;
    }
    
    

    public int getMovetype() {
        return movetype;
    }

    public void setMovetype(int movetype) {
        this.movetype = movetype;
    }

    public int getCombo() {
        return combo;
    }

    public void setCombo(int combo) {
        this.combo = combo;
    }
    
    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public double getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
   
    public void takeDamage(double damage){
        life-=damage;
    }
    
    public Monster(){
 
    }

    public Monster(double x, double y,double velx, double vely, double maxspeed,
            double life, double firerate, String kuva1, String kuva2,int keskitysX, int keskitysY, int movetype, int type, Color bulletColor) {
        this.x = x;
        this.y = y;
        this.velx = velx;
        this.vely = vely;
        this.maxspeed = maxspeed;
        this.life = life;
        this.firerate = firerate;
        this.kuva1 = kuva1;
        this.kuva2 = kuva2;
        this.keskitysX = keskitysX;
        this.keskitysY = keskitysY;
        this.movetype = movetype;
        this.type = type;
        this.bulletColor = bulletColor;
    }

    public Color getBulletColor() {
        return bulletColor;
    }

    public void setBulletColor(Color bulletColor) {
        this.bulletColor = bulletColor;
    }

    public int getKeskitysX() {
        return keskitysX;
    }

    public void setKeskitysX(int keskitysX) {
        this.keskitysX = keskitysX;
    }

    public int getKeskitysY() {
        return keskitysY;
    }

    public void setKeskitysY(int keskitysY) {
        this.keskitysY = keskitysY;
    }

    public double getMaxspeed() {
        return maxspeed;
    }

    public void setMaxspeed(double maxspeed) {
        this.maxspeed = maxspeed;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getShoot() {
        return shoot;
    }

    public void setShoot(double shoot) {
        this.shoot = shoot;
    }

    public double getFirerate() {
        return firerate;
    }

    public void setFirerate(double firerate) {
        this.firerate = firerate;
    }


    public void draw(Graphics g){
        

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.RED);
        g2.fill(new Ellipse2D.Double(x, y, pLeveys, pKorkeus));
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
    
    public void push(double px, double py){
        //System.out.println(px+" "+py);
        x = x + (px/5);
        y = y + (py/5);
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

    public int getBurnDuratio() {
        return burnDuratio;
    }

    public void setBurnDuratio(int burnDuratio) {
        this.burnDuratio = burnDuratio;
    }

    public double getBurnDamage() {
        return burnDamage;
    }

    public void setBurnDamage(double burnDamage) {
        this.burnDamage = burnDamage;
    }

    public int getIceDuratio() {
        return iceDuratio;
    }

    public void setIceDuratio(int iceDuratio) {
        this.iceDuratio = iceDuratio;
    }

    public double getIceLevel() {
        return iceLevel;
    }

    public void setIceLevel(double iceLevel) {
        this.iceLevel = iceLevel;
    }
    
    public void liiku(double ruutuK, double ruutuL, double mx, double my){
               
     x+=velx*speed;
     y+=vely*speed;
     shoot--;
     
     if(x+pLeveys>ruutuL||x<0){
         velx=velx*(-1);
         
     }
     
     if(y>ruutuK||y<0){
         vely=vely*(-1);
     }
     
     if(burnDuratio>0){
         if(burnDuratio%50==0){
             life=life-burnDamage;
         }
         burnDuratio--;
     }
     
     if(iceDuratio>0){
         speed=maxspeed*(1-(iceLevel/13.33));
         iceDuratio--;
     }else{
         speed=maxspeed;
     }
     
     if(movetype==3){
        double apux = 0, apuy = 0;
        double jakox, jakoy;
        double xero, yero;
        
        xero = (mx - (x+(pLeveys/2)));
        yero = (my - (y+(pKorkeus/2)));

        if (xero < 0) {
            apux = xero * (-1);
        } else {
            apux = xero;
        }
        if (yero < 0) {
            apuy = yero * (-1);
        } else {
            apuy = yero;
        }
        jakox = (apux / (apuy + apux));
        jakoy = (apuy / (apuy + apux));
        if (xero < 0) {
            jakox = jakox * (-1);
        }
        if (yero < 0) {
            jakoy = jakoy * (-1);
        }
        velx = jakox;
        vely = jakoy;
     }
     
     if(movetype==1||movetype==2){
         if(y+pKorkeus>200){
             y-=vely*2;
         }
     }

     if(x+pLeveys>ruutuL){
      x=(ruutuL-pLeveys)-1;   
     }
     if(x<0){
         x=1;
     }
     
     if(y<0){
        y=0; 
     }
        }
    
    public void reset(){
    
    
    }
  
}
