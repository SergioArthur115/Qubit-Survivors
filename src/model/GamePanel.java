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

    public GamePanel() {
        projectiles = new ArrayList<>();
        itens = itemS.BuscarItens();
        character = new Character(250, 250, 50, projectiles);

        character.setProjectiles(projectiles);

        enemies = new ArrayList<>();
        enemies.add(new Enemy(100, 100, 50, "images/enemy.png"));
        enemies.add(new Enemy(400, 400, 50, "images/enemy1.png"));

        itens.add(new Item(100, 100, 50, "images/item.png"));

        timer = new Timer(10, this);
        timer.start();

        // Cria um temporizador que dispara um projétil a cada 2 segundos
        timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                teste();
                repaint();
            }
        });
        timer.start();

        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        setFocusable(true);
    }

    public void teste() {
        for (int i = projectiles.size() - 1; i >= 0; i--) {
            Projectile projectile = projectiles.get(i);
            projectile.move();
            if (projectile.getX() < 0 || projectile.getX() > getWidth() || projectile.getY() < 0 || projectile.getY() > getHeight()) {
                projectiles.remove(i);
            }
        }
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
        int mouseX = e.getX();
        int mouseY = e.getY();
        int dx = mouseX - character.getX();
        int dy = mouseY - character.getY();
        character.setProjectileDirection(Math.atan2(dy, dx));
        System.out.println(mouseX);
        System.out.println(mouseY);
        System.out.println(dx);
        System.out.println(dy);
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
        for (Item item : itens) {
            Rectangle ItemBounds = item.getBounds();

            if (characterBounds.intersects(ItemBounds)) {
                System.out.println("Item!");
            }
        }
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Obtenha a posição atual do personagem
        Point personPosition = new Point(character.getX(), character.getY());

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
