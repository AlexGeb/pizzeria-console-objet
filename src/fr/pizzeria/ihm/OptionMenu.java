package fr.pizzeria.ihm;

import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.exception.UnvalidCodeException;
import fr.pizzeria.exception.UnvalidNameException;
import fr.pizzeria.exception.UnvalidPriceException;

public abstract class OptionMenu {
	protected PizzaDaoImpl pizzeria;

	/**
	 * libelle is the text of the option
	 */
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

	protected final void checkPizzaCode(String pizzaCode) throws UnvalidCodeException {
		if (pizzeria.getPizzaIndexByCode(pizzaCode) < 0) {
			throw new UnvalidCodeException("La pizza '" + pizzaCode + "' est inconnue");
		}
	}

	protected final double checkPizzaPrice(String pizzaPrice) throws UnvalidPriceException {
		double price = 0;
		try {
			price = new Double(pizzaPrice);
		} catch (NumberFormatException e) {
			throw new UnvalidPriceException("not a number");
		}
		return price;
	}

	protected final void checkPizzaName(String pizzaName) throws UnvalidNameException {
		if (pizzaName.contains(" "))
			throw new UnvalidNameException(pizzaName + " n'est pas un nom valide pour une pizza");
	}
}
