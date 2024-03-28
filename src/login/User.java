package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Essa é uma classe que simula o que seria um usuário real, simulando todas as
 * conexões com o banco e verificações.
 */
public class User {

    /**
     * 
     * Método responsável por efetuar a conexão com o banco.
     * 
     * @return Connection
     */

    public Connection conectarBD() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.Driver.Manager").newInstance();
            String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123";
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
        }
        return conn;
    }

    /**
     * Nomedo usuário
     */

    public String nome = "";

    /**
     * Retorna true se o nome do usuário existir. Senão, continua false.
     */
    public boolean result = false;

    /**
     * Método responsável por verificar se o usuário existe no banco, verificando 
     * se o login e senha são compatíveis.
     * 
     * @param login 
     * @param senha 
     * @return true se ambos forem compatíveis e false caso a combinação não exsita 
     */
    public boolean verificarUsuario(String login, String senha) {
        String sql = "";
        Connection conn = conectarBD();
        // INSTRUÇÃO SQL
        sql += "select nome from usuarios ";
        sql += "where login = " + "'" + login + "'";
        sql += " and senha = " + "'" + senha + "';";

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                result = true;
                nome = rs.getString("nome");
            }
        } catch (Exception e) {
        }

        return result;
    }
}
