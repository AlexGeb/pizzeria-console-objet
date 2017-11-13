package fr.pizzeria.dao;

import java.util.Arrays;

import fr.pizzeria.model.Pizza;

public class PizzaDaoImpl implements IPizzaDao {
	private Pizza[] pizzas = new Pizza[8];

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
		Pizza[] result = Arrays.copyOf(pizzas, Pizza.numOfPizzas + 1);
		System.arraycopy(pizza, 0, result, Pizza.numOfPizzas, 1);
		pizzas = result;
		return true;
	}

	@Override
	public boolean updatePizza(String codePizza, Pizza pizza) {
		Pizza pizz = getPizzaByCode(codePizza);
		pizz = pizza;
		return true;
	}

	@Override
	public boolean deletePizza(String codePizza) {
		int pizzaIndexToDelete = getPizzaIndexByCode(codePizza);
		Pizza[] result = new Pizza[Pizza.numOfPizzas - 1];
		System.arraycopy(findAllPizzas(), 0, result, 0, pizzaIndexToDelete);
		System.arraycopy(findAllPizzas(), pizzaIndexToDelete + 1, result, pizzaIndexToDelete,
				result.length - pizzaIndexToDelete);
		pizzas = result;
		return true;
	}
	
	private int getPizzaIndexByCode(String code) {
		int index = -1;
		for (Pizza p : pizzas) {
			index++;
			if (p.code.equals(code))
				break;
		}
		return index;
	}
	
	private Pizza getPizzaByCode(String code) {
		Pizza pizz = null;
		for (Pizza p : pizzas) {
			pizz = (p.code.equals(code)) ? p : null;
			if (pizz != null)
				break;
		}
		return pizz;
	}

}
