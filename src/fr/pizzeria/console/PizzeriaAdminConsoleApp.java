package fr.pizzeria.console;

import java.util.Scanner;

import fr.pizzeria.Pizzeria;
import fr.pizzeria.menu.*;
import fr.pizzeria.model.*;

public class PizzeriaAdminConsoleApp {
	static Menu[] menus = new Menu[5];
	static Scanner menu = new Scanner(System.in);

	/**
	 * Méthode main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Initialisation de la pizzeria
		Pizzeria pizzeria = new Pizzeria();
		// Commencer à gérer les entrées utilisaeurs
		displayMenu(pizzeria);

		menu.close();
		System.out.println("**** Ciao, à plus tard ! ****");
	}

	public static void displayMenu(Pizzeria pizzeria) {
		menus[0] = new Menu("1", "Lister les pizzas", new ListerPizzasOptionMenu(pizzeria));
		menus[1] = new Menu("2", "Ajouter une nouvelle pizza", new AjouterPizzaOptionMenu(pizzeria,menu));
		menus[2] = new Menu("3", "Mettre à jour une pizza", new ModifierPizzaOptionMenu(pizzeria,menu));
		menus[3] = new Menu("4", "Supprimer une pizza", new SupprimerPizzaOptionMenu(pizzeria,menu));
		menus[4] = new Menu("99", "Sortir");
		while (true) {
			System.out.println("***** Pizzeria Administration *****");
			// affiche les différentes options :
			for (Menu m : menus)
				System.out.println(m);

			String option = menu.nextLine();

			int menuIndex = findMenuForOption(option);
			if (menuIndex >= 0 && menuIndex != 4) {
				menus[menuIndex].optionMenu.execute(); // execute the proper logic
			} else if (menuIndex == 4) {
				return;
			} else {
				System.out.println("*** option inconnue, recommencez ***");
			}
		}
	}

	public static int findMenuForOption(String option) {
		int index = -1;
		for (int i = 0; i < menus.length; i++) {
			if (menus[i].number.equals(option)) {
				index = i;
				break;
			}
		}
		return index;
	}

}
