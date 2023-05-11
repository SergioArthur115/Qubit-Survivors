/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Jogador;

/**
 *
 * @author 182120042
 */
public class JogadorDAO {
     public void addJogadorDAO(Jogador cVO) {       
        try {
            Connection con = Conexao.getConexao();//busca conexão com o BD
            String sql;
            sql = "insert into jogadores values (null,?)";
            PreparedStatement pst = con.prepareStatement(sql);//cria espaço de trabalho SQL
            pst.setString(1, cVO.getNomeJogador());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao adicionar ao banco!\n"
                    + ex.getMessage());
        }
    }
}
