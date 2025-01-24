package com.algaworks.curso.jpa2.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.inject.Inject;

import com.algaworks.curso.jpa2.dao.AluguelDAO;
import com.algaworks.curso.jpa2.modelo.Aluguel;
import com.algaworks.curso.jpa2.modelo.ApoliceSeguro;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class CadastroAluguelService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AluguelDAO aluguelDAO;

	@Transactional
	public void salvar(Aluguel aluguel) throws NegocioException {

		if (aluguel.getCarro() == null) {
			throw new NegocioException("O carro é obrigatório");
		}
		if (aluguel.getMotorista() == null) {
			throw new NegocioException("Motorista é obrigatório");
		}
		aluguel.setDataPedido(Calendar.getInstance());
		this.aluguelDAO.salvar(aluguel);
	}

	public BigDecimal calcularValorTotalFranquia(BigDecimal valorFranquia, Boolean condicao) {
		if (condicao) {
			valorFranquia = valorFranquia.add(new BigDecimal(500));
		} else {
			valorFranquia = valorFranquia.subtract(new BigDecimal(500));
		}
		return valorFranquia;
	}

	public ApoliceSeguro atualizarValorFranquiaPorProtecaoTerceiro(ApoliceSeguro apolice) {
		BigDecimal valorAtual = apolice.getValorFranquia();
		
		BigDecimal valorNovo = calcularValorTotalFranquia(valorAtual, apolice.getProtecaoTerceiro());
		
		apolice.setValorFranquia(valorNovo);
		
		return apolice;
	}

	public ApoliceSeguro atualizarValorFranquiaPorProtecaoCausasNaturais(ApoliceSeguro apolice) {
		BigDecimal valorAtual = apolice.getValorFranquia();
		
		BigDecimal valorNovo = calcularValorTotalFranquia(valorAtual, apolice.getProtecaoCausasNaturais());
		
		apolice.setValorFranquia(valorNovo);

		return apolice;

	}

	public ApoliceSeguro atualizarValorFranquiaPorProtecaoRoubo(ApoliceSeguro apolice) {
		BigDecimal valorAtual = apolice.getValorFranquia();
		
		BigDecimal valorNovo = calcularValorTotalFranquia(valorAtual, apolice.getProtecaoRoubo());
		
		apolice.setValorFranquia(valorNovo);

		return apolice;

	}

}
