/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Sérgio
 */
public class Projectile {

    private int x, y, size;
    private BufferedImage image;
    private double dirX,dirY;

    public Projectile() {
    }
    

    public Projectile(int x, int y, int size, String imagePath,double dirX,double dirY) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.dirX=dirX;
        this.dirY=dirY;

        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void move(double dirX, double dirY) {
        x += dirX;
        y += dirY;
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, size, size, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x + 5, y + 5, size - 10, size - 10);
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

    public double getDirX() {
        return dirX;
    }

    public void setDirX(double dirX) {
        this.dirX = dirX;
    }

    public double getDirY() {
        return dirY;
    }

    public void setDirY(double dirY) {
        this.dirY = dirY;
    } 
}
