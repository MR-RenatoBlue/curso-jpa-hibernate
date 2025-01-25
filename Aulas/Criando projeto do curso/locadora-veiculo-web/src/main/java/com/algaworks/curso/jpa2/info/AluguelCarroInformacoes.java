package com.algaworks.curso.jpa2.info;

import java.math.BigDecimal;

import com.algaworks.curso.jpa2.modelo.Carro;

public class AluguelCarroInformacoes {
	
	private Carro carro;
	private Long totalAlugueis;
	private BigDecimal valorMaximo;
	private BigDecimal valorMedio;
	private BigDecimal somaDosAlugueis;
	
	public AluguelCarroInformacoes(Carro carro, Long totalAlugueis, Number valorMaximo, Number valorMedio,
			BigDecimal somaDosAlugueis) {
		this.carro = carro;
		this.totalAlugueis = totalAlugueis;
		this.valorMaximo = BigDecimal.valueOf(valorMaximo.doubleValue());
		this.valorMedio = BigDecimal.valueOf(valorMedio.doubleValue());
		this.somaDosAlugueis = BigDecimal.valueOf(somaDosAlugueis.doubleValue());
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public Long getTotalAlugueis() {
		return totalAlugueis;
	}

	public void setTotalAlugueis(Long totalAlugueis) {
		this.totalAlugueis = totalAlugueis;
	}

	public BigDecimal getValorMaximo() {
		return valorMaximo;
	}

	public void setValorMaximo(BigDecimal valorMaximo) {
		this.valorMaximo = valorMaximo;
	}

	public BigDecimal getValorMedio() {
		return valorMedio;
	}

	public void setValorMedio(BigDecimal valorMedio) {
		this.valorMedio = valorMedio;
	}

	public BigDecimal getSomaDosAlugueis() {
		return somaDosAlugueis;
	}

	public void setSomaDosAlugueis(BigDecimal somaDosAlugueis) {
		this.somaDosAlugueis = somaDosAlugueis;
	}

}
