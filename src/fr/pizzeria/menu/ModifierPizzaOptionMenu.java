package fr.pizzeria.menu;

import java.util.Scanner;

import fr.pizzeria.Pizzeria;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.menu.ListerPizzasOptionMenu;

public class ModifierPizzaOptionMenu extends OptionMenu {

	Scanner menu;

	public ModifierPizzaOptionMenu(Pizzeria pizzeria, Scanner menu) {
		super(pizzeria);
		this.menu = menu;
	}

	@Override
	public void execute() {
		modifyPizza();
	}

	private void modifyPizza() {
		new ListerPizzasOptionMenu(pizzeria).execute();
		System.out.println("Veuillez choisir la pizza � modifier : ");
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
	
	private Pizza getPizzaByCode(String code) {
		Pizza pizz = null;
		for (Pizza p : pizzeria.pizzas) {
			pizz = (p.code.equals(code)) ? p : null;
			if (pizz != null)
				break;
		}
		return pizz;
	}

}
