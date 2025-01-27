package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.algaworks.curso.jpa2.lazy.LazyDataModel;
import com.algaworks.curso.jpa2.modelo.Carro;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class CarroDAO implements Serializable, LazyDataModel<Carro> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -798331008717640624L;
	@Inject
	private EntityManager manager;

	public Carro buscarPeloCodigo(Long codigo) {
		return manager.find(Carro.class, codigo);
	}

	public void salvar(Carro carro) {
		manager.merge(carro);
	}

	@SuppressWarnings("unchecked")
	public List<Carro> buscarTodos() {
		return manager.createNamedQuery("buscarTodos").getResultList();
	}

	@Transactional
	public void excluir(Carro carro) throws NegocioException {
		carro = buscarPeloCodigo(carro.getCodigo());
		try {
			manager.remove(carro);
			manager.flush();
		} catch (Exception e) {
			throw new NegocioException("Carro não pode ser excluído");
		}
	}

	public Carro buscarCarroComAcessorios(Long codigo) {
		return (Carro) manager.createQuery("select c from Carro c JOIN c.acessorios a where c.codigo = ?")
				.setParameter(1, codigo).getSingleResult();
	}

	public List<Carro> buscarComPaginacao(int first, int pageSize) {
		return manager.createNamedQuery("buscarTodos").setFirstResult(first).setMaxResults(pageSize).getResultList();

	}

	public Long encontrarQuantidade() {
		return this.manager.createQuery("select count(c) from Carro c", Long.class).getSingleResult();
	}

}
