package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.entity.CartaoPrincipal;

public class CartaoPrincipalDAO {

	public CartaoPrincipal consultaCartaoPrinc(String numCartao) {
		ConectaBD con = new ConectaBD();
		String sql = "SELECT * FROM cartao WHERE numeroCartao = ?;";
		CartaoPrincipal cp = null;
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			pst.setString(1, numCartao);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				int idCartao = rs.getInt("idcartao");
				int idConta = rs.getInt("idconta");
				String numeroCartao = rs.getString("numeroCartao");
				String cvv = rs.getString("cvv");
				double limite = rs.getDouble("limite");
				double saldo = rs.getDouble("saldo");
				cp = new CartaoPrincipal(idConta, true, false);
				cp.setIdcartao(idCartao);
				cp.setNumeroCartao(numeroCartao);
				cp.setCvv(cvv);
				cp.setLimite(limite);
				cp.setSaldo(saldo);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return cp;
	}
	
	public String consultaCartaoPrincPorCpf(String cpf) {
		ConectaBD con = new ConectaBD();
		String sql = "SELECT cp.numeroCartao FROM cartao AS cp \r\n"
				+ "INNER JOIN conta AS c ON cp.idconta = c.idconta \r\n"
				+ "INNER JOIN pessoa AS p ON c.idpessoa = p.idpessoa \r\n"
				+ "WHERE p.cpf = ?;";
		String numeroCartao = null;
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			pst.setString(1, cpf);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				numeroCartao = rs.getString("numeroCartao");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return numeroCartao;
	}
	
	public double consultaLimiteCartaoP(String numCartao) {
		ConectaBD con = new ConectaBD();
		String sql = "SELECT limite FROM cartao WHERE numeroCartao = ?;";
		double limite = 0;
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			pst.setString(1, numCartao);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				limite = rs.getDouble("limite");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return limite;
	}
	
	public double consultaSaldoCartaoP(String numCartao) {
		ConectaBD con = new ConectaBD();
		String sql = "SELECT saldo FROM cartao WHERE numeroCartao = ?;";
		double saldo = 0;
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			pst.setString(1, numCartao);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				saldo = rs.getDouble("saldo");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return saldo;
	}
	
	public void inserirCartaoPrinc(CartaoPrincipal cartaoP, double salario, int idconta) {
		ConectaBD con = new ConectaBD();
		String sql = "INSERT INTO cartao(numeroCartao, cvv, dataEmissao, dataValidade, limite, saldo, idconta, principal, dependente) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			cartaoP.setNumeroCartao(cartaoP.geraNumero());
			pst.setString(1, cartaoP.getNumeroCartao());
			cartaoP.setCvv(cartaoP.geraCVV());
			pst.setString(2, cartaoP.getCvv());
			cartaoP.setDataEmissao(cartaoP.defineDataEmissao());
			pst.setString(3, cartaoP.getDataEmissao());
			cartaoP.setDataValidade(cartaoP.defineDataValidade());
			pst.setString(4, cartaoP.getDataValidade());
			double limite = cartaoP.defineLimite(salario);
			pst.setDouble(5, limite);
			pst.setDouble(6, limite);
			pst.setInt(7, idconta);
			pst.setBoolean(8, true);
			pst.setBoolean(9, false);
			pst.execute();
			System.out.println("Cartão principal número " + cartaoP.getNumeroCartao() + " criado!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public boolean deletarCartaoPrinc(String numCartao) {
		ConectaBD con = new ConectaBD();
		String sql = "DELETE FROM cartao WHERE numeroCartao = ?;";
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			pst.setString(1, numCartao);
			pst.execute();
			return pst.getUpdateCount() > 0;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return false;
	}

}
