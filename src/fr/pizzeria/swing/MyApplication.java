package fr.pizzeria.swing;

public class MyApplication extends DefaultSwingApplication {

	public MyApplication() {
		start(MyApplication.class);
	}

	public void configureUI() {
		configureWindow("Title", 400, 400);
		addButton(1, "Coucou", 20, 20);
		setTextArea(20, 180);
	}

	public void initContext(ApplicationContext context) {

	}

	public void execute(int choix, ApplicationContext context) {

		println("Hello");

		/* TODO: pour interroger l'utilisateur, utilisez la méthode ask */
		String code = ask("code");
		System.out.println(code);
	}
}
