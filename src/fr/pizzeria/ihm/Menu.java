package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.exception.DeletePizzaException;

public class Menu {
	private String titre;
	private OptionMenu[] actions = new OptionMenu[100];

	public Menu() {
		titre = "***** Pizzeria Administration *****";
		PizzaDaoImpl pizzeria = new PizzaDaoImpl();
		Scanner scanner = new Scanner(System.in);
		actions[1] = new ListerPizzasOptionMenu(pizzeria);
		actions[2] = new AjouterPizzaOptionMenu(pizzeria, scanner);
		actions[3] = new ModifierPizzaOptionMenu(pizzeria, scanner);
		actions[4] = new SupprimerPizzaOptionMenu(pizzeria, scanner);
		actions[99] = new ExitOptionMenu(pizzeria, scanner);
	}

	private void display() {
		System.out.println(titre);
		for (int i = 1; i < actions.length; i++) {
			if (actions[i] != null)
				System.out.println(i + ". " + actions[i].getLibelle());
		}
	}

	public void afficher() throws Exception {
		Scanner scanner = new Scanner(System.in);
		boolean condition = true;
		while (condition) {
			display();
			String choix = scanner.nextLine();
			try {
				int choixnum = new Integer(choix);
				actions[choixnum].execute();
			} catch (Exception e) {
				String eObj = e.getClass().getSimpleName();
				System.out.println(e.getMessage());
				switch (eObj) {
				case "DeletePizzaException":
				case "SavePizzaException":
				case "UpdatePizzaException":
					break;
				case "ExitException":
					condition = false;
					break;
				default:
					scanner.close();
					throw e;
				}
			}
		}
	}
}
