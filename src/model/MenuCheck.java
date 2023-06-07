/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import static model.MenuFrame.gamePanel;
import static model.MenuFrame.mainPanel;

/**
 *
 * @author 182120042
 */
public class MenuCheck implements ActionListener {
    
    public MenuCheck() {
    }

    public void verifyPanel(boolean status) {
        System.out.println("status= " + status);
        System.out.println("GamePanel= " + gamePanel);
        System.out.println("MainPanel= " + mainPanel);
        if (status) {
            gamePanel.setEnabled(false);
            mainPanel.setEnabled(true);
            gamePanel.setVisible(false);
            mainPanel.setVisible(true);

            mainPanel.revalidate();
            mainPanel.repaint();
            System.out.println("MainPanel= " + mainPanel);
            System.out.println("MainPanelComponents= " + mainPanel.getComponents());

            //MainPanel é valido ou não
            if (mainPanel.isValid()) {
                System.out.println("mainPanel valid");
            } else {
                System.out.println("mainPanel não valid");
            }

            //MainPanel é displayable ou não
            if (mainPanel.isDisplayable()) {
                System.out.println("mainPanel displayable");
            } else {
                System.out.println("mainPanel não displayable");
            }

            //MainPanel é visivel ou não
            if (mainPanel.isShowing()) {
                System.out.println("MainPanel visivel");
            } else {
                System.out.println("MainPanel não visivel");
            }

            //GamePanel é valido ou não
            if (gamePanel.isValid()) {
                System.out.println("gamePanel valid");
            } else {
                System.out.println("gamePanel não valid");
            }

            //GamePanel é displayable ou não
            if (gamePanel.isDisplayable()) {
                System.out.println("gamePanel displayable");
            } else {
                System.out.println("GamePanel não displayable");
            }

            //GamePanel é visivel ou não
            if (gamePanel.isShowing()) {
                System.out.println("GamePanel visivel");
            } else {
                System.out.println("GamePanel não visivel");
            }

        }
    }

    public void criarBotao() {
        // Cria o botão "Jogar"
        //mainPanel = new JPanel();
        JButton jogarButton = new JButton("Jogar");
        jogarButton.addActionListener((ActionListener) this);
        jogarButton.setSize(new Dimension(400, 400));
        jogarButton.setLocation(400, 400);

        mainPanel.add(jogarButton);

        System.out.println("botao= " + jogarButton);
        boolean botao = false;
        Component[] componentes = mainPanel.getComponents();
        for (Component componente : componentes) {
            if (componente.equals(jogarButton)) {
                botao = true;
            } else {
                botao = false;
            }
        }
        System.out.println("Menubotao= " + botao);
        jogarButton.revalidate();
        jogarButton.repaint();
        mainPanel.revalidate();
        mainPanel.repaint();
        if (jogarButton.isValid()) {
            System.out.println("jogarButton valid");
        } else {
            System.out.println("jogarButton não valid");
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
