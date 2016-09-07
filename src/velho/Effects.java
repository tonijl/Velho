package velho;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

public class Effects {

    double x, y, x2, y2;
    int type; // 1= ball, 2=line
    double Leveys;
    double Korkeus;
    double duration = 10;

    public Effects(double x, double y, int type, double Leveys, double Korkeus) {
        // konstruktori pallolle
        this.x = x;
        this.y = y;
        this.type = type;
        this.Leveys = Leveys;
        this.Korkeus = Korkeus;
    }

    public Effects(double x, double y, double x2, double y2, int type) {
        //kontruktori viivalle
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
        this.type = type;
        this.Leveys = Leveys;
        this.Korkeus = Korkeus;
    }

    public Effects() {
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }
    
    public void reduceDuration(){
        duration--;
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;


        g2.setColor(Color.YELLOW);
        if(type==1){
        g2.drawOval((int) x, (int) y, (int) Leveys, (int) Korkeus);
        }else{
            g2.setStroke(new BasicStroke(10));
            g2.drawLine((int)x, (int)y, (int)x2, (int)y2);
        }

        //g2.fill(new Rectancle.Double(x, y, pLeveys, pKorkeus));
    }
}
