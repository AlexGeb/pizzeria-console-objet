package fr.pizzeria.dao;

import fr.pizzeria.model.Pizza;

public class PizzaDaoImpl implements IPizzaDao {
	private Pizza[] pizzas = new Pizza[100];

	public PizzaDaoImpl() {
		_init();
	}

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
		return pizzas;
	}

	@Override
	public boolean saveNewPizza(Pizza pizza) {
		pizzas[Pizza.getNumOfPizzas() - 1] = pizza;
		return true;
	}

	@Override
	public boolean updatePizza(String codePizza, Pizza pizza) {
		Pizza.delete();
		int pizzaIndexToUpdate = getPizzaIndexByCode(codePizza);
		if (pizzaIndexToUpdate < 0) {
			return false;
		}
		pizzas[pizzaIndexToUpdate] = pizza;
		return true;
	}

	@Override
	public boolean deletePizza(String codePizza) {
		int pizzaIndexToDelete = getPizzaIndexByCode(codePizza);
		if (pizzaIndexToDelete < 0) {
			return false;
		}
		pizzas[pizzaIndexToDelete] = Pizza.delete();
		return true;
	}

	private int getPizzaIndexByCode(String code) {
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
