package fr.pizzeria.menu;

import fr.pizzeria.dao.PizzaDaoImpl;

public abstract class OptionMenu {
	public PizzaDaoImpl pizzeria;
	
	public OptionMenu(PizzaDaoImpl pizzeria) {
		this.pizzeria = pizzeria;
	}
	
	public abstract void execute();
}
