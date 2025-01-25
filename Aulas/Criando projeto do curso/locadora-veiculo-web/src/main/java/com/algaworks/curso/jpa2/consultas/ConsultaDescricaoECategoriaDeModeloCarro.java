package com.algaworks.curso.jpa2.consultas;

import java.util.List;

import javax.persistence.EntityManager;

import com.algaworks.curso.jpa2.util.jpa.EntityManagerProducer;

public class ConsultaDescricaoECategoriaDeModeloCarro {
	public static void main(String[] args) {

		EntityManager em = new EntityManagerProducer().create();

		String jpql = "select mc.descricao, mc.categoria from ModeloCarro mc";
		List<Object[]> resultados = em.createQuery(jpql).getResultList();
		
		for (Object[] resultado: resultados) {
			System.out.println("Descrição: " + resultado[0]);
			System.out.println("Categoria: " + resultado[1]);
		}
		em.close();
	}

}
