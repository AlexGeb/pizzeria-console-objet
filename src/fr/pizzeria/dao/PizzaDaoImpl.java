package fr.pizzeria.dao;

import fr.pizzeria.model.Pizza;

public class PizzaDaoImpl implements IPizzaDao {
	private Pizza[] pizzas = new Pizza[100]; // pre-allocate 100 places for pizzas

	public PizzaDaoImpl() {
		_init();
	}

	/**
	 * Initialize the pizzeria with 8 pizzas
	 */
	private void _init() {
		pizzas[0] = new Pizza("PEP", "Pépéroni", 12.5);
		pizzas[1] = new Pizza("MAR", "Margherita", 14);
		pizzas[2] = new Pizza("REIN", "La Reine", 11.5);
		pizzas[3] = new Pizza("FRO", "La 4 fromages", 12);
		pizzas[4] = new Pizza("CAN", "La cannibale", 12.5);
		pizzas[5] = new Pizza("SAV", "La savoyarde", 13);
		pizzas[6] = new Pizza("ORI", "L'orientale", 13.5);
		pizzas[7] = new Pizza("IND", "L'indienne", 14);
	}

	@Override
	public Pizza[] findAllPizzas() {
		// we select only the not null pizzas
		Pizza[] notNullPizzas = new Pizza[Pizza.getNumOfPizzas()];
		for (int i = 0; i < pizzas.length; i++) {
			if (pizzas[i]!=null) {
				notNullPizzas[i] = pizzas[i];
			}
		}
		return notNullPizzas;
	}

	@Override
	public boolean saveNewPizza(Pizza pizza) {
		pizzas[Pizza.getNumOfPizzas() - 1] = pizza;
		return true;
	}

	@Override
	public boolean updatePizza(String codePizza, Pizza pizza) {

		int pizzaIndexToUpdate = getPizzaIndexByCode(codePizza);
		if (pizzaIndexToUpdate < 0)
			return false; // pizza inexistante, on sort

		// on supprime l'ancienne pizza (doit être appelé pour décrémenter le nombre
		// total de pizzas)
		pizzas[pizzaIndexToUpdate] = Pizza.delete();

		// on place dans le tableau la nouvelle pizza.
		pizzas[pizzaIndexToUpdate] = pizza;
		return true;
	}

	@Override
	public boolean deletePizza(String codePizza) {
		int pizzaIndexToDelete = getPizzaIndexByCode(codePizza);
		if (pizzaIndexToDelete < 0)
			return false;

		// on supprime la pizza ( Pizza.delete() doit être appelé pour décrémenter le
		// nombre total de pizzas)
		pizzas[pizzaIndexToDelete] = Pizza.delete();
		return true;
	}

	/**
	 * @param code
	 *            String unique identifier for a pizza
	 * @return the index if the pizza exist, -1 if it doesn't
	 */
	public int getPizzaIndexByCode(String code) {
		code = code.toUpperCase(); // normalization (we aren't case-sensitive)
		int index = -1;
		for (int i = 0; i < pizzas.length; i++) {
			if (pizzas[i] != null && pizzas[i].getCode().equals(code)) {
				index = i;
				break;
			}
		}
		return index;
	}
}
