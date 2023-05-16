/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.DAOFactory;
import dao.ItemDAO;
import java.util.ArrayList;
import model.Item;

/**
 *
 * @author 182120042
 */
public class ItemServicos {

    public ArrayList<Item> BuscarItens() {
        ItemDAO iDAO = DAOFactory.getItemDAO(); 
        return iDAO.getItensDAO();
    }
}
