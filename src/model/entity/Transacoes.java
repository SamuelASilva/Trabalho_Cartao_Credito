package model.entity;

public class Transacoes {
	private int idtransacao;
	private int idtipo;
	private double valor;
	private double valorPendente;
	private double valorPago;
	private int idestabelecimento;
	private int idcartao;

	public Transacoes(int idtipo, double valor, int idestabelecimento, int idcartao) {
		this.idtipo = idtipo;
		this.valor = valor;
		this.idestabelecimento = idestabelecimento;
		this.idcartao = idcartao;
	}

	public int getIdtransacao() {
		return idtransacao;
	}
	public void setIdtransacao(int idtransacao) {
		this.idtransacao = idtransacao;
	}

	public int getIdtipo() {
		return idtipo;
	}
	public void setIdtipo(int idtipo) {
		this.idtipo = idtipo;
	}

	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public double getValorPendente() {
		return valorPendente;
	}
	public void setValorPendente(double valorPendente) {
		this.valorPendente = valorPendente;
	}

	public double getValorPago() {
		return valorPago;
	}
	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
	}

	public int getIdestabelecimento() {
		return idestabelecimento;
	}
	public void setIdestabelecimento(int idestabelecimento) {
		this.idestabelecimento = idestabelecimento;
	}

	public int getIdcartao() {
		return idcartao;
	}
	public void setIdcartao(int idcartao) {
		this.idcartao = idcartao;
	}
	
}
