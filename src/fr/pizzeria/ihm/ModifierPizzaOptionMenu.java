package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.exception.UnvalidCodeException;
import fr.pizzeria.exception.UnvalidNameException;
import fr.pizzeria.exception.UnvalidPriceException;
import fr.pizzeria.ihm.ListerPizzasOptionMenu;
import fr.pizzeria.model.Pizza;

public class ModifierPizzaOptionMenu extends OptionMenu {

	private Scanner menu;

	public ModifierPizzaOptionMenu(PizzaDaoImpl pizzeria, Scanner menu) {
		super(pizzeria);
		this.setLibelle("Modifier une pizza");
		this.menu = menu;
	}

	@Override
	public void execute() throws UnvalidCodeException, UnvalidNameException, UnvalidPriceException {
		new ListerPizzasOptionMenu(pizzeria).execute();
		System.out.println("Veuillez choisir la pizza à modifier : ");
		System.out.println("(99 pour abandonner)");
		String code = menu.nextLine();
		if (code.equals("99")) {
			return;
		}
		// check if pizza exist :
		checkPizzaCode(code);

		System.out.println("Veuillez saisir le code : ");
		String newcode = menu.nextLine();
		System.out.println("Veuillez saisir le nom (sans espace) : ");
		String name = menu.nextLine();
		// check if name is valid :
		checkPizzaName(name);
		System.out.println("Veuillez saisir le prix : ");
		String price = menu.nextLine();
		// check if the price is valid :
		double prix = checkPizzaPrice(price);
		pizzeria.updatePizza(code, new Pizza(newcode, name, prix));
	}
}
