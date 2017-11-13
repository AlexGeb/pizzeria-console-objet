package fr.pizzeria.menu;

import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.model.Pizza;

public class ListerPizzasOptionMenu extends OptionMenu {

	public ListerPizzasOptionMenu(PizzaDaoImpl pizzeria) {
		super(pizzeria);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		System.out.println("*** Liste des pizzas ***");
		for (Pizza p : pizzeria.findAllPizzas())
			System.out.println(p);
	}

}
