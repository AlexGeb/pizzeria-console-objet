package fr.pizzeria.ihm;

import java.util.List;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.swing.MyApplication;

public class ListerPizzasOptionMenu extends OptionMenu {

	public ListerPizzasOptionMenu(IPizzaDao pizzeria) {
		super(pizzeria);
		this.setLibelle("Lister les pizzas");
	}

	@Override
	public void execute() {
		List<Pizza> pizzas = pizzeria.findAllPizzas();
		System.out.println("*** Liste des pizzas *** (" + Pizza.getNumOfPizzas() +" pizzas)");
		for (Pizza p : pizzas) {
				System.out.println(p);
		}	
	}

	@Override
	public String executeForIhm(MyApplication myApplication) {
		List<Pizza> pizzas = pizzeria.findAllPizzas();
		String pizzaListString = "";
		for (Pizza p : pizzas) {
			pizzaListString+=p.toString() + "\n";
		}
		return pizzaListString;
	}
}
