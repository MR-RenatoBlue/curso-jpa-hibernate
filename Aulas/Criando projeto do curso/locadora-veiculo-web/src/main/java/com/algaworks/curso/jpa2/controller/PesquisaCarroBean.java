package com.algaworks.curso.jpa2.controller;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.algaworks.curso.jpa2.dao.CarroDAO;
import com.algaworks.curso.jpa2.lazy.LazyCarroDataModel;
import com.algaworks.curso.jpa2.modelo.Carro;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaCarroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	CarroDAO carroDAO;

	private List<Carro> carros = new ArrayList<>();

	private LazyCarroDataModel lazyCarros;

	private Carro carroSelecionado;
	private Carro carroSelecionadoParaExclusao;

	public List<Carro> getCarros() {
		return carros;
	}

	public void excluir() {
		try {
			carroDAO.excluir(carroSelecionadoParaExclusao);
			this.carros.remove(carroSelecionadoParaExclusao);
			FacesUtil.addSuccessMessage(
					"Carro placa " + carroSelecionadoParaExclusao.getPlaca() + " excluído com sucesso.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public Carro getCarroSelecionadoParaExclusao() {
		return carroSelecionadoParaExclusao;
	}

	public void setCarroSelecionadoParaExclusao(Carro carroSelecionadoParaExclusao) {
		this.carroSelecionadoParaExclusao = carroSelecionadoParaExclusao;
	}

	public Carro getCarroSelecionado() {
		return carroSelecionado;
	}

	public void setCarroSelecionado(Carro carroSelecionado) {
		this.carroSelecionado = carroSelecionado;
	}

	public Carro getCarroSelecionadoParaVerImagem() {
		return carroSelecionadoParaVerImagem;
	}

	public void setCarroSelecionadoParaVerImagem(Carro carroSelecionadoParaVerImagem) {
		this.carroSelecionadoParaVerImagem = carroSelecionadoParaVerImagem;
	}

	@PostConstruct
	public void inicializar() {
		// carros = carroDAO.buscarTodos();
		this.lazyCarros = new LazyCarroDataModel(this.carroDAO);
	}

	public void buscarCarroComAcessorios() {
		carroSelecionado = carroDAO.buscarCarroComAcessorios(carroSelecionado.getCodigo());
	}

	public LazyCarroDataModel getLazyCarros() {
		return lazyCarros;
	}

}
