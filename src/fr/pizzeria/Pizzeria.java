package fr.pizzeria;

import java.util.Arrays;
import java.util.Scanner;

import fr.pizzeria.model.Pizza;

public class Pizzeria {
	public Pizza[] pizzas = new Pizza[8];;
	Scanner menu = new Scanner(System.in);

	public Pizzeria() {
		this.initPizzas();
	}

	/** Initialize the pizzeria
	 * @return Pizza[8] (tableau de pizza initial)
	 */
	private void initPizzas() {
		pizzas[0] = new Pizza("PEP", "Pépéroni", 12.5);
		pizzas[1] = new Pizza("MAR", "Margherita", 14);
		pizzas[2] = new Pizza("REIN", "La Reine", 11.5);
		pizzas[3] = new Pizza("FRO", "La 4 fromages", 12);
		pizzas[4] = new Pizza("CAN", "La cannibale", 12.5);
		pizzas[5] = new Pizza("SAV", "La savoyarde", 13);
		pizzas[6] = new Pizza("ORI", "L'orientale", 13.5);
		pizzas[7] = new Pizza("IND", "L'indienne", 14);
	}

}
