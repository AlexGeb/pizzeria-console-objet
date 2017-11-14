package fr.pizzeria.menu;

import fr.pizzeria.dao.PizzaDaoImpl;

public abstract class OptionMenu {
	private PizzaDaoImpl pizzeria;

	private String libelle;

	public final String getLibelle() {
		return libelle;
	}

	final void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public OptionMenu(PizzaDaoImpl pizzeria) {
		this.pizzeria = pizzeria;
	}

	public abstract void execute();

	public final PizzaDaoImpl getPizzeria() {
		return pizzeria;
	}
	
}
