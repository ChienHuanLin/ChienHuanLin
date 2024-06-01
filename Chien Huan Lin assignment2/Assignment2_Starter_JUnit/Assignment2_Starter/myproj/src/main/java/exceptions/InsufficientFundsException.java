package exceptions;

public class InsufficientFundsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InsufficientFundsException(String string) {
		super("insufficient funds! cannot withdraw this amount!");
	}
	
	
    
}
