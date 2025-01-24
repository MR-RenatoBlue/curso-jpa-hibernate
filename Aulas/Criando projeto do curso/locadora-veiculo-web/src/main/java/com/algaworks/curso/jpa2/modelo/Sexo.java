package com.algaworks.curso.jpa2.modelo;

public enum Sexo {

	FEMININO("Feminino"), MASCULINO("Masculino"),;

	private String descricao;

	private Sexo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
