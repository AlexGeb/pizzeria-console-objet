package fr.pizzeria.menu;

import java.util.Scanner;
import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.menu.ListerPizzasOptionMenu;


public class SupprimerPizzaOptionMenu extends OptionMenu {
	Scanner menu;

	public SupprimerPizzaOptionMenu(PizzaDaoImpl pizzeria, Scanner menu) {
		super(pizzeria);
		this.menu = menu;
	}

	@Override
	public void execute() {
		deletePizza();
	}

	private void deletePizza() {
		new ListerPizzasOptionMenu(pizzeria).execute();
		System.out.println("Veuillez choisir la pizza à supprimer : ");
		System.out.println("(99 pour abandonner)");
		String code = menu.nextLine();
		if (code.equals("99")) {
			return;
		}
		int pizzaIndexToDelete = getPizzaIndexByCode(code);
		if (pizzaIndexToDelete >= 0) {
			pizzeria.deletePizza(code);
		} else {
			System.out.println(code + " n'est pas un code de pizza valide !!");
			deletePizza();
			return;
		}
	}

	private int getPizzaIndexByCode(String code) {
		int index = -1;
		for (Pizza p : pizzeria.findAllPizzas()) {
			index++;
			if (p.code.equals(code))
				break;
		}
		return index;
	}
}
