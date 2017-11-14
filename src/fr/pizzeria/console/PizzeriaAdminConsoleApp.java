package fr.pizzeria.console;

import fr.pizzeria.ihm.Menu;
import fr.pizzeria.swing.MyApplication;

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
		// creation du menu principal
		Menu menu = new Menu();
		// Commencer à gérer les entrées utilisaeurs
		menu.afficher();
	}

	private static void ihmMode() {
		new MyApplication();
	}

}
