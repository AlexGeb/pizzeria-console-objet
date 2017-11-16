package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;

import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.CategoriePizza;

public class PizzaDaoImpl implements IPizzaDao {
	private List<Pizza> pizzas = new ArrayList<Pizza>(); // pre-allocate 100 places for pizzas

	public PizzaDaoImpl() {
		_init();
	}

	/**
	 * Initialize the pizzeria with 8 pizzas
	 */
	private void _init() {
		pizzas.add(new Pizza("PEP", "Pépéroni", 12.5, CategoriePizza.VIANDE));
		pizzas.add(new Pizza("MAR", "Margherita", 14, CategoriePizza.VIANDE));
		pizzas.add(new Pizza("REIN", "La Reine", 11.5, CategoriePizza.VIANDE));
		pizzas.add(new Pizza("FRO", "La 4 fromages", 12, CategoriePizza.VIANDE));
		pizzas.add(new Pizza("CAN", "La cannibale", 12.5, CategoriePizza.VIANDE));
		pizzas.add(new Pizza("SAV", "La savoyarde", 13, CategoriePizza.VIANDE));
		pizzas.add(new Pizza("ORI", "L'orientale", 13.5, CategoriePizza.VIANDE));
		pizzas.add(new Pizza("IND", "L'indienne", 14, CategoriePizza.VIANDE));
	}

	@Override
	public List<Pizza> findAllPizzas() {
		return pizzas;
	}

	@Override
	public boolean saveNewPizza(Pizza pizza) {
		pizzas.add(pizza);
		return true;
	}

	@Override
	public boolean updatePizza(String codePizza, Pizza pizza) {

		int pizzaIndexToUpdate = getPizzaIndexByCode(pizzas,codePizza);
		if (pizzaIndexToUpdate < 0)
			return false; // pizza inexistante, on sort

		// on supprime l'ancienne pizza (doit être appelé pour décrémenter le nombre
		// total de pizzas)
		Pizza.delete();
		pizzas.set(pizzaIndexToUpdate, pizza);
		return true;
	}

	@Override
	public boolean deletePizza(String codePizza) {
		int pizzaIndexToDelete = getPizzaIndexByCode(pizzas,codePizza);
		if (pizzaIndexToDelete < 0)
			return false;
		// on supprime la pizza ( Pizza.delete() doit être appelé pour décrémenter le
		// nombre total de pizzas)
		Pizza.delete();
		pizzas.remove(pizzaIndexToDelete);
		return true;
	}
}
