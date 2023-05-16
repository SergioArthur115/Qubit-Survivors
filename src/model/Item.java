/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

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
    private int x, y, size;
    private BufferedImage image;

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

    public Item(int x, int y, int size, String imagePath) {
        this.x = x;
        this.y = y;
        this.size = size;

        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void draw(Graphics g) {
        g.drawImage(image, x, y, size, size, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x + 5, y + 5, size - 10, size - 10);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
