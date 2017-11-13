package fr.pizzeria.menu;

import fr.pizzeria.Pizzeria;
import fr.pizzeria.model.Pizza;

public class ListerPizzasOptionMenu extends OptionMenu {

	public ListerPizzasOptionMenu(Pizzeria pizzeria) {
		super(pizzeria);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		System.out.println("*** Liste des pizzas ***");
		for (Pizza p : pizzeria.pizzas)
			System.out.println(p);
	}

}
