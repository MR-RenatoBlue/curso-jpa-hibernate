package com.algaworks.curso.jpa2.consultas;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import com.algaworks.curso.jpa2.util.jpa.EntityManagerProducer;

public class ConsultaFabricantesPeloModeloCarro {

	public static void main(String[] args) {

		EntityManager em = new EntityManagerProducer().create();
		
		List<String> nomeFabricantes = em.createQuery("select mc.fabricante.nome from ModeloCarro mc", String.class).getResultList();
		
		for (String nomeFabricante : nomeFabricantes) {
			System.out.println(nomeFabricante);
		}
		em.close();

	}

}
