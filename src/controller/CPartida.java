/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.Partida;

/**
 *
 * @author 182120042
 */
public class CPartida {
    ArrayList<Partida> partidas = new ArrayList<>();
    int idPartida = 1;

    public int geraID() {
        return this.idPartida++;
    }
    public void addPartida(Partida p) {
        this.partidas.add(p);
    }


    public ArrayList<Partida> getPartidas() {
        return this.partidas;
    }

    public void removePartidas(Partida p) {
        this.partidas.remove(p);
    }
}
