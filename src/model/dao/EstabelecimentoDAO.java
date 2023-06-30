package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.entity.Estabelecimento;

public class EstabelecimentoDAO {

	public Estabelecimento consultaestab(String nomeestabelecimento) {
    	ConectaBD con = new ConectaBD();
    	String sql = "SELECT * FROM estabelecimento WHERE estabelecimento like ?";
    	Estabelecimento d = null;
    	try {
    		PreparedStatement pst = con.getConexao().prepareStatement(sql);
    		pst.setString(1, nomeestabelecimento);
    		ResultSet rs = pst.executeQuery();
    		if (rs.next()) {
    			int idestabelecimento = rs.getInt("idestabelecimento");
    			d = new Estabelecimento(nomeestabelecimento);
    			d.setIdestabelecimento(idestabelecimento);
    		}
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    	}	
    	return d;
    } 
	
	public String consultaNomeEstab(int idestab) {
    	ConectaBD con = new ConectaBD();
    	String sql = "SELECT * FROM estabelecimento WHERE idestabelecimento = ?";
    	String nomeEstab = null;
    	try {
    		PreparedStatement pst = con.getConexao().prepareStatement(sql);
    		pst.setInt(1, idestab);
    		ResultSet rs = pst.executeQuery();
    		if (rs.next()) {
    			nomeEstab = rs.getString("estabelecimento");
    		}
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    	}	
    	return nomeEstab;
    }
	
    public void inserir(Estabelecimento estabelecimento) {
        ConectaBD con = new ConectaBD();
        String sql = "INSERT INTO estabelecimento(estabelecimento) VALUES (?)";
        try {
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            pst.setString(1, estabelecimento.getNome());
            pst.execute();
            System.out.println(estabelecimento.getNome() + " inserido!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }   
    }    
}
