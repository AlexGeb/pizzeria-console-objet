package fr.pizzeria.dao;

import java.util.List;

import fr.pizzeria.model.Pizza;

public interface IPizzaDao {
	/**
	 * @return Pizza[], all the pizzas in the pizzeria
	 */
	List<Pizza> findAllPizzas();

	/**
	 * Used to save a pizza in the pizzeria system
	 * 
	 * @param pizza
	 *            Pizza object
	 * @return true if success, false if not
	 */
	boolean saveNewPizza(Pizza pizza);

	/**
	 * @param codePizza
	 *            String unique identifier of a pizza
	 * @param pizza
	 *            Pizza object
	 * @return true if success, false if not
	 */
	boolean updatePizza(String codePizza, Pizza pizza);

	/**
	 * @param codePizza
	 *            String unique identifier of a pizza
	 * @return true if success, false if not
	 */
	boolean deletePizza(String codePizza);
}
