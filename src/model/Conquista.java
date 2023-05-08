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
public class Conquista {
    private int idConquista;
    private String nomeConquista;
    private String descricaoConquista;
    private LocalDate dataConquista;

    public Conquista() {
    }

    public Conquista(int idConquista, String nomeConquista, String descricaoConquista, LocalDate dataConquista) {
        this.idConquista = idConquista;
        this.nomeConquista = nomeConquista;
        this.descricaoConquista = descricaoConquista;
        this.dataConquista = dataConquista;
    }

    public int getIdConquista() {
        return idConquista;
    }

    public void setIdConquista(int idConquista) {
        this.idConquista = idConquista;
    }

    public String getNomeConquista() {
        return nomeConquista;
    }

    public void setNomeConquista(String nomeConquista) {
        this.nomeConquista = nomeConquista;
    }

    public String getDescricaoConquista() {
        return descricaoConquista;
    }

    public void setDescricaoConquista(String descricaoConquista) {
        this.descricaoConquista = descricaoConquista;
    }

    public LocalDate getDataConquista() {
        return dataConquista;
    }

    public void setDataConquista(LocalDate dataConquista) {
        this.dataConquista = dataConquista;
    }

    @Override
    public String toString() {
        return "Conquista{" + "idConquista=" + idConquista + ", nomeConquista=" + nomeConquista + ", descricaoConquista=" + descricaoConquista + ", dataConquista=" + dataConquista + '}';
    }
    
    
}
