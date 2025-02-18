package com.algaworks.curso.jpa2.tiposbasicos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;

import com.algaworks.curso.jpa2.modelo.Funcionario;
import com.algaworks.curso.jpa2.modelo.Pessoa;
import com.algaworks.curso.jpa2.modelo.Sexo;
import com.algaworks.curso.jpa2.util.jpa.EntityManagerProducer;

public class ExemplosTiposBasicos {

	public static void main(String[] args) throws ParseException {
		EntityManager em =  new EntityManagerProducer().create();
		
		em.getTransaction().begin();
		
		Pessoa pessoa = new Funcionario();
		
		pessoa.setCpf("777.172.888-99");
		pessoa.setDataNascimento(new SimpleDateFormat("yyyy-MM-dd").parse("12-09-1995"));
		pessoa.setNome("marui");
		pessoa.setSexo(Sexo.MASCULINO);
		pessoa.getTelefones().add("41 99996669");
		pessoa.getTelefones().add("41 99998889");
		em.persist(pessoa);
		
		em.getTransaction().commit();
		em.close();

	}

}
