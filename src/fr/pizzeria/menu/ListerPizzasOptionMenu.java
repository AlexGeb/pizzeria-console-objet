package fr.pizzeria.menu;

import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.model.Pizza;

public class ListerPizzasOptionMenu extends OptionMenu {

	public ListerPizzasOptionMenu(PizzaDaoImpl pizzeria) {
		super(pizzeria);
		this.setLibelle("Lister les pizzas");
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		System.out.println("*** Liste des pizzas ***");
		for (Pizza p : getPizzeria().findAllPizzas()) {
			if (p!=null)
				System.out.println(p);
		}
			
	}

}
