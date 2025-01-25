package com.algaworks.curso.jpa2.consultas;

import java.util.List;

import javax.persistence.EntityManager;

import com.algaworks.curso.jpa2.modelo.Fabricante;
import com.algaworks.curso.jpa2.util.jpa.EntityManagerProducer;

public class ConsultaFabricantes {

	public static void main(String[] args) {
		EntityManager em = new EntityManagerProducer().create();

		List<String> fabricantes = em.createQuery("select f.nome from Fabricante f", String.class).getResultList();
		
		for (String fabricante : fabricantes) {
			System.out.println(fabricante);
		}
		
		em.close();
	}

}
