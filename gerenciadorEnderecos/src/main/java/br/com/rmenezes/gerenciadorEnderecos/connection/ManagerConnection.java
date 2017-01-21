package br.com.rmenezes.gerenciadorEnderecos.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.h2.tools.Server;

public class ManagerConnection {
	
	    static String DRIVER_H2 = "org.h2.Driver";
	    static Server servidor;
	    static String URL_H2 = "jdbc:h2:D:/h2";
	    static String USER_H2 = "sa";
	    static String PASSWORD_H2 = "";
	    private static Connection conexao_H2 = null;
	    public static Connection getConexao() throws SQLException {
	        if (conexao_H2 == null) {
	            try {
	                servidor = Server.createTcpServer();
	                servidor.start();
	                System.out.println("Servidor inicializado");
	            } catch (SQLException ex) {
	                StringBuffer mensagem = new StringBuffer("Não foi possível iniciar o servidor");
	                mensagem.append("\nMotivo: " + ex.getMessage());
	            }
	            try {
	                Class.forName(DRIVER_H2);
	                System.out.println("Conectando ao banco H2");
	                conexao_H2 = DriverManager.getConnection(URL_H2, USER_H2, PASSWORD_H2);
	                return conexao_H2;
	            } catch (ClassNotFoundException e) {
	                System.out.println("erro" + e + "\n\n");
	                throw new SQLException(e.getMessage());
	            }
	        } else {
	            return conexao_H2;
	        }
	    }

}
