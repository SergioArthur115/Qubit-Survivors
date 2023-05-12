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
import model.Partida;
/**
 *
 * @author 182120042
 */
public class PartidaDAO {
        public void addPartidaDAO(Partida pVO) {
        try {
            Connection con = Conexao.getConexao();//busca conexão com o BD
            String sql;
            sql = "insert into partidas values (null,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);//cria espaço de trabalho SQL
            pst.setString(1, pVO.getNomeMapa());
            java.sql.Date dataPartida = java.sql.Date.valueOf(pVO.getDataPartida());
            pst.setDate(2, dataPartida);
            pst.setInt(3, pVO.getPontuacao());
            java.sql.Date duracao = java.sql.Date.valueOf(pVO.getDuracao());
            pst.setDate(4, duracao);
            pst.setInt(5, pVO.getIdJogador().getIdJogador());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao adicionar partida ao banco!\n"
                    + ex.getMessage());
        }
    }
}
