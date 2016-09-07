
package velho;

public class Upgrade {
    
    String name;
    String icon;
    String description;
    int type; // 1=damage, 2=firerate, 3=bulletsize, 4=fire, 5=ice, 6=lightning 7=wind
    int points=0;
    boolean unlocked;

    public Upgrade(String name, String icon, String description, int type, int points, boolean unlocked) {
        this.name = name;
        this.icon = icon;
        this.description = description;
        this.type = type;
        this.points = points;
        this.unlocked = unlocked;
    }

    public Upgrade() {
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
    }
    
  
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    

    
}
