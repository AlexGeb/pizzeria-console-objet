package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.exception.UnvalidCodeException;
import fr.pizzeria.exception.UnvalidNameException;
import fr.pizzeria.exception.UnvalidPriceException;
import fr.pizzeria.ihm.ListerPizzasOptionMenu;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.swing.Input;
import fr.pizzeria.swing.MyApplication;

public class ModifierPizzaOptionMenu extends OptionMenu {

	private Scanner menu;

	public ModifierPizzaOptionMenu(IPizzaDao pizzeria, Scanner menu) {
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

		CategoriePizza categorie = new SelectCategoryPizzaInput(menu).execute();

		pizzeria.updatePizza(code, new Pizza(newcode, name, prix, categorie));
	}

	@Override
	public String executeForIhm(MyApplication myApplication) throws StockageException {
		String oldCode = myApplication.ask("code");
		// check if pizza exist :
		checkPizzaCode(oldCode);

		Input input = myApplication.askMultiple("code", "nom", "prix", "categorie");
		String code = input.getValue("code");
		String name = input.getValue("nom");
		checkPizzaName(name);
		double price = checkPizzaPrice(input.getValue("prix"));
		CategoriePizza cat = CategoriePizza.valueOf(input.getValue("categorie").toUpperCase());
		Pizza newPizz = new Pizza(code, name, price, cat);
		boolean bool = pizzeria.updatePizza(oldCode, newPizz);
		return bool ? "Pizza modified : \n" + newPizz : "ERROR at saving";
	}
}
