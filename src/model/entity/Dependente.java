package model.entity;

public class Dependente {
	private int idDependente;
	private int idpessoaPrinc;
	private int idpessoaDepend;
	
	public Dependente(int idpessoaPrinc, int idpessoaDepend) {
		this.idpessoaPrinc = idpessoaPrinc;
		this.idpessoaDepend = idpessoaDepend;
	}

	public int getIdDependente() {
		return idDependente;
	}
	public void setIdDependente(int idDependente) {
		this.idDependente = idDependente;
	}

	public int getIdpessoaPrinc() {
		return idpessoaPrinc;
	}
	public void setIdpessoaPrinc(int idpessoaPrinc) {
		this.idpessoaPrinc = idpessoaPrinc;
	}

	public int getIdpessoaDepend() {
		return idpessoaDepend;
	}
	public void setIdpessoaDepend(int idpessoaDepend) {
		this.idpessoaDepend = idpessoaDepend;
	}

}
