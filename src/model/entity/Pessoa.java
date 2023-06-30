package model.entity;

public class Pessoa {
	private int idpessoa;
	private String nome;
	private String cpf;
	private int anoNasci;
	private double salario;
	private boolean principal;

	public Pessoa(String nome, String cpf, int anoNasci, double salario, boolean principal) {
		this.nome = nome;
		this.cpf = cpf;
		this.anoNasci = anoNasci;
		this.salario = salario;
		this.principal = principal;
	}

	public int getIdpessoa() {
		return idpessoa;
	}
	public void setIdpessoa(int idpessoa) {
		this.idpessoa = idpessoa;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getAnoNasci() {
		return anoNasci;
	}
	public void setAnoNasci(int anoNasci) {
		this.anoNasci = anoNasci;
	}

	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}

	public boolean isPrincipal() {
		return principal;
	}
	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}

}
