/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.Item;

/**
 *
 * @author 182120042
 */
public class CItem {

    ArrayList<Item> itens = new ArrayList<>();
    int idItem = 1;

    public int geraID() {
        return this.idItem++;
    }

    public void addItem(Item i) {
        this.itens.add(i);
    }

    public ArrayList<Item> getItens() {
        return this.itens;
    }

    public void removePartidas(Item i) {
        this.itens.remove(i);
    }
}
