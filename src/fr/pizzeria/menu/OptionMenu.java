package fr.pizzeria.menu;

import fr.pizzeria.Pizzeria;

public abstract class OptionMenu {
	public Pizzeria pizzeria;
	
	public OptionMenu(Pizzeria pizzeria) {
		this.pizzeria = pizzeria;
	}
	
	public abstract void execute();
}
