/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.Conquista;

/**
 *
 * @author 182120042
 */
public class CConquista {

    ArrayList<Conquista> conquistas = new ArrayList<>();
    int idConquista = 1;

    public int geraID() {
        return this.idConquista++;
    }

    public void addConquistas(Conquista c) {
        this.conquistas.add(c);
    }

    public ArrayList<Conquista> getConquistas() {
        return this.conquistas;
    }

    public void removeConquistas(Conquista c) {
        this.conquistas.remove(c);
    }
}
