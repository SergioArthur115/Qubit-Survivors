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
import java.awt.Font;
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
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import static model.MenuFrame.gamePanel;
import static model.MenuFrame.mainPanel;
import qubitsurvivors.QubitSurvivors;
import services.ItemServicos;
import services.JogadorServicos;
import services.PartidaServicos;
import services.ServicosFactory;

public class GamePanel extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener {

    ItemServicos itemS = ServicosFactory.getItemServicos();
    PartidaServicos partidaS = ServicosFactory.getPartidaServicos();
    JogadorServicos jogadorS = ServicosFactory.getJogadorServicos();
    private Character character;
    private ArrayList<Enemy> enemies;
    private ArrayList<Item> itens;
    private ArrayList<Projectile> projectiles;
    private Timer timer;
    private Timer timerTiro;
    private Timer timerInimigo;
    private Timer timerItem;
    private double directionX, directionY, dirX, dirY;
    private double mouseX, mouseY;
    private int score = 0, idJogador = 0;
    private JLabel scoreLabel;
    private LocalTime startTime;
    private LocalDate dataPartida;
    private String playerName;
    private Jogador j;
    private boolean menuStatus = false, go = false;

    public GamePanel() {
        playerName = JOptionPane.showInputDialog(this, "Please enter your name:");
        j = new Jogador();
        j.setNomeJogador(playerName);
        jogadorS.addJogador(j);
        j.setIdJogador(jogadorS.getIDJogadorDAO(playerName));
        MenuFrame.gamePanel = this;
        itens = itemS.BuscarItens();
        timer = new Timer(10, this);
        dataPartida = LocalDate.now();
        startTime = LocalTime.now();
        //duracao = LocalTime.now();
        timer.start();
        Random gerador = new Random();
        character = new Character(350, 350, 50);

        enemies = new ArrayList<>();
        timerInimigo = new Timer(2200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enemies.add(new Enemy(gerador.nextInt(800), gerador.nextInt(800), 50, "images/enemy.png"));
                enemies.add(new Enemy(gerador.nextInt(800), gerador.nextInt(800), 50, "images/enemy1.png"));
            }
        });
        timerInimigo.start();

        timerItem = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                itens.add(new Item(gerador.nextInt(800), gerador.nextInt(800), 50, "images/item.png"));
            }
        });
        timerItem.start();

        projectiles = new ArrayList<>();

        timerTiro = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dirX = directionX;
                dirY = directionY;
                projectiles.add(new Projectile(character.getX(), character.getY(), 25, "images/projectile.png", dirX, dirY));
            }
        });
        timerTiro.start();
        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setPreferredSize(new Dimension(100, 50));
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        scoreLabel.setForeground(Color.white);
        add(scoreLabel);

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
                JOptionPane.showMessageDialog(this, "Game Over!");
                LocalTime endTime = LocalTime.now();
                Duration duration = Duration.between(startTime, endTime);
                LocalTime duracao = LocalTime.MIN.plus(duration);
                Partida p = new Partida(0, "Grass", playerName, dataPartida, score, duracao, jogadorS.getIDJogadorDAO(playerName));
                partidaS.addPartida(p);
                //MenuFrame menuFrame = new MenuFrame();
                //menuFrame.showPanel(MenuFrame.mainPanel);
                QubitSurvivors.menuFrame.dispose();
                QubitSurvivors.menuFrame = new MenuFrame();
                QubitSurvivors.menuFrame.setVisible(true);
                
                menuStatus = true;
                go = true;
                MenuCheck mc = new MenuCheck();
                mc.verifyPanel(menuStatus);
                mc.criarBotao();
                timer.stop();
                break;
            }

            Iterator<Projectile> projectileIterator = projectiles.iterator();
            while (projectileIterator.hasNext()) {
                Projectile projectile = projectileIterator.next();
                Rectangle projectileBounds = projectile.getBounds();
                if (projectileBounds.intersects(enemyBounds)) {
                    score += 10;
                    scoreLabel.setText("Score: " + score);
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
                score += 10;
                scoreLabel.setText("Score: " + score);
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

    public boolean isGameOver() {
        // Check if the player's character has collided with an enemy

        return go;
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
