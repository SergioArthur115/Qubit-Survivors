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
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;
import services.ItemServicos;
import services.ServicosFactory;

public class GamePanel extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener {

    ItemServicos itemS = ServicosFactory.getItemServicos();
    private Character character;
    private ArrayList<Enemy> enemies;
    private ArrayList<Item> itens;
    private ArrayList<Projectile> projectiles;
    private Timer timer;
    private Timer timerTiro;
    private double directionX, directionY, dirX, dirY;
    private double mouseX, mouseY;

    public GamePanel() {

        itens = itemS.BuscarItens();
        character = new Character(250, 250, 50);

        enemies = new ArrayList<>();
        enemies.add(new Enemy(100, 100, 50, "images/enemy.png"));
        enemies.add(new Enemy(400, 400, 50, "images/enemy1.png"));

        itens.add(new Item(100, 100, 50, "images/item.png"));

        projectiles = new ArrayList<>();

        timer = new Timer(10, this);
        timer.start();
        timerTiro = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dirX=directionX;
                dirY=directionY;
                projectiles.add(new Projectile(character.getX(), character.getY(), 25, "images/projectile.png",dirX,dirY));
            }
        });
        timerTiro.start();

        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        setFocusable(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        BackgroundQS background = new BackgroundQS();
        background.draw(g);
        character.draw(g);

        for (Enemy enemy : enemies) {
            enemy.draw(g);

        }

        for (Item item : itens) {
            item.draw(g);

        }
        for (Projectile projectile : projectiles) {
            projectile.draw(g);
        }
    }

    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        double dx = mouseX - character.getX();//inclinação da reta em X
        double dy = mouseY - character.getY();//inclinação da reta em Y
        double distance = Math.sqrt(dx * dx + dy * dy);
        directionX = dx / distance;
        directionY = dy / distance;
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

        Iterator<Enemy> enemyIterator = enemies.iterator();
        while (enemyIterator.hasNext()) {
            Enemy enemy = enemyIterator.next();
            Rectangle enemyBounds = enemy.getBounds();

            if (characterBounds.intersects(enemyBounds)) {
                System.out.println("Game Over!");
                timer.stop();
            }

            Iterator<Projectile> projectileIterator = projectiles.iterator();
            while (projectileIterator.hasNext()) {
                Projectile projectile = projectileIterator.next();
                Rectangle projectileBounds = projectile.getBounds();
                if (projectileBounds.intersects(enemyBounds)) {
                    System.out.println("acertou");
                    projectileIterator.remove();
                    enemyIterator.remove();
                }
            }
        }

        Iterator<Item> itemIterator = itens.iterator();
        while (itemIterator.hasNext()) {
            Item item = itemIterator.next();
            Rectangle itemBounds = item.getBounds();

            if (characterBounds.intersects(itemBounds)) {
                System.out.println("Item!");
                itemIterator.remove();
            }
        }

        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Obtenha a posição atual do personagem
        Point personPosition = new Point(character.getX(), character.getY());
        for (Projectile projectile : projectiles) {
            double speed = 10.0; // velocidade do projétil
            double moveX = projectile.getDirX() * speed;
            double moveY = projectile.getDirY() * speed;
            projectile.move(moveX, moveY);
        }

        // Para cada inimigo, calcule a direção em que ele deve se mover
        for (Enemy enemy : enemies) {
            // Calcule a distância entre o personagem e o inimigo
            double distance = Math.sqrt(Math.pow(personPosition.x - enemy.getX(), 2) + Math.pow(personPosition.y - enemy.getY(), 2));

            // Determine a direção em que o inimigo deve se mover
            double directionX = (personPosition.x - enemy.getX()) / distance;
            double directionY = (personPosition.y - enemy.getY()) / distance;

            // Multiplique o vetor de direção pelo valor da velocidade dos inimigos
            double speed = 5.0; // velocidade dos inimigos
            double moveX = directionX * speed;
            double moveY = directionY * speed;

            // Adicione o vetor de movimento à posição atual do inimigo para atualizar sua posição
            enemy.move((int) moveX, (int) moveY);
        }

        checkCollisions();

    }

    @Override
    public void mouseClicked(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
