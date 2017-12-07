package fr.pizzeria.exception;

public class ExitException extends RuntimeException {

	public ExitException() {
	}

	public ExitException(String msg) {
		super(msg);
	}
}
