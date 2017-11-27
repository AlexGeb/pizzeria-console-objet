package fr.pizzeria.ihm;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.exception.UnvalidCodeException;
import fr.pizzeria.exception.UnvalidNameException;
import fr.pizzeria.exception.UnvalidPriceException;
import fr.pizzeria.swing.MyApplication;

/**
 * 
 * @author AlexGeb
 *
 */
public abstract class OptionMenu {

	protected IPizzaDao pizzeria;

	public OptionMenu(IPizzaDao pizzeria2) {
		this.pizzeria = pizzeria2;
	}

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

	/**
	 * The specific logic for each option is implemented here
	 * @return nothing
	 * @throws Exception
	 */
	public abstract void execute() throws Exception;

	public final IPizzaDao getPizzeria() {
		return pizzeria;
	}

	/**
	 * checkPizzaCode throws an UnvalidCodeException if the pizza code doesn't exist
	 * @param pizzaCode, the code of the pizza to check
	 * @throws UnvalidCodeException
	 */
	protected final void checkPizzaCode(String pizzaCode) throws UnvalidCodeException {
		if (pizzeria.getPizzaIndexByCode(pizzeria.findAllPizzas(),pizzaCode) < 0) {
			throw new UnvalidCodeException("La pizza '" + pizzaCode + "' est inconnue");
		}
	}

	/**
	 * checkPizzaPrice throws an UnvalidPriceException if the pizzaPrice given by the user is not formatable to double
	 * @param pizzaPrice
	 * @return the pizza price as a double
	 * @throws UnvalidPriceException
	 */
	protected final double checkPizzaPrice(String pizzaPrice) throws UnvalidPriceException {
		double price = 0;
		try {
			price = new Double(pizzaPrice);
			if(price<0)
				throw new UnvalidPriceException("Impossible d'avoir un prix négatif");
		} catch (NumberFormatException e) {
			throw new UnvalidPriceException("not a number");
		}
		return price;
	}

	/**
	 * checkPizzaName throws UnvalidNameException if the pizzaName given by the user contains spaces
	 * @param pizzaName
	 * @throws UnvalidNameException
	 */
	protected final void checkPizzaName(String pizzaName) throws UnvalidNameException {
		if (pizzaName.trim().contains(" "))
			throw new UnvalidNameException(pizzaName + " n'est pas un nom valide pour une pizza");
	}

	public abstract String executeForIhm(MyApplication myApplication) throws StockageException;

}
