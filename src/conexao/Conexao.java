/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

/**
 *
 * @author 182120042
 */
public class Conexao {

    private static String url = "jdbc:mysql://localhost:3306/qubits_Survivors";//cria uma constante com o endereço do BD/schema
    private static String user = "root";//cria uma constante com um user do BD
    private static String pass = "";//cria uma constante com a senha do BD

    public static Connection getConexao() throws SQLException {
        Connection c = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            throw new SQLException("Erro ao conectar banco!\n" + e.getMessage());
        }
        return c;
    }
}
