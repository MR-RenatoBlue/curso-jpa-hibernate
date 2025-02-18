package com.algaworks.curso.jpa2.criteria;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.algaworks.curso.jpa2.modelo.Acessorio;
import com.algaworks.curso.jpa2.modelo.Carro;
import com.algaworks.curso.jpa2.modelo.Categoria;
import com.algaworks.curso.jpa2.modelo.Fabricante;
import com.algaworks.curso.jpa2.modelo.ModeloCarro;

public class EventosCascata {

	private static EntityManagerFactory factory;
	private EntityManager manager;

	@BeforeClass
	public static void init() {
		factory = Persistence.createEntityManagerFactory("locadoraVeiculoPU");
	}

	@Before
	public void setUp() {
		this.manager = factory.createEntityManager();
	}

	@After
	public void tearDown() {
		this.manager.close();
	}
	
	@Test
	public void exemploEntidadeTransient() {
		Carro carro = new Carro();
		carro.setCor("azul");
		carro.setPlaca("zzz23e");
		Fabricante fabricante = new Fabricante();
		fabricante.setNome("Ferrari");
		ModeloCarro modelo = new ModeloCarro();
		modelo.setCategoria(Categoria.ESPORTIVO);
		modelo.setDescricao("ferrari");
		modelo.setFabricante(fabricante);
		Acessorio ac1 = new Acessorio();
		ac1 = this.manager.find(Acessorio.class, 1L);
		Acessorio ac2 = new Acessorio("Novo acessório");
		Acessorio ac3 = new Acessorio("Novo acessório 2");
		
		carro.setModelo(modelo);
		carro.setAcessorios(Arrays.asList(ac1,ac2,ac3));
		this.manager.getTransaction().begin();
		this.manager.persist(carro);
		this.manager.getTransaction().commit();
	}
	
	@Test
	public void buscaFoto() throws IOException {
		Carro carro = manager.find(Carro.class, 15L);
			
		if (carro.getFoto() != null) {
			BufferedImage img = ImageIO.read(new ByteArrayInputStream(carro.getFoto()));
			JOptionPane.showMessageDialog(null, new JLabel(new ImageIcon(img)));
		} else {
			System.out.println("Carro não possui foto.");
		}
	}

}
