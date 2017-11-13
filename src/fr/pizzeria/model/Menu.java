package fr.pizzeria.model;

import fr.pizzeria.menu.OptionMenu;

public class Menu {
	public String text;
	public String number;
	public OptionMenu optionMenu;
	
	public Menu(String number, String text) {
		this.number = number;
		this.text = text;
	}
	public Menu(String number, String text,OptionMenu optionMenu) {
		this(number,text);
		this.optionMenu = optionMenu;
	}
	/* 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return number + ". " + text;
	}
}
