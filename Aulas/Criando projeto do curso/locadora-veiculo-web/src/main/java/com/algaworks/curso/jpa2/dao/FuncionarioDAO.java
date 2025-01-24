package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.algaworks.curso.jpa2.modelo.Funcionario;
import com.algaworks.curso.jpa2.service.NegocioException;

public class FuncionarioDAO implements Serializable{

	@Inject
	private EntityManager manager;

	public List<Funcionario> buscarTodos() {
		return manager.createQuery("from Funcionario").getResultList();
	}

	public void excluir(Funcionario funcionarioSelecionado) throws NegocioException {
		funcionarioSelecionado = buscarPeloCodigo(funcionarioSelecionado.getCodigo());
		try {
			manager.remove(funcionarioSelecionado);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Funcionário não pode ser excluído");
		}

	}

	public Funcionario buscarPeloCodigo(Long codigo) {
		return manager.find(Funcionario.class, codigo);
	}

	public void salvar(Funcionario funcionario) throws NegocioException {
		try {
			manager.merge(funcionario);
		} catch (Exception e) {
			throw new NegocioException(e.getMessage());
		}
	}

}
