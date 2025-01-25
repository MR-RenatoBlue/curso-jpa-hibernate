package com.algaworks.curso.jpa2.consultas;

import java.util.List;

import javax.persistence.EntityManager;

import com.algaworks.curso.jpa2.util.jpa.EntityManagerProducer;

public class ConsultaModeloFiltrandoFabricante {

	public static void main(String[] args) {

		EntityManager em = new EntityManagerProducer().create();
		
		List<String> modelosCarro = em.createQuery("select mc.descricao from ModeloCarro mc where mc.fabricante.nome = 'Honda'", String.class).getResultList();
		
		for (String modeloCarro : modelosCarro) {
			System.out.println(modeloCarro);
		}
		em.close();
	}

}
