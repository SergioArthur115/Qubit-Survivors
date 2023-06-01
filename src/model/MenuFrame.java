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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import javax.swing.event.InternalFrameListener;

public class MenuFrame extends JFrame implements ActionListener {

    public static JPanel mainPanel, gamePanel;
    private static JButton jogarButton;

    public MenuFrame() {
        // Configura o frame
        super("Menu");
        setTitle("Qubit Survivors");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);
        setResizable(false);

        // Adiciona o botão ao painel principal
        criarBotao();

        // Define o painel principal como o conteúdo do frame
        setContentPane(mainPanel);
        setVisible(true);
    }

    public static void showPanel(JPanel panel) {
        JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(panel);
        currentFrame.dispose();
        MenuFrame newFrame = new MenuFrame();
        if (panel == mainPanel) {
            if (gamePanel != null) {
                gamePanel.setVisible(false);
            }
            mainPanel.setVisible(true);
            mainPanel.requestFocusInWindow();
            newFrame.criarBotao();
        } else if (panel == gamePanel) {
            if (gamePanel != null) {
                mainPanel.setVisible(false);
            }
            gamePanel.setVisible(true);
            gamePanel.requestFocusInWindow();
        }
    }
    public static void teste(){
        gamePanel.setEnabled(false);
        mainPanel.setEnabled(true);
        gamePanel.setVisible(false);
        mainPanel.setVisible(true);
        //MenuFrame.criarBotao();
    }

    public void criarBotao() {
        // Cria o botão "Jogar"
        mainPanel = new JPanel();
        jogarButton = new JButton("Jogar");
        jogarButton.addActionListener(this);
        jogarButton.setSize(new Dimension(400, 400));
        jogarButton.setLocation(400, 400);
        mainPanel.add(jogarButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Cria a tela do jogo e a exibe
        GamePanel gamePanel = new GamePanel();
        setContentPane(gamePanel);
        revalidate();
        gamePanel.requestFocusInWindow();
        if (gamePanel.isGameOver()) {
            showPanel(mainPanel);
        }
    }

    public static void main(String[] args) {
        new MenuFrame();
    }
}
