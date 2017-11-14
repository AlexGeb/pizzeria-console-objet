package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.model.Pizza;

public class AjouterPizzaOptionMenu extends OptionMenu {
	Scanner menu;

	public AjouterPizzaOptionMenu(PizzaDaoImpl pizzeria, Scanner menu) {
		super(pizzeria);
		this.setLibelle("Ajouter une nouvelle pizza");
		this.menu = menu;
	}

	@Override
	public void execute() {
		this.addPizza();
	}

	private void addPizza() {
		System.out.println("Veuillez saisir le code : ");
		String code = menu.nextLine();
		System.out.println("Veuillez saisir le nom (sans espace) : ");
		String name = menu.nextLine();
		System.out.println("Veuillez saisir le prix : ");
		String price = menu.nextLine();
		getPizzeria().saveNewPizza(new Pizza(code, name, new Double(price)));
	}

}
