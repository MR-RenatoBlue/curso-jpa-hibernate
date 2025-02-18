package com.algaworks.curso.jpa2.avancado;

import javax.persistence.EntityManager;

import com.algaworks.curso.jpa2.avancado.modelo.Veiculo;
import com.algaworks.curso.jpa2.avancado.modelo.VeiculoId;
import com.algaworks.curso.jpa2.util.jpa.EntityManagerProducer;

public class ExemploChaveComposta {

	public static void main(String[] args) {

		EntityManager em = new EntityManagerProducer().create();
		
		Veiculo veiculo = new Veiculo();
		veiculo.setCodigo(new VeiculoId("ABC-1234", "Almirante Tamandaré"));
		veiculo.setFabricante("Chevrolet");
		veiculo.setModelo("Corsa");
		
		em.getTransaction().begin();
		em.persist(veiculo);
		em.getTransaction().commit();
		em.close();

	}

}
