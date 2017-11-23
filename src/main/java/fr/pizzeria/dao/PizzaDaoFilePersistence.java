package fr.pizzeria.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.pizzeria.model.Pizza;

public class PizzaDaoFilePersistence implements IPizzaDao {
	private final String FILE_NAME = "pizza_list.txt";

	public PizzaDaoFilePersistence() {
	}

	@Override
	public List<Pizza> findAllPizzas() {
		List<Pizza> finalList = new ArrayList<Pizza>();
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(FILE_NAME);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String line;
			while ((line = bufferedReader.readLine()) != null) {
				if (line.isEmpty())
					continue;
				Pizza pizza = new Pizza(line);
				finalList.add(pizza);
			}

			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + FILE_NAME + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + FILE_NAME + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
		return finalList;
	}

	@Override
	public boolean saveNewPizza(Pizza pizza) {
		try {
			// Assume default encoding.
			FileWriter fileWriter = new FileWriter(FILE_NAME, true);

			// Always wrap FileWriter in BufferedWriter.
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			// Note that write() does not automatically
			// append a newline character.
			bufferedWriter.newLine();
			bufferedWriter.write(pizza.toStringForFile());

			// Always close files.
			bufferedWriter.close();
			return true;
		} catch (IOException ex) {
			System.out.println("Error writing to file '" + FILE_NAME + "'");
			// Or we could just do this:
			// ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updatePizza(String codePizza, Pizza pizza) {
		List<Pizza> pizzas = findAllPizzas();
		int pizzaIndexToUpdate = getPizzaIndexByCode(pizzas, codePizza);
		pizzas.set(pizzaIndexToUpdate, pizza);
		deleteAll();
		for (Pizza p : pizzas) {
			saveNewPizza(p);
		}
		return true;
	}

	@Override
	public boolean deletePizza(String codePizza) {
		List<Pizza> pizzas = findAllPizzas();
		int pizzaIndexToDelete = getPizzaIndexByCode(pizzas, codePizza);
		if (pizzaIndexToDelete >= 0) {
			pizzas.remove(pizzaIndexToDelete);
			deleteAll();
			for (Pizza p : pizzas) {
				saveNewPizza(p);
			}
			return true;
		} else {
			return false;
		}
	}

	private void deleteAll() {
		try {
			FileWriter fileWriter = new FileWriter(FILE_NAME, false);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write("");
			bufferedWriter.close();
		} catch (IOException ex) {
			System.out.println("Error deleting lines in file '" + FILE_NAME + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
	}
}
