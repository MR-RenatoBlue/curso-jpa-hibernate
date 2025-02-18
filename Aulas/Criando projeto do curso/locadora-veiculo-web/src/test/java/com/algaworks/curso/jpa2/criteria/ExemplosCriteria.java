package com.algaworks.curso.jpa2.criteria;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.hibernate.criterion.BetweenExpression;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.algaworks.curso.jpa2.modelo.Aluguel;
import com.algaworks.curso.jpa2.modelo.Carro;
import com.algaworks.curso.jpa2.modelo.ModeloCarro;
import com.algaworks.curso.jpa2.modelo.Motorista;

public class ExemplosCriteria {

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
	public void projecoes() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<String> criteriaQuery = builder.createQuery(String.class);

		Root<Carro> carro = criteriaQuery.from(Carro.class);
		criteriaQuery.select(carro.<String>get("placa"));

		TypedQuery<String> query = manager.createQuery(criteriaQuery);
		List<String> placas = query.getResultList();
		for (String placa : placas) {
			System.out.println("Placa: " + placa);
		}
	}

	@Test // ALT + SHIFT + X T -- menu para executar somente o teste no cursor
	public void funcoesAgregacao() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<BigDecimal> criteriaQuery = builder.createQuery(BigDecimal.class);

		Root<Aluguel> aluguel = criteriaQuery.from(Aluguel.class);
		criteriaQuery.select(builder.sum(aluguel.<BigDecimal>get("valorTotal")));

		TypedQuery<BigDecimal> query = manager.createQuery(criteriaQuery);

		BigDecimal total = query.getSingleResult();

		System.out.println("sOMA: " + total);

	}

	@Test
	public void resultadoComplexo() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Object[]> critQ = builder.createQuery(Object[].class);

		Root<Carro> carro = critQ.from(Carro.class);
		critQ.multiselect(carro.get("placa"), carro.get("valorDiaria"));

		TypedQuery<Object[]> query = manager.createQuery(critQ);
		List<Object[]> resultado = query.getResultList();

		for (Object[] valores : resultado) {
			System.out.println("Placa: " + valores[0]);
			System.out.println("Valor da Diária: " + valores[1]);
		}
	}

	@Test
	public void resultadoTupla() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Tuple> criQ = builder.createTupleQuery();
		Root<Carro> carro = criQ.from(Carro.class);
		criQ.multiselect(carro.get("placa").alias("placa_carro"), carro.get("valorDiaria").alias("valor_diaria"));
		TypedQuery<Tuple> query = manager.createQuery(criQ);
		List<Tuple> resultado = query.getResultList();

		for (Tuple tuple : resultado) {
			System.out.println(tuple.get("placa_carro") + " - " + tuple.get("valor_diaria"));
		}
	}

	@Test
	public void resultadoConstrutores() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<PrecoCarro> criQ = builder.createQuery(PrecoCarro.class);

		Root<Carro> carro = criQ.from(Carro.class);
		criQ.select(builder.construct(PrecoCarro.class, carro.get("placa"), carro.get("valorDiaria")));

		TypedQuery<PrecoCarro> query = manager.createQuery(criQ);
		List<PrecoCarro> resultado = query.getResultList();
		for (PrecoCarro precoCarro : resultado) {
			System.out.println(precoCarro.getPlaca() + " - " + precoCarro.getValor());
		}

	}

	@Test
	public void exemploFuncao() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Carro> criteriaQuery = builder.createQuery(Carro.class);
		Root<Carro> carro = criteriaQuery.from(Carro.class);
		Predicate predicate = builder.equal(builder.upper(carro.<String>get("cor")), "azul".toUpperCase());

		criteriaQuery.select(carro);
		criteriaQuery.where(predicate);

		TypedQuery<Carro> query = manager.createQuery(criteriaQuery);
		List<Carro> carros = query.getResultList();
		for (Carro car : carros) {
			System.out.println(car.getPlaca() + " - " + car.getCor());
		}
	}

	@Test
	public void exemploOrdenacao() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Carro> criteriaQuery = builder.createQuery(Carro.class);

		Root<Carro> carro = criteriaQuery.from(Carro.class);
		Order order = builder.asc(carro.get("valorDiaria"));
		criteriaQuery.select(carro);
		criteriaQuery.orderBy(order);

		TypedQuery<Carro> query = manager.createQuery(criteriaQuery);
		List<Carro> carros = query.getResultList();
		for (Carro c : carros) {
			System.out.println(c.getPlaca() + " - " + c.getValorDiaria());
		}
	}

	@Test
	public void exemploJoinFetch() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Carro> criteriaQuery = builder.createQuery(Carro.class);

		Root<Carro> carro = criteriaQuery.from(Carro.class);
		Join<Carro, ModeloCarro> modelo = (Join) carro.fetch("modelo");

		TypedQuery<Carro> query = manager.createQuery(criteriaQuery);
		List<Carro> carros = query.getResultList();

		for (Carro c : carros) {
			System.out.println(c.getPlaca() + " - " + c.getModelo().getDescricao());
		}
	}

	@Test
	public void mediaDasDiariasDosCarros() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Double> criteriaQuery = builder.createQuery(Double.class);

		Root<Carro> carro = criteriaQuery.from(Carro.class);
		criteriaQuery.select(builder.avg(carro.<Double>get("valorDiaria")));

		TypedQuery<Double> query = manager.createQuery(criteriaQuery);
		Double total = query.getSingleResult();
		System.out.println("Média da diária: " + total);

	}

	@Test
	public void carrosComValoresAcimaDaMedia() {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Carro> criteriaQuery = builder.createQuery(Carro.class);

		Subquery<Double> subquery = criteriaQuery.subquery(Double.class);

		Root<Carro> carro = criteriaQuery.from(Carro.class);
		Root<Carro> carroSub = subquery.from(Carro.class);
		subquery.select(builder.avg(carroSub.<Double>get("valorDiaria")));

		criteriaQuery.select(carro);
		criteriaQuery.where(builder.greaterThanOrEqualTo(carro.<Double>get("valorDiaria"), subquery));
		TypedQuery<Carro> query = manager.createQuery(criteriaQuery);
		List<Carro> carros = query.getResultList();

		for (Carro c : carros) {
			System.out.println(c.getPlaca() + " - " + c.getValorDiaria());
		}

	}

	@Test
	public void exemploMetaModel() {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Carro> criteriaQuery = builder.createQuery(Carro.class);

		Root<Carro> carro = criteriaQuery.from(Carro.class);
		Join<Carro, ModeloCarro> modelo = (Join) carro.fetch("modelo");

		TypedQuery<Carro> query = manager.createQuery(criteriaQuery);
		List<Carro> carros = query.getResultList();

		for (Carro c : carros) {
			System.out.println(c.getPlaca() + " - " + c.getModelo().getDescricao());
		}
	}

	@Test
	public void motoristasQueMaisAlugaramCarrosMesPassado() {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<MotoristaComMaisAlugueis> criteriaQuery = builder.createQuery(MotoristaComMaisAlugueis.class);

		Root<Aluguel> a = criteriaQuery.from(Aluguel.class);
		Join<Aluguel, Motorista> motorista = (Join) a.join("motorista");
		criteriaQuery.select(builder.construct(MotoristaComMaisAlugueis.class, builder.count(a), motorista));
		Calendar calendar = Calendar.getInstance();
		calendar.set(2025, 0, 1);
		Date primeiroJan = calendar.getTime();
		System.out.println(primeiroJan);
		calendar.set(2025, 0, 31);
		Date ultimoJan = calendar.getTime();
System.out.println(ultimoJan);
		criteriaQuery.where(builder.between((Path<Date>)a.<Date>get("dataEntrega"), primeiroJan, ultimoJan));
		criteriaQuery.groupBy(motorista);
		TypedQuery<MotoristaComMaisAlugueis> query = manager.createQuery(criteriaQuery);
		List<MotoristaComMaisAlugueis> motoristaVsAlugueis = query.getResultList();

		for (MotoristaComMaisAlugueis ma : motoristaVsAlugueis) {
			System.out.println(ma.getQuantidadeAlugueis() + " - " + ma.getMotorista().getNome() + " - CPF: "
					+ ma.getMotorista().getCpf());
		}
	}
}
