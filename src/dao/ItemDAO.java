/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Item;

/**
 *
 * @author 182120042
 */
public class ItemDAO {

    public ArrayList<Item> getItensDAO() {
        ArrayList<Item> itens = new ArrayList<>();
        try {
            Connection con = Conexao.getConexao();
            String sql = "select * from itens";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Item i = new Item();
                i.setIdItem(rs.getInt("id_item"));
                i.setNomeItem(rs.getString("nome_item"));
                i.setDescricaoItem(rs.getString("descricao_item"));
                i.setTipoItem(rs.getString("tipo_item"));
                i.setRaridadeItem(rs.getString("raridade_item"));
                i.setCaracItem(rs.getString("carac_item"));
                itens.add(i);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar Itens!\n"
                    + ex.getMessage());
        }
        return itens;
    }
}
