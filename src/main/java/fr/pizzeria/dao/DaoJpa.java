package fr.pizzeria.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DaoJpa {
	private EntityManagerFactory emf = null;
	private EntityManager em = null;

	public DaoJpa() {
	}

	protected EntityManager getEntityManager() {
		if (em == null) {
			em = getEntityManagerFactory().createEntityManager();
		}
		return em;
	}

	protected EntityManagerFactory getEntityManagerFactory() {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory("pizzeria-jpa");
		}
		return emf;
	}
}
