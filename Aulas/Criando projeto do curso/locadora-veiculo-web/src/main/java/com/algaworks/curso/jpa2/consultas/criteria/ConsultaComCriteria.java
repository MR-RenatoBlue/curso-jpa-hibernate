package com.algaworks.curso.jpa2.consultas.criteria;

import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.engine.jdbc.internal.BasicFormatterImpl;
import org.hibernate.engine.spi.LoadQueryInfluencers;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.internal.SessionImpl;
import org.hibernate.loader.OuterJoinLoader;
import org.hibernate.loader.criteria.CriteriaJoinWalker;
import org.hibernate.loader.criteria.CriteriaLoader;
import org.hibernate.persister.entity.OuterJoinLoadable;

import com.algaworks.curso.jpa2.modelo.Carro;
import com.algaworks.curso.jpa2.util.jpa.EntityManagerProducer;

public class ConsultaComCriteria {
	
	public static String GenerateSQL(CriteriaImpl query) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException
    {
		CriteriaImpl c = (CriteriaImpl)query;
		SessionImpl s = (SessionImpl)c.getSession();
		SessionFactoryImplementor factory = (SessionFactoryImplementor)s.getSessionFactory();
		String[] implementors = factory.getImplementors( c.getEntityOrClassName() );
		CriteriaLoader loader = new CriteriaLoader((OuterJoinLoadable)factory.getEntityPersister(implementors[0]),
		    factory, c, implementors[0], (LoadQueryInfluencers) s.getEnabledFilters());
		Field f = OuterJoinLoader.class.getDeclaredField("sql");
		f.setAccessible(true);
		String sql = (String)f.get(loader);
		return sql;
    }

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		// TODO Auto-generated method stub
		
		EntityManager em = new EntityManagerProducer().create();
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Carro> criteriaQuery = builder.createQuery(Carro.class);
		Root<Carro> c = criteriaQuery.from(Carro.class);
		criteriaQuery.select(c);
		criteriaQuery.where(builder.like(c.<String>get("placa"), "%2%"));
		TypedQuery<Carro> query = em.createQuery(criteriaQuery);
		System.out.println(query.toString());
		
		List<Carro> carros = query.getResultList();
		
		
		
		for (Carro carro: carros) {
			System.out.println("Placa: " + carro.getPlaca());
		}
		
		
	    
		em.close();

	}

}
