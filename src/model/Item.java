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
public class Item {
    private int idItem;
    private String nomeItem;
    private String descricaoItem;
    private String tipoItem;
    private String raridadeItem;
    private String caracItem;

    public Item() {
    }

    public Item(int idItem, String nomeItem, String descricaoItem, String tipoItem, String raridadeItem, String caracItem) {
        this.idItem = idItem;
        this.nomeItem = nomeItem;
        this.descricaoItem = descricaoItem;
        this.tipoItem = tipoItem;
        this.raridadeItem = raridadeItem;
        this.caracItem = caracItem;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }

    public String getDescricaoItem() {
        return descricaoItem;
    }

    public void setDescricaoItem(String descricaoItem) {
        this.descricaoItem = descricaoItem;
    }

    public String getTipoItem() {
        return tipoItem;
    }

    public void setTipoItem(String tipoItem) {
        this.tipoItem = tipoItem;
    }

    public String getRaridadeItem() {
        return raridadeItem;
    }

    public void setRaridadeItem(String raridadeItem) {
        this.raridadeItem = raridadeItem;
    }

    public String getCaracItem() {
        return caracItem;
    }

    public void setCaracItem(String caracItem) {
        this.caracItem = caracItem;
    }

    @Override
    public String toString() {
        return "Item{" + "idItem=" + idItem + ", nomeItem=" + nomeItem + ", descricaoItem=" + descricaoItem + ", tipoItem=" + tipoItem + ", raridadeItem=" + raridadeItem + ", caracItem=" + caracItem + '}';
    }
    
    
}
