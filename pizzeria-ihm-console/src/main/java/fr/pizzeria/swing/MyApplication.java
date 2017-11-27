package fr.pizzeria.swing;

import java.util.Map;

import fr.pizzeria.ihm.Menu;
import fr.pizzeria.ihm.OptionMenu;

public class MyApplication extends DefaultSwingApplication {
	static Menu menu;
	public static void start(Menu menuPrincipal) {
		menu = menuPrincipal;
		start(MyApplication.class);
	}

	public void configureUI() {
		configureWindow("Title", 400, 600);
		int verticalPosition = 0;
		for(Map.Entry<Integer, OptionMenu> pair : menu.actions.entrySet()) {
			verticalPosition+=30;
			addButton(pair.getKey(), pair.getValue().getLibelle(), 20, verticalPosition);
		}
		setTextArea(20, verticalPosition+30);
	}

	public void initContext(ApplicationContext context) {

	}

	public void execute(int choix, ApplicationContext context) {
		try {
			String msg = menu.actions.get(choix).executeForIhm(this);
			clearScreen();
			println(msg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
