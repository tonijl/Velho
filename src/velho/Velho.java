package velho;


import java.awt.Cursor;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

public class Velho {
    public static void main(String args[]){
        JFrame f = new JFrame();
f.setCursor(f.getToolkit().createCustomCursor(
            new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0),
            "null"));
        Ohjaus s = new Ohjaus();
        f.add(s);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(600,900);
                // Transparent 16 x 16 pixel cursor image.

    }
}
