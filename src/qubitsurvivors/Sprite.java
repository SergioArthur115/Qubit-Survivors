/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qubitsurvivors;

/**
 *
 * @author 182120042
 */
import java.awt.Graphics;
import java.awt.Image;

public class Sprite {
    
    private Image image;
    private int x;
    private int y;
    
    public Sprite(Image image, int x, int y) {
        this.image = image;
        this.x = x;
        this.y = y;
    }
    
    public void draw(Graphics g) {
        g.drawImage(image, x, y, null);
    }
    
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    
    // propriedades da classe
    
    public boolean collidesWith(Sprite other) {
        int x1 = x;
        int y1 = y;
        int w1 = image.getWidth(null);
        int h1 = image.getHeight(null);
        int x2 = other.x;
        int y2 = other.y;
        int w2 = other.image.getWidth(null);
        int h2 = other.image.getHeight(null);
        return (x1 < x2 + w2 &&
                x1 + w1 > x2 &&
                y1 < y2 + h2 &&
                y1 + h1 > y2);
    }
}


