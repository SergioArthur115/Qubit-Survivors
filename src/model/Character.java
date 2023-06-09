/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.Timer;

public class Character {

    private int x, y, size;
    private BufferedImage image;
    private Timer timer;

    public Character(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;

        try {
            image = ImageIO.read(new File("images/charac.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, size, size, null);

    }

    public Rectangle getBounds() {
        return new Rectangle(x + 10, y + 10, size - 20, size - 20);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
