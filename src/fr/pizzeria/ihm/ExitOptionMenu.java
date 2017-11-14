package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.exception.ExitException;

public class ExitOptionMenu extends OptionMenu {
	PizzaDaoImpl pizzeria;
	Scanner scanner;
	public ExitOptionMenu(PizzaDaoImpl pizzeria, Scanner scanner) {
		super(pizzeria);
		this.pizzeria = pizzeria;
		this.scanner = scanner;
		this.setLibelle("Quitter");
	}


	@Override
	public void execute() throws ExitException {
		throw new ExitException("Ciao, à bientôt !!!");
	}

}
