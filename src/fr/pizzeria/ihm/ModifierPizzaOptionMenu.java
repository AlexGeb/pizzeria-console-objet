package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.ihm.ListerPizzasOptionMenu;
import fr.pizzeria.model.Pizza;

public class ModifierPizzaOptionMenu extends OptionMenu {

	Scanner menu;

	public ModifierPizzaOptionMenu(PizzaDaoImpl pizzeria, Scanner menu) {
		super(pizzeria);
		this.setLibelle("Modifier une pizza");
		this.menu = menu;
	}

	@Override
	public void execute() {
		modifyPizza();
	}

	private void modifyPizza() {
		new ListerPizzasOptionMenu(getPizzeria()).execute();
		System.out.println("Veuillez choisir la pizza à modifier : ");
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
		
		getPizzeria().updatePizza(code, new Pizza(newcode,name,new Double(price)));
	}
	
	private Pizza getPizzaByCode(String code) {
		Pizza pizz = null;
		for (Pizza p : getPizzeria().findAllPizzas()) {
			pizz = (p.getCode().equals(code)) ? p : null;
			if (pizz != null)
				break;
		}
		return pizz;
	}

}
