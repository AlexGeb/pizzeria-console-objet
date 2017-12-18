package fr.pizzeria.dao;

import java.sql.ResultSet;
import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.pizzeria.model.Pizza;

public class PizzaDaoJpa extends DaoJpa implements IPizzaDao {

	@Override
	public List<Pizza> findAllPizzas() {
		TypedQuery<Pizza> res = getEntityManager().createQuery("select a from Pizza a", Pizza.class);
		return res.getResultList();
	}

	@Override
	public boolean saveNewPizza(Pizza pizza) {
		EntityTransaction tr = getEntityManager().getTransaction();
		tr.begin();
		getEntityManager().persist(pizza);
		tr.commit();
		return false;
	}

	@Override
	public boolean updatePizza(String codePizza, Pizza pizza) {
		return false;
	}

	@Override
	public boolean deletePizza(String codePizza) {
		return false;
	}

}
