package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class AjouterPizzaOptionMenu extends OptionMenu {
	Scanner menu;

	public AjouterPizzaOptionMenu(IPizzaDao pizzeria, Scanner menu) {
		super(pizzeria);
		this.setLibelle("Ajouter une nouvelle pizza");
		this.menu = menu;
	}

	@Override
	public void execute() throws StockageException {
		System.out.println("Veuillez saisir le code : ");
		String code = menu.nextLine();
		System.out.println("Veuillez saisir le nom (sans espace) : ");
		String name = menu.nextLine();
		checkPizzaName(name);
		System.out.println("Veuillez saisir le prix : ");
		String price = menu.nextLine();
		double prix = checkPizzaPrice(price);
		
		CategoriePizza categorie = new SelectCategoryPizzaInput(menu).execute();

		Pizza newPizz = new Pizza(code, name, prix, categorie);
		boolean bool = pizzeria.saveNewPizza(newPizz);
		if (bool) {
			System.out.println("Pizza created : \n" + newPizz);
		}
	}
}
