package exceptions;

public class UserAlreadyRegistered extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 13271390993780L;

	public UserAlreadyRegistered(String message) {
		super(message);
	}
}
