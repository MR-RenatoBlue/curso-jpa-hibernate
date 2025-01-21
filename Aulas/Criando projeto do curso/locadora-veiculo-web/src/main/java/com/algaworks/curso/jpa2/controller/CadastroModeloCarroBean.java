package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.dao.FabricanteDAO;
import com.algaworks.curso.jpa2.modelo.Categoria;
import com.algaworks.curso.jpa2.modelo.Fabricante;
import com.algaworks.curso.jpa2.modelo.ModeloCarro;
import com.algaworks.curso.jpa2.service.CadastroModeloCarroService;
import com.algaworks.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroModeloCarroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private ModeloCarro modeloCarro;

	private List<Fabricante> fabricantes;
	
	private List<Categoria> categorias;

	@Inject
	private CadastroModeloCarroService cadastroModeloCarroService;

	@Inject
	private FabricanteDAO fabricanteDAO;

	@PostConstruct
	public void inicializar() {
		this.limpar();
		this.fabricantes = fabricanteDAO.buscarTodos();
		this.categorias = Arrays.asList(Categoria.values());
	}
	private void limpar() {
		this.modeloCarro = new ModeloCarro();
		
	}
	public void salvar() {
		try {
			this.cadastroModeloCarroService.salvar(this.modeloCarro);
			FacesUtil.addSuccessMessage("Modelo Carro salvo com sucesso");
		} catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ModeloCarro getModeloCarro() {
		return modeloCarro;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public CadastroModeloCarroService getCadastroModeloCarroService() {
		return cadastroModeloCarroService;
	}

	public FabricanteDAO getFabricanteDAO() {
		return fabricanteDAO;
	}

	public void setModeloCarro(ModeloCarro modelo) {
		this.modeloCarro = modelo;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	public void setCadastroModeloCarroService(CadastroModeloCarroService cadastroModeloCarroService) {
		this.cadastroModeloCarroService = cadastroModeloCarroService;
	}

	public void setFabricanteDAO(FabricanteDAO fabricanteDAO) {
		this.fabricanteDAO = fabricanteDAO;
	}
	
	public List<Categoria> getCategorias() {
		return categorias;
	}
}
