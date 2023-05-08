/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author 182120042
 */
public class Jogador {

    private int idJogador;
    private String nomeJogador;

    public Jogador() {
    }

    public Jogador(int idJogador, String nomeJogador) {
        this.idJogador = idJogador;
        this.nomeJogador = nomeJogador;
    }

    public int getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(int idJogador) {
        this.idJogador = idJogador;
    }

    public String getNomeJogador() {
        return nomeJogador;
    }

    public void setNomeJogador(String nomeJogador) {
        this.nomeJogador = nomeJogador;
    }

    @Override
    public String toString() {
        return "Jogador{" + "idJogador=" + idJogador + ", nomeJogador=" + nomeJogador + '}';
    }
    
    
}
