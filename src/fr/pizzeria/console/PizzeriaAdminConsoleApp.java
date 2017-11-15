package fr.pizzeria.console;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoFilePersistence;
import fr.pizzeria.ihm.Menu;
import fr.pizzeria.swing.MyApplication;

/** 
 * The PizzeriaAdminConsoleApp is the entry point of the programm.
 * It implements an application used to manage pizzas in a pizzeria 
 * via the console.
 * 
 * @author ETY0002
 *
 */
public class PizzeriaAdminConsoleApp {

	/**
	 * Méthode main
	 * 
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// ihmMode();
		consoleMode();
	}

	private static void consoleMode() throws Exception {
		IPizzaDao daoClass = new PizzaDaoFilePersistence();
		// creation du menu principal
		Menu menu = new Menu(daoClass);
		// Commencer à gérer les entrées utilisaeurs
		menu.afficher();
	}

	private static void ihmMode() {
		new MyApplication();
	}

}
