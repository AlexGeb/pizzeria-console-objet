package fr.pizzeria.ihm;

import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.model.Pizza;

public class ListerPizzasOptionMenu extends OptionMenu {

	public ListerPizzasOptionMenu(PizzaDaoImpl pizzeria) {
		super(pizzeria);
		this.setLibelle("Lister les pizzas");
	}

	@Override
	public void execute() {
		System.out.println("*** Liste des pizzas *** (" + Pizza.getNumOfPizzas() +" pizzas)");
		for (Pizza p : getPizzeria().findAllPizzas()) {
			if (p!=null)
				System.out.println(p);
		}	
	}
}
