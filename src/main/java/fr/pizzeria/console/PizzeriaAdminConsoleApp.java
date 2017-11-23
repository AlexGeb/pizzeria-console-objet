package fr.pizzeria.console;

import java.util.Scanner;
import java.util.ResourceBundle;

import fr.pizzeria.dao.IPizzaDao;
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
		String daoimpl = ResourceBundle.getBundle("application").getString("daoImplementation");
		IPizzaDao instanceDao = (IPizzaDao) Class.forName(daoimpl).newInstance();
		
		System.out.println("Choisissez un mode");
		System.out.println("1 => mode console");
		System.out.println("2 => mode ihm");
		Scanner mode = new Scanner(System.in);
		String choix = mode.nextLine();
		switch (choix) {
		case "1":
			consoleMode(mode,instanceDao);
			break;
		case "2":
			ihmMode(instanceDao);
			break;
		default:
			consoleMode(mode,instanceDao);
			break;
		}
	}

	private static void consoleMode(Scanner mode,IPizzaDao daoClass) throws Exception {
		// creation du menu principal
		Menu menu = new Menu(daoClass,mode);
		// Commencer à gérer les entrées utilisaeurs
		menu.afficher();
	}

	private static void ihmMode(IPizzaDao daoClass) {
		// creation du menu principal
		Menu menu = new Menu(daoClass);
		MyApplication.start(menu);
	}

}
