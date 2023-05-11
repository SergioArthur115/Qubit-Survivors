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
import model.Item;

/**
 *
 * @author 182120042
 */
public class ItemDAO {
        public Item getItemByName(String nome_item) {
        Item i = new Item();
        try {
            Connection con = Conexao.getConexao();
            String sql = "select * from itens where nome_item = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, nome_item);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                i.setIdItem(rs.getInt("id_item"));
                i.setNomeItem(rs.getString("nome_item"));
                i.setDescricaoItem(rs.getString("descricao_item"));
                i.setTipoItem(rs.getString("tipo_item"));
                i.setRaridadeItem(rs.getString("raridade_item"));
                i.setCaracItem(rs.getString("carac_item"));
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar Item!\n"
                    + ex.getMessage());
        }
        return i;
    }
}
