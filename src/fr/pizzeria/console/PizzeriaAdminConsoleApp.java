package fr.pizzeria.console;

import pizzeria.Pizzeria;
import fr.pizzeria.model.Pizza;

public class PizzeriaAdminConsoleApp {

	private static final int NUM_OF_PIZZA_INIT = 8;

	public static void main(String[] args) {
		Pizzeria pizzeria = new Pizzeria(initPizzas());
		pizzeria.displayMenu();

	}

	public static Pizza[] initPizzas() {
		Pizza[] pizzas = new Pizza[NUM_OF_PIZZA_INIT];
		pizzas[0] = new Pizza("PEP", "Pépéroni", 12.5);
		pizzas[1] = new Pizza("MAR", "Margherita", 14);
		pizzas[2] = new Pizza("REIN", "La Reine", 11.5);
		pizzas[3] = new Pizza("FRO", "LA 4 fromages", 12);
		pizzas[4] = new Pizza("CAN", "La cannibale", 12.5);
		pizzas[5] = new Pizza("SAV", "La savoyarde", 13);
		pizzas[6] = new Pizza("ORI", "L'orientale", 13.5);
		pizzas[7] = new Pizza("IND", "L'indienne", 14);
		return pizzas;
	}

}
