package fr.pizzeria.console;

import java.util.Scanner;

import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.menu.*;
import fr.pizzeria.model.*;

public class PizzeriaAdminConsoleApp {

	/**
	 * Méthode main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Initialisation de la pizzeria
		PizzaDaoImpl pizzeria = new PizzaDaoImpl();
		Scanner scanner = new Scanner(System.in);
		// creation du menu principal
		Menu menu = createMenu(pizzeria, scanner);
		// Commencer à gérer les entrées utilisaeurs
		menu.afficher();
		// Si on est la, c'est qu'on veut sortir du programme :
		System.out.println("**** Ciao, à plus tard ! ****");
	}

	private static Menu createMenu(PizzaDaoImpl pizzeria, Scanner scanner) {
		OptionMenu[] actions = new OptionMenu[4];
		actions[0] = new ListerPizzasOptionMenu(pizzeria);
		actions[1] = new AjouterPizzaOptionMenu(pizzeria, scanner);
		actions[2] = new ModifierPizzaOptionMenu(pizzeria, scanner);
		actions[3] = new SupprimerPizzaOptionMenu(pizzeria, scanner);
		Menu menu = new Menu("***** Pizzeria Administration *****", actions);
		return menu;
	}
}
