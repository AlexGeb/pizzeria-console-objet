package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.exception.UnvalidNameException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.swing.Input;
import fr.pizzeria.swing.MyApplication;

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

	@Override
	public String executeForIhm(MyApplication myApplication) throws StockageException {
		Input input = myApplication.askMultiple("code","nom","prix","categorie");
		String code = input.getValue("code");
		String name = input.getValue("nom");
		checkPizzaName(name);
		double price = checkPizzaPrice(input.getValue("prix"));
		CategoriePizza cat = CategoriePizza.valueOf(input.getValue("categorie").toUpperCase());
		Pizza newPizz = new Pizza(code, name, price, cat);
		boolean bool = pizzeria.saveNewPizza(newPizz);
		return bool ? "Pizza created : \n" + newPizz : "ERROR at saving";
	}
}
