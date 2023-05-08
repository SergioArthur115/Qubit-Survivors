/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.Jogador;

/**
 *
 * @author 182120042
 */
public class CJogador {

    ArrayList<Jogador> jogadores = new ArrayList<>();
    int idJogador = 1;

    public int geraID() {
        return this.idJogador++;
    }
    public void addJogador(Jogador j) {
        this.jogadores.add(j);
    }


    public ArrayList<Jogador> getJogadores() {
        return this.jogadores;
    }

    public void removeJogadores(Jogador j) {
        this.jogadores.remove(j);
    }
}
