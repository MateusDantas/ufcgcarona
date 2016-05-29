package exceptions;

public class DatabaseError extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 13273982907237980L;

	public DatabaseError(String message) {
		super(message);
	}
}
