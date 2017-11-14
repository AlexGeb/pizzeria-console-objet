package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.exception.UpdatePizzaException;
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
	public void execute() throws UpdatePizzaException {
		modifyPizza();
	}

	private void modifyPizza() throws UpdatePizzaException {
		new ListerPizzasOptionMenu(pizzeria).execute();
		System.out.println("Veuillez choisir la pizza à modifier : ");
		System.out.println("(99 pour abandonner)");
		String code = menu.nextLine();
		if (code.equals("99")) {
			return;
		}

		int pizzaIndexToUpdate = pizzeria.getPizzaIndexByCode(code);
		if (pizzaIndexToUpdate < 0) {
			throw new UpdatePizzaException("La pizza '" + code + "' est inconnue");
		}

		System.out.println("Veuillez saisir le code : ");
		String newcode = menu.nextLine();
		System.out.println("Veuillez saisir le nom (sans espace) : ");
		String name = menu.nextLine();
		if(name.contains(" ")) {
			throw new UpdatePizzaException("'" + name + "' contient des espaces");
		}
		System.out.println("Veuillez saisir le prix : ");
		String price = menu.nextLine();
		double prix = 0;
		try {
			prix = new Double(price);
		} catch (NumberFormatException e) {
			throw new UpdatePizzaException("Le prix est invalide");
		}
		pizzeria.updatePizza(code, new Pizza(newcode,name,prix));
	}
}
