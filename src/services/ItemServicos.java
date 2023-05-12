/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.DAOFactory;
import dao.ItemDAO;
import model.Item;

/**
 *
 * @author 182120042
 */
public class ItemServicos {

    public void BuscarItens(String name) {
        ItemDAO iDAO = DAOFactory.getItemDAO();
        iDAO.getItensDAO();
    }
}
