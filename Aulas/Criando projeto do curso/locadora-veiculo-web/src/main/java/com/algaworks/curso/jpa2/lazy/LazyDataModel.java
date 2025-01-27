package com.algaworks.curso.jpa2.lazy;

import java.util.List;

public interface LazyDataModel<T> {
	
	public List<T> buscarComPaginacao(int firstResult, int pageSize);
	
	public Long encontrarQuantidade();

}
