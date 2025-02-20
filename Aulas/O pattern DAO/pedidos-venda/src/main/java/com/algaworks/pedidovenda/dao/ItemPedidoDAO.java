package com.algaworks.pedidovenda.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.algaworks.pedidovenda.model.Entrega;
import com.algaworks.pedidovenda.model.ItemPedido;

public interface ItemPedidoDAO extends GenericDAO<ItemPedido, Long>{

	public List<ItemPedido> buscarItensPendentes();
	
}
