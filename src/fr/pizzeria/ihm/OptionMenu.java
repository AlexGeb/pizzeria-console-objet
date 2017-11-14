package fr.pizzeria.ihm;

import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.exception.ExitException;

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

	public abstract void execute() throws Exception;

	public final PizzaDaoImpl getPizzeria() {
		return pizzeria;
	}
	
}
