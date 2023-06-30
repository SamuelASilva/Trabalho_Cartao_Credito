package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import model.entity.Transacoes;

public class TransacoesDAO {
	
	public Transacoes consulta(int idtr) {
		ConectaBD con = new ConectaBD();
		String sql = "SELECT * FROM transacao WHERE idtransacao = ?;";
		Transacoes t = null;
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			pst.setInt(1, idtr);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int idtransacao = rs.getInt("idtransacao");
				int idtipo = rs.getInt("idtipo");
				double valor = rs.getDouble("valor");
				double valorPendente = rs.getDouble("valorPendente");
				double valorPago = rs.getDouble("valorPago");
				int idestabelecimento = rs.getInt("idestabelecimento");
				int idcartao = rs.getInt("idcartao");
				t = new Transacoes(idtipo, valor, idestabelecimento, idcartao);
				t.setIdtransacao(idtransacao);
				t.setValorPendente(valorPendente);
				t.setValorPago(valorPago);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return t;
	}
	
	public List<Transacoes> consultaTransacoes(int idcart) {
		ConectaBD con = new ConectaBD();
		String sql = "SELECT * FROM transacao WHERE idcartao = ?;";
		Transacoes t = null;
		List<Transacoes> listaTransacoes = new LinkedList<Transacoes>();
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			pst.setInt(1, idcart);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int idtransacao = rs.getInt("idtransacao");
				int idtipo = rs.getInt("idtipo");
				double valor = rs.getDouble("valor");
				double valorPendente = rs.getDouble("valorPendente");
				double valorPago = rs.getDouble("valorPago");
				int idestabelecimento = rs.getInt("idestabelecimento");
				int idcartao = rs.getInt("idcartao");
				t = new Transacoes(idtipo, valor, idestabelecimento, idcartao);
				t.setIdtransacao(idtransacao);
				t.setValorPendente(valorPendente);
				t.setValorPago(valorPago);
				listaTransacoes.add(t);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return listaTransacoes;
	}
	
	public List<Transacoes> consultaTransacoesPendentes(int idcart) {
		ConectaBD con = new ConectaBD();
		String sql = "SELECT * FROM transacao WHERE idcartao = ? AND valorPendente > 0 AND idtipo = 1;";
		Transacoes t = null;
		List<Transacoes> listaTransacoes = new LinkedList<Transacoes>();
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			pst.setInt(1, idcart);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int idtransacao = rs.getInt("idtransacao");
				int idtipo = rs.getInt("idtipo");
				double valor = rs.getDouble("valor");
				double valorPendente = rs.getDouble("valorPendente");
				double valorPago = rs.getDouble("valorPago");
				int idestabelecimento = rs.getInt("idestabelecimento");
				int idcartao = rs.getInt("idcartao");
				t = new Transacoes(idtipo, valor, idestabelecimento, idcartao);
				t.setIdtransacao(idtransacao);
				t.setValorPendente(valorPendente);
				t.setValorPago(valorPago);
				listaTransacoes.add(t);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return listaTransacoes;
	}
	
	public List<Transacoes> consultaTransacoesPagas(int idcart) {
		ConectaBD con = new ConectaBD();
		String sql = "SELECT * FROM transacao WHERE idcartao = ? AND valorPago > 0 AND valorPendente = 0;";
		Transacoes t = null;
		List<Transacoes> listaTransacoes = new LinkedList<Transacoes>();
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			pst.setInt(1, idcart);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int idtransacao = rs.getInt("idtransacao");
				int idtipo = rs.getInt("idtipo");
				double valor = rs.getDouble("valor");
				double valorPendente = rs.getDouble("valorPendente");
				double valorPago = rs.getDouble("valorPago");
				int idestabelecimento = rs.getInt("idestabelecimento");
				int idcartao = rs.getInt("idcartao");
				t = new Transacoes(idtipo, valor, idestabelecimento, idcartao);
				t.setIdtransacao(idtransacao);
				t.setValorPendente(valorPendente);
				t.setValorPago(valorPago);
				listaTransacoes.add(t);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return listaTransacoes;
	}
	
	public void inserirTransacao(Transacoes t) {
		ConectaBD con = new ConectaBD();
		String sql = "INSERT INTO transacao(idtipo, valor, valorPendente, valorPago, idestabelecimento, idcartao) VALUES (?, ?, ?, ?, ?, ?);";
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			pst.setInt(1, t.getIdtipo());
			pst.setDouble(2, t.getValor());
			pst.setDouble(3, t.getValor());
			pst.setDouble(4, 0);
			pst.setInt(5, t.getIdestabelecimento());
			pst.setInt(6, t.getIdcartao());
			pst.execute();
			System.out.println("Transação inserida!");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public boolean pagarTransacao(int idtr, double vlrPago) {
		ConectaBD con = new ConectaBD();
		Transacoes t = consulta(idtr);
		double valorPendente = t.getValorPendente();
		double valorPago = t.getValorPago();
		
		double novoVlrPendente = valorPendente - vlrPago;
		double novoVlrPago = valorPago + vlrPago;
		
		String sql = "UPDATE transacao SET valorPendente = ? WHERE idtransacao = ?;";
		String sql2 = "UPDATE transacao SET valorPago = ? WHERE idtransacao = ?;";
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			pst.setDouble(1, novoVlrPendente);
			pst.setInt(2, idtr);
			pst.execute();
			
			PreparedStatement pst2 = con.getConexao().prepareStatement(sql2);
			pst2.setDouble(1, novoVlrPago);
			pst2.setInt(2, idtr);
			pst2.execute();
			
			if (pst.getUpdateCount() > 0 && pst2.getUpdateCount() > 0) {
				return true;
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return false;
	}

}
