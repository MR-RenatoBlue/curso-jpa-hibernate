package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.jws.WebParam.Mode;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.algaworks.curso.jpa2.lazy.LazyDataModel;
import com.algaworks.curso.jpa2.modelo.ModeloCarro;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class ModeloCarroDAO implements Serializable, LazyDataModel<ModeloCarro> {

	@Inject
	private EntityManager manager;

	public ModeloCarro buscarPeloCodigo(Long codigo) {
		return manager.find(ModeloCarro.class, codigo);
	}

	public void salvar(ModeloCarro modelo) {
		manager.merge(modelo);
	}

	public List<ModeloCarro> buscarTodos() {
		return manager.createQuery("from ModeloCarro").getResultList();
	}

	@Transactional
	public void excluir(ModeloCarro modelo) throws NegocioException {
		modelo = buscarPeloCodigo(modelo.getCodigo());
		try {
			manager.remove(modelo);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Este modelo não pode ser excluído");
		}
	}

	@Override
	public List<ModeloCarro> buscarComPaginacao(int firstResult, int pageSize) {
		return manager.createQuery("from ModeloCarro").setFirstResult(firstResult).setMaxResults(pageSize).getResultList();
	}

	@Override
	public Long encontrarQuantidade() {
		return manager.createQuery("select count(mc) from ModeloCarro mc", Long.class).getSingleResult() ;
	}

}
