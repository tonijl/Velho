
package velho;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;


public class Magic{
    int fireRate;
    double damage;
    double size;
    double speed;
    int level;
    int type; // 1=normal, 2=shotgun, 3=beam, 4 = spray
    int fire;
    int ice;
    int lightning;
    int wind;
    String icon;
    String name;
    String description;
    boolean selected=false;

    public Magic(int fireRate, double damage, double size, double speed,
            int level, int type, int fire, int ice, int lightning, int wind,
            String icon, String name,String description) {
        this.fireRate = fireRate;
        this.damage = damage;
        this.size = size;
        this.speed = speed;
        this.level = level;
        this.type = type;
        this.fire = fire;
        this.ice = ice;
        this.lightning = lightning;
        this.wind = wind;
        this.icon = icon;
        this.name = name;
        this.description = description;
    }

    public Magic() {
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
  
    public int getFireRate() {
        return fireRate;
    }

    public void setFireRate(int fireRate) {
        this.fireRate = fireRate;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getFire() {
        return fire;
    }

    public void setFire(int fire) {
        this.fire = fire;
    }

    public int getIce() {
        return ice;
    }

    public void setIce(int ice) {
        this.ice = ice;
    }

    public int getLightning() {
        return lightning;
    }

    public void setLightning(int lightning) {
        this.lightning = lightning;
    }

    public int getWind() {
        return wind;
    }

    public void setWind(int wind) {
        this.wind = wind;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
    
}

   