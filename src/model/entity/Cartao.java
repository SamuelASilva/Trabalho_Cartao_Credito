package model.entity;

public interface Cartao {
	public String geraNumero();
	public String geraCVV();
	public String defineDataEmissao();
	public String defineDataValidade();
}
