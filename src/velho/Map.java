
package velho;

import java.awt.Color;


public class Map {
    
    int x,y;
    String nimi;
    String description;
    boolean locked;
    boolean cleared;
    boolean selected;
    int[]monsters;
    int[]spawntime;
    Color background;

    public Map(int x, int y, String nimi, String description, boolean locked, boolean cleared,boolean selected,Color background, int[] monsters, int[] spawntime) {
        this.x = x;
        this.y = y;
        this.nimi = nimi;
        this.description = description;
        this.locked = locked;
        this.cleared = cleared;
        this.selected = selected;
        this.background = background;
        this.monsters = monsters;
        this.spawntime = spawntime;
    }

    public Map() {
    }

    public Color getBackground() {
        return background;
    }

    public void setBackground(Color background) {
        this.background = background;
    }
    
    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isCleared() {
        return cleared;
    }

    public void setCleared(boolean cleared) {
        this.cleared = cleared;
    }

    public int[] getMonsters() {
        return monsters;
    }

    public void setMonsters(int[] monsters) {
        this.monsters = monsters;
    }

    public int[] getSpawntime() {
        return spawntime;
    }

    public void setSpawntime(int[] spawntime) {
        this.spawntime = spawntime;
    }
    
    
    
}
