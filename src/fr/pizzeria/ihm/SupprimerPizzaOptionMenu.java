package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.UnvalidCodeException;
import fr.pizzeria.ihm.ListerPizzasOptionMenu;
import fr.pizzeria.swing.MyApplication;

public class SupprimerPizzaOptionMenu extends OptionMenu {
	Scanner menu;

	public SupprimerPizzaOptionMenu(IPizzaDao pizzeria, Scanner menu) {
		super(pizzeria);
		this.setLibelle("Supprimer une pizza");
		this.menu = menu;
	}

	@Override
	public void execute() throws UnvalidCodeException {
		new ListerPizzasOptionMenu(getPizzeria()).execute();
		System.out.println("Veuillez choisir la pizza à supprimer : ");
		System.out.println("(99 pour abandonner)");
		String code = menu.nextLine();
		if (code.equals("99")) {
			return;
		}
		// check if pizza exists
		checkPizzaCode(code);
		// if no errors, we can safely delete the pizza
		pizzeria.deletePizza(code);
	}

	@Override
	public String executeForIhm(MyApplication myApplication) throws UnvalidCodeException {
		String code = myApplication.ask("code");
		// check if pizza exists
		checkPizzaCode(code);
		// if no errors, we can safely delete the pizza
		return pizzeria.deletePizza(code)?"Pizza supprimée avec succés":"Error at deleting pizza";
	}
}
