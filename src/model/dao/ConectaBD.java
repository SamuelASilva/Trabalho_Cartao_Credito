package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaBD {
	private Connection conexao;

	public ConectaBD() {
		String url = "jdbc:mariadb://localhost:3306/trab_cartaoCredito";
		String usuario = "root";
		String senha = "123";
		try {
			conexao = DriverManager.getConnection(url, usuario, senha);
			System.out.println("Conexão realizada com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	public Connection getConexao() {
		return conexao;
	}

}
