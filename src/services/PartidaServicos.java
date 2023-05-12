/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.DAOFactory;
import dao.PartidaDAO;
import model.Partida;

/**
 *
 * @author 182120042
 */
public class PartidaServicos {

    public void addPartida(Partida partida) {
        PartidaDAO pDAO = DAOFactory.getPartidaDAO();
        pDAO.addPartidaDAO(partida);
    }
    
}
