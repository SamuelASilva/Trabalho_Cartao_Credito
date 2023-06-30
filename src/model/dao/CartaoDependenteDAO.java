package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.entity.CartaoDependente;
import model.entity.CartaoPrincipal;


public class CartaoDependenteDAO {

	public CartaoDependente consultaCartaoDepend(String numCartao) {
		ConectaBD con = new ConectaBD();
		String sql = "SELECT * FROM cartao WHERE numeroCartao = ?;";
		CartaoDependente cd = null;
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			pst.setString(1, numCartao);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				int idConta = rs.getInt("idconta");
				cd = new CartaoDependente(idConta, false, true);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return cd;
	}
	
	public String consultaCartaoDependPorCpf(String cpf) {
		ConectaBD con = new ConectaBD();
		String sql = "SELECT cp.numeroCartao FROM cartao AS cp \r\n"
				+ "INNER JOIN conta AS c ON cp.idconta = c.idconta \r\n"
				+ "INNER JOIN pessoa AS p ON c.idpessoa = p.idpessoa \r\n"
				+ "WHERE p.cpf = ? AND dependente = TRUE;";
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
	
	public double consultaLimiteCartaoD(String numCartao) {
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
	
	public double consultaSaldoCartaoD(String numCartao) {
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
	
	public void inserirCartaoDepend(CartaoDependente cartaoD, double limiteCD, int idconta) {
		ConectaBD con = new ConectaBD();
		String sql = "INSERT INTO cartao(numeroCartao, cvv, dataEmissao, dataValidade, limite, saldo, idconta, principal, dependente) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			cartaoD.setNumeroCartao(cartaoD.geraNumero());
			pst.setString(1, cartaoD.getNumeroCartao());
			cartaoD.setCvv(cartaoD.geraCVV());
			pst.setString(2, cartaoD.getCvv());
			cartaoD.setDataEmissao(cartaoD.defineDataEmissao());
			pst.setString(3, cartaoD.getDataEmissao());
			cartaoD.setDataValidade(cartaoD.defineDataValidade());
			pst.setString(4, cartaoD.getDataValidade());
			double limite = cartaoD.defineLimite(limiteCD);
			pst.setDouble(5, limite);
			pst.setDouble(6, limite);
			pst.setInt(7, idconta);
			pst.setBoolean(8, false);
			pst.setBoolean(9, true);
			pst.execute();
			
			
			
			System.out.println("Cartão dependente número " + cartaoD.getNumeroCartao() + " criado!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public boolean deletarCartaoDepend(String numCartao) {
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
