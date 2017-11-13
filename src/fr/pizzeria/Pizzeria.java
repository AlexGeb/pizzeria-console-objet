package fr.pizzeria;

import java.util.Arrays;
import java.util.Scanner;

import fr.pizzeria.model.Pizza;

public class Pizzeria {
	Pizza[] pizzas;
	Scanner menu = new Scanner(System.in);

	public Pizzeria(Pizza[] thePizzas) {
		this.pizzas = thePizzas;
	}

	public void displayMenu() {
		System.out.println("***** Pizzeria Administration *****");
		System.out.println("1. Lister les pizzas");
		System.out.println("2. Ajouter une nouvelle pizza");
		System.out.println("3. Mettre à jour une nouvelle pizza");
		System.out.println("4. Supprimer une pizza");
		System.out.println("99. Sortir");
		String option = menu.nextLine();
		switch (option) {
		case "1":
			listPizzas();
			displayMenu();
			break;
		case "2":
			addPizza();
			break;
		case "3":
			modifyPizza();
			break;
		case "4":
			if(pizzas.length>0) {
				deletePizza();
			} else {
				System.out.println("il n'y a pas de pizza");
			}
			break;
		case "99":
			break;
		default:
			break;

		}
		menu.close();
	}

	private void listPizzas() {
		for (Pizza p : pizzas)
			System.out.println(p.toString());
	}

	private void addPizza() {
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
		displayMenu();
	}

	private void modifyPizza() {
		listPizzas();
		System.out.println("Veuillez choisir la pizza à modifier : ");
		System.out.println("(99 pour abandonner)");
		String code = menu.nextLine();
		if (code.equals("99")) {
			displayMenu();
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
		displayMenu();
		return;
	}

	private void deletePizza() {
		listPizzas();
		System.out.println("Veuillez choisir la pizza à supprimer : ");
		System.out.println("(99 pour abandonner)");
		String code = menu.nextLine();
		if (code.equals("99")) {
			displayMenu();
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
		displayMenu();
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
