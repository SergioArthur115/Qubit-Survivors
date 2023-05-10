/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author 182120042
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    private Character character;
    private ArrayList<Enemy> enemies;
    private Timer timer;

    public GamePanel() {
        //setPreferredSize(new Dimension(600, 600));
        //setBackground(Color.WHITE);

        character = new Character(250, 250, 50);

        enemies = new ArrayList<>();
        enemies.add(new Enemy(100, 100, 50, "images/enemy.png"));
        enemies.add(new Enemy(400, 400, 50, "images/enemy1.png"));

        timer = new Timer(10, this);
        timer.start();

        addKeyListener(this);
        setFocusable(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        BackgroundQS background = new BackgroundQS();
        //background.paintComponent(g);
        background.draw(g);
        character.draw(g);

        for (Enemy enemy : enemies) {
            enemy.draw(g);

        }
    }

    private boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyPressed(KeyEvent e) {
        int moveAmount = 5;

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            upPressed = true;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            downPressed = true;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftPressed = true;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }

        if (upPressed && leftPressed) {
            character.move(-moveAmount, -moveAmount);
        } else if (upPressed && rightPressed) {
            character.move(moveAmount, -moveAmount);
        } else if (downPressed && leftPressed) {
            character.move(-moveAmount, moveAmount);
        } else if (downPressed && rightPressed) {
            character.move(moveAmount, moveAmount);
        } else if (upPressed) {
            character.move(0, -moveAmount);
        } else if (downPressed) {
            character.move(0, moveAmount);
        } else if (leftPressed) {
            character.move(-moveAmount, 0);
        } else if (rightPressed) {
            character.move(moveAmount, 0);
        }
        checkCollisions();
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            upPressed = false;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            downPressed = false;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftPressed = false;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    private void checkCollisions() {
        Rectangle characterBounds = character.getBounds();

        for (Enemy enemy : enemies) {
            Rectangle enemyBounds = enemy.getBounds();

            if (characterBounds.intersects(enemyBounds)) {
                System.out.println("Game Over!");
                timer.stop();
            }
        }
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Move o personagem
        //character.move(WIDTH, WIDTH);

        // Move os inimigos
        for (Enemy enemy : enemies) {
            //enemy.move(WIDTH, WIDTH);
        }
        checkCollisions();

    }

}
