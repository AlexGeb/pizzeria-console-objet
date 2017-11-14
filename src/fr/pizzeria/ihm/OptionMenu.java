package fr.pizzeria.ihm;

import fr.pizzeria.dao.PizzaDaoImpl;

public abstract class OptionMenu {
	protected PizzaDaoImpl pizzeria;

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

	public abstract void execute() throws Exception;

	public final PizzaDaoImpl getPizzeria() {
		return pizzeria;
	}
	
}
