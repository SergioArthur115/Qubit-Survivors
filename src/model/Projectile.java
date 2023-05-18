/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Sérgio
 */
public class Projectile {

    private int x;
    private int y;
    private int speed;
    private double direction;
    private Image image;

    public Projectile(int x, int y, int size, double direction, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.direction = direction;

        // Calcula a direção do projétil com base na posição atual do personagem e na posição do mouse
        x = (int) (Math.cos(direction) * speed);
        y = (int) (Math.sin(direction) * speed);

        try {
            image = ImageIO.read(new File("images/projectile.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void move() {
        int dx = (int) (speed * Math.cos(direction));
        int dy = (int) (speed * Math.sin(direction));
        x += dx;
        y += dy;
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
