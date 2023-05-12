/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.DAOFactory;
import dao.JogadorDAO;
import model.Jogador;

/**
 *
 * @author 182120042
 */
public class JogadorServicos {

    public void addJogador(Jogador cVO) {
        JogadorDAO jDAO = DAOFactory.getJogadorDAO();
        jDAO.addJogadorDAO(cVO);
    }
    public int getIDJogadorDAO(String nome_jogador) {
        JogadorDAO jDAO = DAOFactory.getJogadorDAO();
        return jDAO.getIDJogadorDAO(nome_jogador);
    }
}
