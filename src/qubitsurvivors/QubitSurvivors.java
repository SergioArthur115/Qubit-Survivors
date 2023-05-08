package qubitsurvivors;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class QubitSurvivors extends JPanel implements KeyListener, Runnable {

    private static final long serialVersionUID = 1L;

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private static final int PLAYER_WIDTH = 50;
    private static final int PLAYER_HEIGHT = 50;
    private static final int PLAYER_SPEED = 5;

    private static final int ENEMY_WIDTH = 30;
    private static final int ENEMY_HEIGHT = 30;
    private static final int ENEMY_SPEED = 3;

    private static final int ITEM_WIDTH = 20;
    private static final int ITEM_HEIGHT = 20;

    private static final int MAX_ENEMIES = 10;
    private static final int MAX_ITEMS = 5;

    private static final int TIME_LIMIT = 60; // seconds

    private Image playerImage;
    private Image enemyImage;
    private Image itemImage;

    private Rectangle player;
    private ArrayList<Rectangle> enemies;
    private ArrayList<Rectangle> items;

    private int score;
    private int timeLeft;

    private boolean running;

    public QubitSurvivors() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);

        playerImage = new ImageIcon("images/charac.png").getImage();
        enemyImage = new ImageIcon("images/enemy.jpg").getImage();
        enemyImage = new ImageIcon("images/enemy1.jpg").getImage();
        enemyImage = new ImageIcon("images/enem2.jpg").getImage();
        itemImage = new ImageIcon("images/item.jpg").getImage();

        player = new Rectangle(WIDTH / 2 - PLAYER_WIDTH / 2, HEIGHT / 2 - PLAYER_HEIGHT / 2, PLAYER_WIDTH, PLAYER_HEIGHT);
        enemies = new ArrayList<Rectangle>();
        items = new ArrayList<Rectangle>();

        score = 0;
        timeLeft = TIME_LIMIT;

        running = true;

        addKeyListener(this);
        setFocusable(true);
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }

    public void run() {
        while (running) {
            update();
            repaint();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
        // move the player
        if (leftPressed) {
            player.x -= PLAYER_SPEED;
        }
        if (rightPressed) {
            player.x += PLAYER_SPEED;
        }
        if (upPressed) {
            player.y -= PLAYER_SPEED;
        }
        if (downPressed) {
            player.y += PLAYER_SPEED;
        }

        // keep the player inside the screen
        if (player.x < 0) {
            player.x = 0;
        }
        if (player.x > WIDTH - PLAYER_WIDTH) {
            player.x = WIDTH - PLAYER_WIDTH;
        }
        if (player.y < 0) {
            player.y = 0;
        }
        if (player.y > HEIGHT - PLAYER_HEIGHT) {
            player.y = HEIGHT - PLAYER_HEIGHT;
        }

        // spawn enemies
        if (enemies.size() < MAX_ENEMIES) {
            Random random = new Random();
            int x = random.nextInt(WIDTH - ENEMY_WIDTH);
            int y = random.nextInt(HEIGHT - ENEMY_HEIGHT);
            Rectangle enemy = new Rectangle(x, y, ENEMY_WIDTH, ENEMY_HEIGHT);
            enemies.add(enemy);
        }

        // move enemies
        for (Rectangle enemy : enemies) {
            if (player.x < enemy.x) {
                enemy.x -= ENEMY_SPEED;
            } else {
                enemy.x += ENEMY_SPEED;
            }
            if (player.y < enemy.y) {
                enemy.y -= ENEMY_SPEED;
            } else {
                enemy.y += ENEMY_SPEED;
            }
        }

        // check for collision with enemies
        for (Rectangle enemy : enemies) {
            if (player.intersects(enemy)) {
                // the player collided with an enemy
                // do something
            }
        }

        // spawn items
        if (items.size() < MAX_ITEMS) {
            Random random = new Random();
            int x = random.nextInt(WIDTH - ITEM_WIDTH);
            int y = random.nextInt(HEIGHT - ITEM_HEIGHT);
            Rectangle item = new Rectangle(x, y, ITEM_WIDTH, ITEM_HEIGHT);
            items.add(item);
        }

        // check for collision with items
        for (Rectangle item : items) {
            if (player.intersects(item)) {
                // the player collected an item
                // do something
            }
        }

        // update the time left
        timeLeft--;
        if (timeLeft == 0) {
            // the game is over
            running = false;
        }
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // draw the player
        g.drawImage(playerImage, player.x, player.y, PLAYER_WIDTH, PLAYER_HEIGHT, null);

        // draw the enemies
        for (Rectangle enemy : enemies) {
            g.drawImage(enemyImage, enemy.x, enemy.y, ENEMY_WIDTH, ENEMY_HEIGHT, null);
        }

        // draw the items
        for (Rectangle item : items) {
            g.drawImage(itemImage, item.x, item.y, ITEM_WIDTH, ITEM_HEIGHT, null);
        }

        // draw the score and time left
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 10, 20);
        g.drawString("Time left: " + timeLeft, 10, 40);
    }

    private boolean leftPressed;
    private boolean rightPressed;
    private boolean upPressed;
    private boolean downPressed;

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                leftPressed = true;
                break;
            case KeyEvent.VK_RIGHT:
                rightPressed = true;
                break;
            case KeyEvent.VK_UP:
                upPressed = true;
                break;
            case KeyEvent.VK_DOWN:
                downPressed = true;
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                leftPressed = false;
                break;
            case KeyEvent.VK_RIGHT:
                rightPressed = false;
                break;
            case KeyEvent.VK_UP:
                upPressed = false;
                break;
            case KeyEvent.VK_DOWN:
                downPressed = false;
                break;
        }
    }

    public void keyTyped(KeyEvent e) {
        // do nothing
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Qubit Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(new QubitSurvivors());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
