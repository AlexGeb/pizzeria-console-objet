package fr.pizzeria.ihm;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.ExitException;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.utils.StringUtils;

/**
 * Menu contains the main logic of the pizzeria application. It indefinetly
 * displays a menu with various options and manage the user inputs accordingly.
 * <h1>***** Pizzeria Administration *****</h1> 1. Lister les pizzas <br>
 * 2. Ajouter une nouvelle pizza <br>
 * 3. Modifier une pizza <br>
 * 99. Quitter <br>
 * 4. Supprimer une pizza <br>
 * 
 * @author AlexGeb
 *
 */
public class Menu {
	private final String TITRE = "***** Pizzeria Administration *****";
	public Map<Integer, OptionMenu> actions = new HashMap<Integer, OptionMenu>();
	private Scanner scanner = new Scanner(System.in);

	/**
	 * Initialisation of the Menu : 5 options
	 * @param mode 
	 */
	public Menu(IPizzaDao daoClass, Scanner mode) {
		this(daoClass);
		this.scanner = mode;
		
	}

	public Menu(IPizzaDao daoClass) {
		IPizzaDao pizzeria = daoClass;
		actions.put(1, new ListerPizzasOptionMenu(pizzeria));
		actions.put(2, new AjouterPizzaOptionMenu(pizzeria, scanner));
		actions.put(3, new ModifierPizzaOptionMenu(pizzeria, scanner));
		actions.put(4, new SupprimerPizzaOptionMenu(pizzeria, scanner));
		actions.put(99, new ExitOptionMenu(pizzeria, scanner));
	}

	/**
	 * display() simply displays the menu in the console
	 * 
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
	 * 
	 * @return nothing
	 * @throws Exception
	 */
	public void afficher() throws Exception {
		boolean condition = true;
		while (condition) {
			display();
			String choix = scanner.nextLine();
			int choixnum = 0;
			if (StringUtils.isInteger(choix.trim()) && actions.containsKey(Integer.parseInt(choix))) {
				choixnum = Integer.parseInt(choix);
			} else {
				System.out.println("'" + choix + "'" + " n'est pas une option valide");
				continue;
			}
			try {
				actions.get(choixnum).execute();
			} catch (StockageException e) {
				System.out.println("**!!! " + e.getMessage() + " !!!**");
			} catch (ExitException e) {
				System.out.println("**!!! " + e.getMessage() + " !!!**");
				condition = false;
				scanner.close();
			}
		}
	}
}
