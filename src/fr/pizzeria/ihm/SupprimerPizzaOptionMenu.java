package fr.pizzeria.ihm;

import java.util.Scanner;
import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.exception.UnvalidCodeException;
import fr.pizzeria.ihm.ListerPizzasOptionMenu;

public class SupprimerPizzaOptionMenu extends OptionMenu {
	Scanner menu;

	public SupprimerPizzaOptionMenu(PizzaDaoImpl pizzeria, Scanner menu) {
		super(pizzeria);
		this.setLibelle("Supprimer une pizza");
		this.menu = menu;
	}

	@Override
	public void execute() throws UnvalidCodeException {
		new ListerPizzasOptionMenu(getPizzeria()).execute();
		System.out.println("Veuillez choisir la pizza à supprimer : ");
		System.out.println("(99 pour abandonner)");
		String code = menu.nextLine();
		if (code.equals("99")) {
			return;
		}
		// check if pizza exists
		checkPizzaCode(code);
		// if no errors, we can safely delete the pizza
		pizzeria.deletePizza(code);
	}
}
