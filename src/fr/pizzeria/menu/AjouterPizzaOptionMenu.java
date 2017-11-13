package fr.pizzeria.menu;

import java.util.Arrays;
import java.util.Scanner;

import fr.pizzeria.Pizzeria;
import fr.pizzeria.model.Pizza;

public class AjouterPizzaOptionMenu  extends OptionMenu  {
	Scanner menu;
	public AjouterPizzaOptionMenu(Pizzeria pizzeria,Scanner menu) {
		super(pizzeria);
		this.menu = menu;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		this.addPizza();
	}
	
	private void addPizza() {
		System.out.println("Veuillez saisir le code : ");
		String code = menu.nextLine();
		System.out.println("Veuillez saisir le nom (sans espace) : ");
		String name = menu.nextLine();
		System.out.println("Veuillez saisir le prix : ");
		String price = menu.nextLine();
		Pizza[] newPizza = new Pizza[] { new Pizza(code, name, new Double(price)) };
		Pizza[] result = Arrays.copyOf(pizzeria.pizzas, pizzeria.pizzas.length + 1);
		System.arraycopy(newPizza, 0, result, pizzeria.pizzas.length, 1);
		pizzeria.pizzas = result;
	}

}
