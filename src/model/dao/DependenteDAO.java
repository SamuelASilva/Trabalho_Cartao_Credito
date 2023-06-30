package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.entity.Dependente;

public class DependenteDAO {

	public Dependente consultaDependente(String cpfP) {
		ConectaBD con = new ConectaBD();
		String sql = "SELECT \r\n"
				+ "	pDepe.idpessoa AS idPDepen, \r\n"
				+ "	pPrin.idpessoa AS idPPrinc \r\n"
				+ "FROM dependente AS d\r\n"
				+ "INNER JOIN pessoa AS pPrin ON d.idpessoaPrinc = pPrin.idpessoa\r\n"
				+ "INNER JOIN pessoa AS pDepe ON d.idpessoaDepen = pDepe.idpessoa\r\n"
				+ "WHERE pPrin.cpf = ?;";
		Dependente d = null;
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			pst.setString(1, cpfP);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				int idPPrinc = rs.getInt("idPPrinc");
				int idPDepen = rs.getInt("idPDepen");
				d = new Dependente(idPPrinc, idPDepen);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return d;
	}
	
	public String consultaPrincipal(String cpfD) {
		ConectaBD con = new ConectaBD();
		String sql = "SELECT \r\n"
				+ "	pPrin.cpf AS cpfPPrinc \r\n"
				+ "FROM dependente AS d\r\n"
				+ "INNER JOIN pessoa AS pPrin ON d.idpessoaPrinc = pPrin.idpessoa\r\n"
				+ "INNER JOIN pessoa AS pDepe ON d.idpessoaDepen = pDepe.idpessoa\r\n"
				+ "WHERE pDepe.cpf = ?;";
		String cpfPrinc = null;
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			pst.setString(1, cpfD);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				cpfPrinc = rs.getString("cpfPPrinc");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return cpfPrinc;
	}
	
	public String consultaCPFDependente(String cpfP) {
		ConectaBD con = new ConectaBD();
		String sql = "SELECT \r\n"
				+ "	pDepe.cpf AS cpfpDepe \r\n"
				+ "FROM dependente AS d\r\n"
				+ "INNER JOIN pessoa AS pPrin ON d.idpessoaPrinc = pPrin.idpessoa\r\n"
				+ "INNER JOIN pessoa AS pDepe ON d.idpessoaDepen = pDepe.idpessoa\r\n"
				+ "WHERE pPrin.cpf = ?;";
		String cpfDepend = null;
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			pst.setString(1, cpfP);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				cpfDepend = rs.getString("cpfpDepe");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return cpfDepend;
	}
	
	public void inserirDependente(String cpfPrinc, String cpfDepend) {
		ConectaBD con = new ConectaBD();
		String sql = "INSERT INTO dependente(idpessoaPrinc, idpessoaDepen) "
				+ "VALUES (?, ?);";
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			int idpPrinc = new PessoaDAO().consultarCPF(cpfPrinc).getIdpessoa();
			int idpDepen = new PessoaDAO().consultarCPF(cpfDepend).getIdpessoa();
			pst.setInt(1, idpPrinc);
			pst.setInt(2, idpDepen);
			pst.execute();
			
			sql = "UPDATE pessoa SET principal = FALSE WHERE cpf = ?;";
			try {
				pst = con.getConexao().prepareStatement(sql);
				pst.setString(1, cpfDepend);
				pst.execute();
				
				System.out.println("Dependente criado!");
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void retirarDependente(String cpfPrinc, String cpfDepend) {
		ConectaBD con = new ConectaBD();
		String sql = "DELETE FROM dependente WHERE idpessoaPrinc = ? AND idpessoaDepen = ?;";
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			Dependente d = consultaDependente(cpfPrinc);
			pst.setInt(1, d.getIdpessoaPrinc());
			pst.setInt(2, d.getIdpessoaDepend());
			pst.execute();
			
			sql = "UPDATE pessoa SET principal = TRUE WHERE cpf = ?;";
			try {
				pst = con.getConexao().prepareStatement(sql);
				pst.setString(1, cpfDepend);
				pst.execute();
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
