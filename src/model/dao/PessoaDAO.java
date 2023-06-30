package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.entity.Pessoa;

public class PessoaDAO {
	
	public Pessoa consultarCPF(String cpf_pessoa) {
		ConectaBD con = new ConectaBD();
		String sql = "SELECT * FROM pessoa WHERE cpf = ?;";
		Pessoa p = null;
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			pst.setString(1, cpf_pessoa);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				int idpessoa = rs.getInt("idpessoa");
				String nome = rs.getString("nome");
				String cpf = rs.getString("cpf");
				int anoNasci = rs.getInt("anoNascimento");
				double salario = rs.getDouble("salario");
				boolean principal = rs.getBoolean("principal");
				p = new Pessoa(nome, cpf, anoNasci, salario, principal);
				p.setIdpessoa(idpessoa);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return p;
	}


	public void inserir(Pessoa pessoa) {
		ConectaBD con = new ConectaBD();
		String sql = "INSERT INTO pessoa(nome, cpf, anoNascimento, salario, principal) VALUES (?, ?, ?, ?, ?);";
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			pst.setString(1, pessoa.getNome());
			pst.setString(2, pessoa.getCpf());
			pst.setInt(3, pessoa.getAnoNasci());
			pst.setDouble(4, pessoa.getSalario());
			pst.setBoolean(5, true);
			pst.execute();
			System.out.println(pessoa.getNome() + " inserido!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public boolean deletar(String cpf) {
		ConectaBD con = new ConectaBD();
		String sql = "DELETE FROM pessoa WHERE cpf = ?;";
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			pst.setString(1, cpf);
			pst.execute();
			return pst.getUpdateCount() > 0;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean atualizar(String opcao, String cpf, String info) {
		ConectaBD con = new ConectaBD();
		String sql = "UPDATE pessoa SET ${opcao} = ? WHERE cpf = ?;";
		sql = sql.replace("${opcao}", opcao);
		
		if (opcao == "anoNasci") {
			int anoNasci;
			anoNasci = Integer.parseInt(info);
			try {
				PreparedStatement pst = con.getConexao().prepareStatement(sql);
				pst.setInt(1, anoNasci);
				pst.setString(2, cpf);
				pst.execute();
				return pst.getUpdateCount() > 0;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else if (opcao == "salario") {
			double salario;
			salario = Double.parseDouble(info);
			try {
				PreparedStatement pst = con.getConexao().prepareStatement(sql);
				pst.setDouble(1, salario);
				pst.setString(2, cpf);
				pst.execute();
				return pst.getUpdateCount() > 0;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {
			try {
				PreparedStatement pst = con.getConexao().prepareStatement(sql);
				pst.setString(1, info);
				pst.setString(2, cpf);
				pst.execute();
				return pst.getUpdateCount() > 0;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		return false;
	}

}
