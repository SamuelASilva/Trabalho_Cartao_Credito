package model.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class CartaoPrincipal implements Cartao {
	private int idcartao;
	private String numeroCartao;
	private String cvv;
	private String dataEmissao;
	private String dataValidade;
	private double limite;
	private double saldo;
	private int idconta;
	private boolean principal;
	private boolean dependente;

	public CartaoPrincipal(int idconta, boolean principal, boolean dependente) {
		this.idconta = idconta;
		this.principal = principal;
		this.dependente = dependente;
	}

	public int getIdcartao() {
		return idcartao;
	}
	public void setIdcartao(int idcartao) {
		this.idcartao = idcartao;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(String dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public String getDataValidade() {
		return dataValidade;
	}
	public void setDataValidade(String dataValidade) {
		this.dataValidade = dataValidade;
	}

	public double getLimite() {
		BigDecimal bd = new BigDecimal(limite).setScale(2, RoundingMode.HALF_EVEN);
		return bd.doubleValue();
	}
	public void setLimite(double limite) {
		this.limite = limite;
	}
	public double defineLimite(double salario) {
		limite = salario * 0.7;
		BigDecimal bd = new BigDecimal(limite).setScale(2, RoundingMode.HALF_EVEN);
		return bd.doubleValue();
	}

	public double getSaldo() {
		BigDecimal bd = new BigDecimal(saldo).setScale(2, RoundingMode.HALF_EVEN);
		return bd.doubleValue();
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public int getIdconta() {
		return idconta;
	}
	public void setIdconta(int idconta) {
		this.idconta = idconta;
	}

	public boolean isPrincipal() {
		return principal;
	}
	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}

	public boolean isDependente() {
		return dependente;
	}
	public void setDependente(boolean dependente) {
		this.dependente = dependente;
	}

	@Override
	public String geraNumero() {
		String codBanco = "6279";
		String numCartao = codBanco + "";
		
		for (int i = 0; i < 12; i++) {
			numCartao += new Random().nextInt(10);
		}
		
		return numCartao;
	}

	@Override
	public String geraCVV() {
		String cvv = "";
		
		for (int i = 0; i < 3; i++) {
			cvv += new Random().nextInt(10);
		}
		
		return cvv;
	}

	@Override
	public String defineDataEmissao() {
		Calendar dataAtual = GregorianCalendar.getInstance();
		int anoAtual = dataAtual.get(Calendar.YEAR);
		int mesAtual = dataAtual.get(Calendar.MONTH);
		int diaAtual = dataAtual.get(Calendar.DAY_OF_MONTH);
		
		String dataEmissao = anoAtual + "-" + mesAtual + "-" + diaAtual;
		
		return dataEmissao;
	}

	@Override
	public String defineDataValidade() {
		Calendar dataAtual = GregorianCalendar.getInstance();
		int anoAtual = dataAtual.get(Calendar.YEAR);
		int ano = anoAtual + 1;
		int mesAtual = dataAtual.get(Calendar.MONTH);
		int diaAtual = dataAtual.get(Calendar.DAY_OF_MONTH);
		
		String dataValidade = ano + "-" + mesAtual + "-" + diaAtual;
		
		return dataValidade;
	}
	
}
