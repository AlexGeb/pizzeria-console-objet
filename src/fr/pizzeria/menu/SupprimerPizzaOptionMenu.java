package fr.pizzeria.menu;

import java.util.Scanner;

import fr.pizzeria.Pizzeria;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.menu.ListerPizzasOptionMenu;
public class SupprimerPizzaOptionMenu extends OptionMenu {
	Scanner menu;

	public SupprimerPizzaOptionMenu(Pizzeria pizzeria, Scanner menu) {
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
			Pizza[] result = new Pizza[pizzeria.pizzas.length - 1];
			System.arraycopy(pizzeria.pizzas, 0, result, 0, pizzaIndexToDelete);
			System.arraycopy(pizzeria.pizzas, pizzaIndexToDelete + 1, result, pizzaIndexToDelete,
					result.length - pizzaIndexToDelete);
			pizzeria.pizzas = result;
		} else {
			System.out.println(code + " n'est pas un code de pizza valide !!");
			deletePizza();
			return;
		}
	}

	private int getPizzaIndexByCode(String code) {
		int index = -1;
		for (Pizza p : pizzeria.pizzas) {
			index++;
			if (p.code.equals(code))
				break;
		}
		return index;
	}
}
