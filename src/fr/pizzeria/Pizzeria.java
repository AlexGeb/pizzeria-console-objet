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
	


	public void listPizzas() {
		for (Pizza p : pizzas)
			System.out.println(p);
	}

	public void addPizza() {
		System.out.println("Veuillez saisir le code : ");
		String code = menu.nextLine();
		System.out.println("Veuillez saisir le nom (sans espace) : ");
		String name = menu.nextLine();
		System.out.println("Veuillez saisir le prix : ");
		String price = menu.nextLine();
		Pizza[] newPizza = new Pizza[] { new Pizza(code, name, new Double(price)) };
		Pizza[] result = Arrays.copyOf(pizzas, pizzas.length + 1);
		System.arraycopy(newPizza, 0, result, pizzas.length, 1);
		pizzas = result;
	}

	private void modifyPizza() {
		listPizzas();
		System.out.println("Veuillez choisir la pizza à modifier : ");
		System.out.println("(99 pour abandonner)");
		String code = menu.nextLine();
		if (code.equals("99")) {
			return;
		}

		Pizza pizzaToUpdate = getPizzaByCode(code);
		if (pizzaToUpdate == null) {
			System.out.println(code + " n'est pas un code de pizza valide !!");
			modifyPizza();
			return;
		}

		System.out.println("Veuillez saisir le code : ");
		String newcode = menu.nextLine();
		System.out.println("Veuillez saisir le nom (sans espace) : ");
		String name = menu.nextLine();
		System.out.println("Veuillez saisir le prix : ");
		String price = menu.nextLine();

		pizzaToUpdate.code = newcode;
		pizzaToUpdate.name = name;
		pizzaToUpdate.price = new Double(price);
		return;
	}

	private void deletePizza() {
		listPizzas();
		System.out.println("Veuillez choisir la pizza à supprimer : ");
		System.out.println("(99 pour abandonner)");
		String code = menu.nextLine();
		if (code.equals("99")) {
			return;
		}
		int pizzaIndexToDelete = getPizzaIndexByCode(code);
		if (pizzaIndexToDelete >= 0) {
			Pizza[] result = new Pizza[pizzas.length-1];
			System.arraycopy(pizzas, 0, result, 0, pizzaIndexToDelete);
			System.arraycopy(pizzas, pizzaIndexToDelete + 1, result, pizzaIndexToDelete,
					result.length - pizzaIndexToDelete);
			pizzas = result;
		} else {
			System.out.println(code + " n'est pas un code de pizza valide !!");

			deletePizza();
			return;
		}
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

	private int getPizzaIndexByCode(String code) {
		int index = -1;
		for (Pizza p : pizzas) {
			index++;
			if (p.code.equals(code))
				break;
		}
		return index;
	}
}
