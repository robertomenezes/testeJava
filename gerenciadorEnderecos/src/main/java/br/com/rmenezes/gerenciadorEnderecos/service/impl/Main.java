package br.com.rmenezes.gerenciadorEnderecos.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.rmenezes.gerenciadorEnderecos.connection.ManagerConnection;

public class Main {
	public static void main(String[] args) throws SQLException {
		
		ManagerConnection connection = null;
		
		Connection conexao = connection.getConexao();
		conexao.close();
		System.out.println(conexao.toString());
		
	}
}
