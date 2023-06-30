package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.entity.Conta;

public class ContaDAO {

	public Conta consultaConta(String numConta) {
		ConectaBD con = new ConectaBD();
		String sql = "SELECT * FROM conta WHERE numeroConta = ?;";
		Conta c = null;
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			pst.setString(1, numConta);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				String numeroConta = rs.getString("numeroConta");
				int idpessoa = rs.getInt("idpessoa");
				c = new Conta(numeroConta, idpessoa);
				c.setIdconta(rs.getInt("idconta"));
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return c;
	}
	
	public String consultaContaPorCPF(String cpf) {
		ConectaBD con = new ConectaBD();
		String sql = "SELECT * FROM conta AS c \n"
				+ "INNER JOIN pessoa AS p ON c.idpessoa = p.idpessoa \n"
				+ "WHERE p.cpf = ?;";
		String numeroConta = null;
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			pst.setString(1, cpf);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				numeroConta = rs.getString("numeroConta");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return numeroConta;
		
	}
	
	public void inserirConta(Conta conta) {
		ConectaBD con = new ConectaBD();
		String sql = "INSERT INTO conta(numeroConta, idpessoa) VALUES (? ,?);";
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			pst.setString(1, conta.getNumeroConta());
			pst.setInt(2, conta.getIdpessoa());
			pst.execute();
			System.out.println("Conta número " + conta.getNumeroConta() + " criada!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public boolean deletarConta(String numeroConta) {
		ConectaBD con = new ConectaBD();
		String sql = "DELETE FROM conta WHERE numeroConta = ?;";
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			pst.setString(1, numeroConta);
			pst.execute();
			return pst.getUpdateCount() > 0;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return false;
	}

}
