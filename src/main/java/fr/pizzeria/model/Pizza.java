package fr.pizzeria.model;

import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ETY0002 Class Pizza
 */
@Entity
@Table(name = "pizza")
public class Pizza {

	private static int numOfPizzas = 0; // variable pour garder en mémoire le nombre de pizza
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	@ToString(uppercase = true, symbol = " ->")
	private String code;

	@Column
	@ToString()
	private String name;

	@Column
	@ToString(symbol = " €", surroundedBefore = "( ", surroundedAfter = " )")
	private Double price;

	@Column
	@Enumerated(EnumType.STRING)
	@ToString()
	private CategoriePizza categorie;

	private Pizza() {
		numOfPizzas++;
	}

	/**
	 * Instantiate a new Pizza
	 * 
	 * @param code
	 *            unique String object to identify the pizza
	 * @param name
	 *            String => nom de la pizza
	 * @param price
	 *            Double => prix de la pizza
	 */
	public Pizza(String code, String name, double price, CategoriePizza categorie) {
		this();
		this.code = code.toUpperCase(); // toUpperCase() pour normalizer les codes
		this.name = name;
		this.price = price;
		this.categorie = categorie;
	}

	/**
	 * 
	 * @param line,
	 *            should be on the form : "CAN -> La cannibale ( 12.5 � ) Viande"
	 */
	public Pizza(String line) {
		this();
		String delims = ";";
		String[] fields = line.trim().split(delims);
		this.code = fields[0].trim().toUpperCase();
		this.name = fields[1].trim();
		this.price = new Double(fields[2].trim());
		this.categorie = CategoriePizza.valueOf(fields[3].toUpperCase());
	}

	/**
	 * delete() has to be called each time you want to delete a pizza it is used to
	 * decrement the total number of pizzas in the pizzeria
	 * 
	 * @return null
	 */
	public static Pizza delete() {
		numOfPizzas--;
		return null;
	}

	/**
	 * @return le String correspondant à : "code;name;price;categorie"
	 */
	public String toStringForFile() {
		return String.join(";", code.toUpperCase(), name, price.toString(), categorie.toString());
	}

	/*
	 * equals
	 * 
	 * @see java.lang.Object#equals(java.lang.Object) deux pizzas sont �gales si
	 * elles ont le m�me code
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pizza other = (Pizza) obj;
		if (getCode() == null) {
			if (other.getCode() != null)
				return false;
		} else if (!getCode().equals(other.getCode()))
			return false;
		return true;
	}

	/*
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String pizzaString = "";
		for (Field attribute : this.getClass().getDeclaredFields()) {
			if (attribute.isAnnotationPresent(ToString.class)) {
				ToString tostr = (ToString) attribute.getAnnotation(ToString.class);
				try {
					String fieldValue = attribute.get(this).toString();
					if (tostr.uppercase()) {
						fieldValue = fieldValue.toUpperCase();
					}
					fieldValue = tostr.surroundedBefore() + fieldValue + tostr.symbol() + tostr.surroundedAfter();
					pizzaString += fieldValue;
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return pizzaString.trim().replace("_", " ");
	}

	/**
	 * @return an int, which is the total number of pizzas in the pizzeria
	 */
	public static int getNumOfPizzas() {
		return numOfPizzas;
	}

	/**
	 * @return the code of the pizza (unique identifier)
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the name of the pizza
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the price of the pizza
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @return the category of the pizza
	 */
	public CategoriePizza getCategory() {
		return categorie;
	}

}