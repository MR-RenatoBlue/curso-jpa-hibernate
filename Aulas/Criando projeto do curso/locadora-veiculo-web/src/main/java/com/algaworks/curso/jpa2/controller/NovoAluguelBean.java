package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.dao.CarroDAO;
import com.algaworks.curso.jpa2.dao.MotoristaDAO;
import com.algaworks.curso.jpa2.modelo.Aluguel;
import com.algaworks.curso.jpa2.modelo.ApoliceSeguro;
import com.algaworks.curso.jpa2.modelo.Carro;
import com.algaworks.curso.jpa2.modelo.Motorista;
import com.algaworks.curso.jpa2.service.CadastroAluguelService;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class NovoAluguelBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Aluguel aluguel;
	private List<Motorista> motoristas;

	private List<Carro> carros;

	BigDecimal valorFranquiaApolice;

	ApoliceSeguro apolice;

	@Inject
	private CadastroAluguelService cadastroAluguelService;
	@Inject
	private CarroDAO carroDAO;
	@Inject
	private MotoristaDAO motoristaDAO;

	public void salvar() {
		try {
			this.cadastroAluguelService.salvar(aluguel);
			FacesUtil.addSuccessMessage("Aluguel salvo com sucesso!");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}

		this.limpar();
	}

	@PostConstruct
	public void inicializar() {
		this.limpar();

		this.carros = this.carroDAO.buscarTodos();
	}

	public void limpar() {
		this.aluguel = new Aluguel();
		this.aluguel.setApoliceSeguro(new ApoliceSeguro());
		this.apolice = this.aluguel.getApoliceSeguro();
		this.motoristas = this.motoristaDAO.buscarTodos();
	}

	public Aluguel getAluguel() {
		return aluguel;
	}

	public void setAluguel(Aluguel aluguel) {
		this.aluguel = aluguel;
	}

	public List<Carro> getCarros() {
		return carros;
	}

	public List<Motorista> getMotoristas() {
		return motoristas;
	}

	private void atualizarValorFranquiaCheckProtecaoTerceiro() {
		this.apolice = cadastroAluguelService.atualizarValorFranquiaPorProtecaoTerceiro(this.apolice);
	}

	private void atualizarValorFranquiaCheckProtecaoCausasNaturais() {
		this.apolice = cadastroAluguelService.atualizarValorFranquiaPorProtecaoCausasNaturais(this.apolice);
	}

	private void atualizarValorFranquiaCheckProtecaoRoubo() {
		this.apolice = cadastroAluguelService.atualizarValorFranquiaPorProtecaoRoubo(this.apolice);
	}

}
