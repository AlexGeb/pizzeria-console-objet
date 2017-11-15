package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.ExitException;

public class ExitOptionMenu extends OptionMenu {
	IPizzaDao pizzeria;
	Scanner scanner;
	public ExitOptionMenu(IPizzaDao pizzeria2, Scanner scanner) {
		super(pizzeria2);
		this.pizzeria = pizzeria2;
		this.scanner = scanner;
		this.setLibelle("Quitter");
	}


	@Override
	public void execute() throws ExitException {
		throw new ExitException("Ciao, � bient�t");
	}

}
