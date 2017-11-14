package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.exception.UnvalidNameException;
import fr.pizzeria.exception.UnvalidPriceException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class AjouterPizzaOptionMenu extends OptionMenu {
	Scanner menu;

	public AjouterPizzaOptionMenu(PizzaDaoImpl pizzeria, Scanner menu) {
		super(pizzeria);
		this.setLibelle("Ajouter une nouvelle pizza");
		this.menu = menu;
	}

	@Override
	public void execute() throws UnvalidNameException, UnvalidPriceException {
		System.out.println("Veuillez saisir le code : ");
		String code = menu.nextLine();
		System.out.println("Veuillez saisir le nom (sans espace) : ");
		String name = menu.nextLine();
		checkPizzaName(name);
		System.out.println("Veuillez saisir le prix : ");
		String price = menu.nextLine();
		double prix = checkPizzaPrice(price);
		Pizza newPizz = new Pizza(code, name, prix, CategoriePizza.VIANDE);
		boolean bool = pizzeria.saveNewPizza(newPizz);
		if (bool) {
			System.out.println("Pizza created : \n" + newPizz);
		}
	}

}
