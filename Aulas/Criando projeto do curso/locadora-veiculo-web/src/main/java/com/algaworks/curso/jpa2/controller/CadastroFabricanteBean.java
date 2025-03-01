package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.modelo.Fabricante;
import com.algaworks.curso.jpa2.service.CadastroFabricanteService;
import com.algaworks.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroFabricanteBean implements Serializable{
	@Inject
	private CadastroFabricanteService cadastroFabricanteService;
	private Fabricante fabricante;
	
	@PostConstruct
	public void init() {
		this.limpar();
	}
	
	public void limpar() {
		this.fabricante = new Fabricante();
	}
	
	public void salvar() {
		try {
			this.cadastroFabricanteService.salvar(this.fabricante);
			FacesUtil.addSuccessMessage("Fabricante salvo com sucesso.");
		} catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public void novo() {
		limpar();
	}

	public CadastroFabricanteService getCadastroFabricanteService() {
		return cadastroFabricanteService;
	}

	public void setCadastroFabricanteService(CadastroFabricanteService cadastroFabricanteService) {
		this.cadastroFabricanteService = cadastroFabricanteService;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}
}
