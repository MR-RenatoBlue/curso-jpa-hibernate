package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.algaworks.curso.jpa2.lazy.LazyDataModel;
import com.algaworks.curso.jpa2.modelo.Acessorio;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class AcessorioDAO implements Serializable, LazyDataModel<Acessorio> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Acessorio buscarPeloCodigo(Long codigo) {
		return manager.find(Acessorio.class, codigo);
	}
	
	public void salvar(Acessorio fabricante) {
		manager.merge(fabricante);
	}

	public List<Acessorio> buscarTodos() {
		return manager.createQuery("from Acessorio").getResultList();
	}
	
	@Transactional
	public void excluir(Acessorio fabricante) throws NegocioException {
		fabricante = buscarPeloCodigo(fabricante.getCodigo());
		try {
			manager.remove(fabricante);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Acessorio não pode ser excluído.");
		}
	}

	public List<Acessorio> buscarComPaginacao(int first, int pageSize) {
		return manager.createQuery("from Acessorio").setFirstResult(first).setMaxResults(pageSize).getResultList();
		
	}

	public Long encontrarQuantidade() {
		return manager.createQuery("select count(a) from Acessorio a", Long.class).getSingleResult();
	}
}
