package com.algaworks.curso.jpa2.consultas;

import java.util.List;

import javax.persistence.EntityManager;

import com.algaworks.curso.jpa2.info.AluguelCarroInformacoes;
import com.algaworks.curso.jpa2.modelo.Carro;
import com.algaworks.curso.jpa2.util.jpa.EntityManagerProducer;

public class ConsultasAgregadasEmCarro {
	public static void main(String[] args) {

		EntityManager em = new EntityManagerProducer().create();

//		String jpql = "select c, count(a), max(a.valorTotal), avg(a.valorTotal), sum(a.valorTotal) from Carro c JOIN c.alugueis a group by c ";
		String jpql = "select NEW  com.algaworks.curso.jpa2.info.AluguelCarroInformacoes(c, count(a), max(a.valorTotal), avg(a.valorTotal), sum(a.valorTotal)) from Carro c JOIN c.alugueis a group by c ";
		List<AluguelCarroInformacoes> resultados = em.createQuery(jpql,AluguelCarroInformacoes.class).getResultList();

		for (AluguelCarroInformacoes info : resultados) {
			System.out.println("Modelo: " + info.getCarro().getModelo().getDescricao());
			System.out.println("Quantidade de alugueis: " + info.getTotalAlugueis());
			System.out.println("Valor máximo de aluguel: " + info.getValorMaximo());
			System.out.println("Valor Médio de aluguel:" + info.getValorMedio());
			System.out.println("Soma dos aluguéis: " + info.getSomaDosAlugueis());
			System.out.println("----------------------------------------------------------");
		}
		em.close();
	}
}
