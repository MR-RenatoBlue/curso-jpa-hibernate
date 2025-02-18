package com.algaworks.curso.jpa2.criteria;

import com.algaworks.curso.jpa2.modelo.Motorista;

public class MotoristaComMaisAlugueis {

	private Long quantidadeAlugueis;
	private Motorista motorista;
	
	public MotoristaComMaisAlugueis(Long quantidadeAlugueis, Motorista motorista) {
		this.quantidadeAlugueis = quantidadeAlugueis;
		this.motorista = motorista;
	}

	public Long getQuantidadeAlugueis() {
		return quantidadeAlugueis;
	}

	public void setQuantidadeAlugueis(Long quantidadeAlugueis) {
		this.quantidadeAlugueis = quantidadeAlugueis;
	}

	public Motorista getMotorista() {
		return motorista;
	}

	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}
	
	
	
	
}
