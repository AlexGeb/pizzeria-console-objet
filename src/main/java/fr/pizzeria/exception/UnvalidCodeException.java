package fr.pizzeria.exception;

public class UnvalidCodeException extends StockageException {

	private static final long serialVersionUID = -3041396126504631575L;

	public UnvalidCodeException() {
		// TODO Auto-generated constructor stub
	}

	public UnvalidCodeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UnvalidCodeException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public UnvalidCodeException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UnvalidCodeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
