package com.algaworks.curso.jpa2.consultas;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.algaworks.curso.jpa2.modelo.Categoria;
import com.algaworks.curso.jpa2.util.jpa.EntityManagerProducer;

public class ConsultaModeloFiltroEmFabricanteECategoria {

	public static void main(String[] args) {

		EntityManager em = new EntityManagerProducer().create();

		Categoria[] categorias = {Categoria.HATCH_MEDIO, Categoria.SEDAN_GRANDE};

		String jpql = "select mc.descricao from ModeloCarro mc where mc.categoria in (:categories)";
		List<String> cats = Categoria.ordinaisPorNome(categorias);
		
		for (String ss : cats) {
			System.out.println(ss);
		}
		
		
		List<String> modelos = em.createNativeQuery(jpql).setParameter("categories", cats).getResultList();
		for (String modelo : modelos) {
			System.out.println(modelo);
		}
		em.close();

	}

}
