package model.entity;

public class Conta {
	private int idconta;
	private String numeroConta;
	private int idpessoa;

	public Conta(String numeroConta, int idpessoa) {
		this.numeroConta = numeroConta;
		this.idpessoa = idpessoa;
	}

	public int getIdconta() {
		return idconta;
	}
	public void setIdconta(int idconta) {
		this.idconta = idconta;
	}

	public String getNumeroConta() {
		return numeroConta;
	}
	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public int getIdpessoa() {
		return idpessoa;
	}
	public void setIdpessoa(int idpessoa) {
		this.idpessoa = idpessoa;
	}

}
