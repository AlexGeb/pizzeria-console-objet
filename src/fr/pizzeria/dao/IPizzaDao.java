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
	
	/**
	 * @param pizzas2
	 *            ArrayList<Pizza>
	 * @param code
	 *            String unique identifier for a pizza
	 * @return the index if the pizza exist, -1 if it doesn't
	 */
	default int getPizzaIndexByCode(List<Pizza> pizzas2, String pizzaCode) {
		pizzaCode = pizzaCode.toUpperCase(); // normalization (we aren't case-sensitive)
		int index = -1;
		for (int i = 0; i < pizzas2.size(); i++) {
			if (pizzas2.get(i) != null && pizzas2.get(i).getCode().equals(pizzaCode)) {
				index = i;
				break;
			}
		}
		return index;
	};
}
