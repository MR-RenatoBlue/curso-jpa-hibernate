package com.algaworks.curso.jpa2.consultas;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TemporalType;

import com.algaworks.curso.jpa2.info.AluguelCarroInformacoes;
import com.algaworks.curso.jpa2.modelo.Carro;
import com.algaworks.curso.jpa2.util.jpa.EntityManagerProducer;

public class ConsultasCoMDatas {
	public static void main(String[] args) {

		EntityManager em = new EntityManagerProducer().create();
		String jpql = "select count(a) from Aluguel a where a.dataDevolucao between :inicio and :fim";

		Calendar inicioCalendar = Calendar.getInstance();
		inicioCalendar.set(2025,0, 24, 0,0);
		Date inicio = inicioCalendar.getTime();
		Calendar fimCalendar = Calendar.getInstance();
		fimCalendar.set(2025,1, 1, 0,0);
		Date fim = fimCalendar.getTime();
		List<Number> resultados = em.createQuery(jpql,Number.class)
				.setParameter("inicio", inicio, TemporalType.TIMESTAMP)
				.setParameter("fim", fim, TemporalType.TIMESTAMP)
				.getResultList();

		for (Number info : resultados) {
			System.out.println("Quantidade de alugueis devolvidos entre " + inicio + " e " + fim + "=> " + info);
		}
		em.close();
	}
}
