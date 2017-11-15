package fr.pizzeria.ihm;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import fr.pizzeria.dao.PizzaDaoImpl;

/**
 * Menu contains the main logic of the pizzeria application. 
 * It indefinetly displays a menu with various options and manage the user inputs accordingly.
 * <h1>***** Pizzeria Administration ***** </h1>
 * 1. 	Lister les pizzas <br>
 * 2. 	Ajouter une nouvelle pizza <br>
 * 3. 	Modifier une pizza <br>
 * 99. 	Quitter <br>
 * 4. 	Supprimer une pizza <br>
 * 
 * @author AlexGeb
 *
 */
public class Menu {
	private final String TITRE = "***** Pizzeria Administration *****";
	private Map<Integer, OptionMenu> actions = new HashMap<Integer, OptionMenu>();
	private Scanner scanner = new Scanner(System.in);

	/**
	 * Initialisation of the Menu : 5 options
	 */
	public Menu() {
		PizzaDaoImpl pizzeria = new PizzaDaoImpl();
		actions.put(1, new ListerPizzasOptionMenu(pizzeria));
		actions.put(2, new AjouterPizzaOptionMenu(pizzeria, scanner));
		actions.put(3, new ModifierPizzaOptionMenu(pizzeria, scanner));
		actions.put(4, new SupprimerPizzaOptionMenu(pizzeria, scanner));
		actions.put(99, new ExitOptionMenu(pizzeria, scanner));
	}

	/**
	 * display() simply displays the menu in the console
	 * @return nothing
	 */
	private void display() {
		System.out.println(TITRE);
		for (Map.Entry<Integer, OptionMenu> pair : actions.entrySet()) {
			System.out.println(pair.getKey() + ". " + pair.getValue().getLibelle());
		}
	}

	/**
	 * afficher() starts a loop in which the user inputs are processed.
	 * @return nothing
	 * @throws Exception
	 */
	public void afficher() throws Exception {
		boolean condition = true;
		while (condition) {
			display();
			String choix = scanner.nextLine();
			try {
				int choixnum = new Integer(choix.trim());
				actions.get(choixnum).execute();
			} catch (Exception e) {
				String eObj = e.getClass().getSimpleName();
				String msg = "";
				switch (eObj) {
				case "UnvalidCodeException":
				case "UnvalidNameException":
				case "UnvalidPriceException":
					// pour les exceptions ci-dessus, on print un message et on continue la boucle
					msg = e.getMessage();
					break;
				case "ArrayIndexOutOfBoundsException":
				case "NumberFormatException":
					msg = "'" + choix + "'" + " n'est pas une option valide";
					break;
				case "ExitException":
					// si on a fini, on sort
					msg = e.getMessage();
					condition = false;
					scanner.close();
					break;
				default:
					scanner.close();
					throw e;
				}
				System.out.println("**!!! " + msg + " !!!**");
			}
		}
	}
}
