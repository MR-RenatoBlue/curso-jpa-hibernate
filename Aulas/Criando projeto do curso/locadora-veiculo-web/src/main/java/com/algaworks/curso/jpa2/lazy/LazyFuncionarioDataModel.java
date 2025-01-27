package com.algaworks.curso.jpa2.lazy;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.algaworks.curso.jpa2.dao.FuncionarioDAO;
import com.algaworks.curso.jpa2.modelo.Funcionario;

public class LazyFuncionarioDataModel extends LazyDataModel<Funcionario> implements Serializable {

	private FuncionarioDAO funcionarioDAO;
	
	public LazyFuncionarioDataModel(FuncionarioDAO funcionarioDAO) {
		this.funcionarioDAO = funcionarioDAO;
	}
	
	@Override
	public List<Funcionario> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, String> filters) {
		List<Funcionario> funcionarios = funcionarioDAO.buscarComPaginacao(first, pageSize);
		this.setRowCount(funcionarioDAO.encontrarQuantidade().intValue());
		return funcionarios;
	}
	
}
