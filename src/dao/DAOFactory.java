/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author 182120042
 */
public class DAOFactory {
    private static JogadorDAO jDAO = new JogadorDAO();
    private static ItemDAO iDAO = new ItemDAO();
    private static PartidaDAO pDAO = new PartidaDAO();
    private static JogadorItemDAO jiDAO = new JogadorItemDAO();

    public static JogadorDAO getJogadorDAO() {
        return jDAO;
    }

    public static ItemDAO getItemDAO() {
        return iDAO;
    }

    public static PartidaDAO getPartidaDAO() {
        return pDAO;
    }

    public static JogadorItemDAO getJogadorItemDAO() {
        return jiDAO;
    }
}
