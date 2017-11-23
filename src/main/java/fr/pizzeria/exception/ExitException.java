package fr.pizzeria.exception;

public class ExitException extends Exception {

	private static final long serialVersionUID = -5452149264436774820L;

	public ExitException() {
	}

	public ExitException(String msg) {
		super(msg);
	}
}
