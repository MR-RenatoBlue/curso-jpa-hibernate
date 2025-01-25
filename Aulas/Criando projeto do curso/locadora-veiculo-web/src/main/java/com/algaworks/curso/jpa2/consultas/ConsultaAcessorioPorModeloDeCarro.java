package com.algaworks.curso.jpa2.consultas;

import java.util.List;

import javax.persistence.EntityManager;

import com.algaworks.curso.jpa2.util.jpa.EntityManagerProducer;

public class ConsultaAcessorioPorModeloDeCarro {
	public static void main(String[] args) {

		EntityManager em = new EntityManagerProducer().create();

//		String jpql = "select a.descricao from ModeloCarro mc ,Carro c, Acessorio a where mc.descricao = 'Fit' and c.modelo = mc";
		String jpql = "select a.descricao from Carro c join c.acessorios a where c.modelo.descricao = 'Fit'";
		List<String> acessorios= em.createQuery(jpql).getResultList();

		for (String resultado : acessorios) {
			System.out.println("Acessório: " + resultado);
		}
		em.close();
	}
}
