package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.model.CategoriePizza;

public class SelectCategoryPizzaInput {
	Scanner menu;

	public SelectCategoryPizzaInput(Scanner menu2) {
		this.menu = menu2;
	}

	/**
	 * Displays a menu with each categories available and invite the user to select
	 * a category for the new Pizza
	 * 
	 * @return the categorie selected
	 */
	public final CategoriePizza execute() {
		CategoriePizza[] categories = CategoriePizza.values();
		System.out.println("Veuillez selectionner une categorie de Pizza : ");
		for (int i = 0; i < categories.length; i++) {
			System.out.println(i + ". " + categories[i].getValue());
		}
		String categorie = menu.nextLine();
		int choix = 0;
		try {
			choix = new Integer(categorie);
		} catch (Exception e) {
			System.out.println("Mauvais choix, recommencez :");
			execute();
		}
		return categories[choix];
	}

}
