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
            System.out.println("Erro ao adicionar jogador ao banco!\n"
                    + ex.getMessage());
        }
    }

    public int getIDJogadorDAO(String nome_jogador) {
        int id_jogador=0;
        try {
 
            Connection con = Conexao.getConexao();//busca conexão com o BD
            String sql;
            sql = "select max(id_jogador) from jogadores where nome_jogador = ?";
            PreparedStatement pst = con.prepareStatement(sql);//cria espaço de trabalho SQL
            pst.setString(1, nome_jogador);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                id_jogador = rs.getInt("id_jogador");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao pegar id do jogador!\n"
                    + ex.getMessage());
        }
        return id_jogador;
    }
}
