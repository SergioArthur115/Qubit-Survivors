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
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);
        setResizable(false);

        // Adiciona o botão ao painel principal
        mainPanel = new JPanel();
        jogarButton = new JButton("Jogar");
        jogarButton.addActionListener(this);
        jogarButton.setSize(new Dimension(400, 400));
        jogarButton.setLocation(400, 400);
        mainPanel.add(jogarButton);

        // Define o painel principal como o conteúdo do frame
        setContentPane(mainPanel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Cria a tela do jogo e a exibe
        GamePanel gamePanel = new GamePanel();
        setContentPane(gamePanel);
        revalidate();
        gamePanel.requestFocusInWindow();
    }

    public static void main(String[] args) {
        new MenuFrame();
    }
}
