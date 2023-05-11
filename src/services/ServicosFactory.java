/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author 182120042
 */
public class ServicosFactory {

    private static JogadorServicos jServicos = new JogadorServicos();
    private static ItemServicos iServicos = new ItemServicos();
    private static PartidaServicos pServicos = new PartidaServicos();
    private static JogadorItemServicos jiServicos = new JogadorItemServicos();

    public static JogadorServicos getJogadorServicos() {
        return jServicos;
    }

    public static ItemServicos getItemServicos() {
        return iServicos;
    }

    public static PartidaServicos getPartidaServicos() {
        return pServicos;
    }

    public static JogadorItemServicos getJogadorItemServicos() {
        return jiServicos;
    }
}
