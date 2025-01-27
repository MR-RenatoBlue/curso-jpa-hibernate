package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.algaworks.curso.jpa2.lazy.LazyDataModel;
import com.algaworks.curso.jpa2.modelo.Fabricante;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class FabricanteDAO implements Serializable, LazyDataModel<Fabricante> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager em;

	public void salvar(Fabricante fabricante) {
		em.merge(fabricante);
	}

	@Transactional
	public void excluir(Fabricante fabricanteSelecionado) throws NegocioException {
		fabricanteSelecionado = em.find(Fabricante.class, fabricanteSelecionado.getCodigo());
		em.remove(fabricanteSelecionado);
		em.flush();

	}

	@SuppressWarnings("unchecked")
	public List<Fabricante> buscarTodos() {
		return em.createQuery("from Fabricante").getResultList();
	}

	public Fabricante buscarPeloCodigo(Long codigo) {
		return em.find(Fabricante.class, codigo);

	}

	public List<Fabricante> buscarComPaginacao(int first, int pageSize) {
		return em.createQuery("from Fabricante").setFirstResult(first).setMaxResults(pageSize).getResultList();
	}

	public Long encontrarQuantidade() {
		return em.createQuery("select count(f) from Fabricante f", Long.class).getSingleResult();
	}

}
