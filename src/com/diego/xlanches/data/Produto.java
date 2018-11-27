package com.diego.xlanches.data;

public class Produto {

	private String nome;
	private String descricao;
	private double valor;
	
	public Produto() {
		this.nome = "";
		this.descricao = "";
		this.valor = 0;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}	
	
}
