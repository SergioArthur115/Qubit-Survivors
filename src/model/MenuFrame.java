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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFrame extends JFrame implements ActionListener {

    public static JPanel mainPanel,gamePanel;
    private JButton jogarButton;

    public MenuFrame() {
        // Configura o frame
        super("Menu");
        setTitle("Qubit Survivors");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);
        setResizable(false);

        // Cria o botão "Jogar"
        jogarButton = new JButton("Jogar");
        jogarButton.addActionListener(this);

        // Adiciona o botão ao painel principal
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(jogarButton, BorderLayout.CENTER);

        // Define o painel principal como o conteúdo do frame
        setContentPane(mainPanel);
    }

    public void showPanel(JPanel panel) {
        // Exibe o painel recebido como parâmetro e esconde o outro painel
        if (panel == mainPanel) {
            mainPanel.setVisible(true);
            gamePanel.setVisible(false);
        } else if (panel == gamePanel) {
            mainPanel.setVisible(false);
            gamePanel.setVisible(true);
        }
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
