/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author 182120042
 */
public class Partida {

    private int idPartida;
    private String nomeMapa;
    private String nomeJogador;
    private LocalDate dataPartida;
    private int pontuacao;
    private LocalDate duracao;
    private Jogador idJogador;

    public Partida() {
    }

    public Partida(int idPartida, String nomeMapa, String nomeJogador, LocalDate dataPartida, int pontuacao, LocalDate duracao, Jogador idJogador) {
        this.idPartida = idPartida;
        this.nomeMapa = nomeMapa;
        this.nomeJogador = nomeJogador;
        this.dataPartida = dataPartida;
        this.pontuacao = pontuacao;
        this.duracao = duracao;
        this.idJogador = idJogador;
    }

    public int getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(int idPartida) {
        this.idPartida = idPartida;
    }

    public String getNomeMapa() {
        return nomeMapa;
    }

    public void setNomeMapa(String nomeMapa) {
        this.nomeMapa = nomeMapa;
    }

    public String getNomeJogador() {
        return nomeJogador;
    }

    public void setNomeJogador(String nomeJogador) {
        this.nomeJogador = nomeJogador;
    }

    public LocalDate getDataPartida() {
        return dataPartida;
    }

    public void setDataPartida(LocalDate dataPartida) {
        this.dataPartida = dataPartida;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public LocalDate getDuracao() {
        return duracao;
    }

    public void setDuracao(LocalDate duracao) {
        this.duracao = duracao;
    }

    public Jogador getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(Jogador idJogador) {
        this.idJogador = idJogador;
    }

    @Override
    public String toString() {
        return "Partida{" + "idPartida=" + idPartida + ", nomeMapa=" + nomeMapa + ", nomeJogador=" + nomeJogador + ", dataPartida=" + dataPartida + ", pontuacao=" + pontuacao + ", duracao=" + duracao + ", idJogador=" + idJogador + '}';
    }
    
    
}
