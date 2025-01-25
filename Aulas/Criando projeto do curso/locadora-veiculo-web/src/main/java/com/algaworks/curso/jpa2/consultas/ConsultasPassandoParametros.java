package com.algaworks.curso.jpa2.consultas;

import java.util.List;

import javax.persistence.EntityManager;

import com.algaworks.curso.jpa2.info.AluguelCarroInformacoes;
import com.algaworks.curso.jpa2.modelo.Carro;
import com.algaworks.curso.jpa2.util.jpa.EntityManagerProducer;

public class ConsultasPassandoParametros {
	public static void main(String[] args) {

		EntityManager em = new EntityManagerProducer().create();
		String modelo = "Honda";
		String jpql = "select mc.descricao from ModeloCarro mc where mc.fabricante.nome = :modelo";
		List<String> resultados = em.createQuery(jpql,String.class)
				.setParameter("modelo", modelo)
				.getResultList();

		for (String info : resultados) {
			System.out.println("Modelo: " + info);
		}
		em.close();
	}
}
