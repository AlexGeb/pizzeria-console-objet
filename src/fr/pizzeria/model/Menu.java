package fr.pizzeria.model;

import java.util.Scanner;

import fr.pizzeria.menu.OptionMenu;

public class Menu {
	private String titre;
	private OptionMenu[] actions;

	public Menu(String titre, OptionMenu[] actions) {
		this.titre = titre;
		this.actions = actions;
	}

	private void display() {
		System.out.println(titre);
		for (int i = 1; i < actions.length + 1; i++) {
			System.out.println(i + ". " + actions[i - 1].getLibelle());
		}
		System.out.println("99. Quitter");
	}

	public void afficher() {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			display();
			String choix = scanner.nextLine();
			if(choix.equals("99"))
				break;
			try {
				int choixnum = new Integer(choix);
				actions[choixnum - 1].execute();
			} catch (Exception e) {
				System.out.println("ERROR");
				System.out.println(e.toString());
				break;
			}
		}
		scanner.close();
	}

}
