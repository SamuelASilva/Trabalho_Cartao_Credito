package model.entity;

public class Estabelecimento {
    private String nome;
    private int idestabelecimento;

    public Estabelecimento(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

	public int getIdestabelecimento() {
		return idestabelecimento;
	}
	public void setIdestabelecimento(int idestabelecimento) {
		this.idestabelecimento = idestabelecimento;
	}
}
