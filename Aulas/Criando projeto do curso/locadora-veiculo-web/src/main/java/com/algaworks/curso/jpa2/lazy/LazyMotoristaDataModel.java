package com.algaworks.curso.jpa2.lazy;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.algaworks.curso.jpa2.dao.MotoristaDAO;
import com.algaworks.curso.jpa2.modelo.Motorista;

public class LazyMotoristaDataModel extends LazyDataModel<Motorista> implements Serializable {

	private MotoristaDAO motoristaDAO;
	
	public LazyMotoristaDataModel(MotoristaDAO motoristaDAO) {
		this.motoristaDAO = motoristaDAO;
	}
	
	@Override
	public List<Motorista> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, String> filters) {
		List<Motorista> motoristas = motoristaDAO.buscarComPaginacao(first, pageSize);
		this.setRowCount(this.motoristaDAO.encontrarQuantidade().intValue());		
		return motoristas;
	}
}
