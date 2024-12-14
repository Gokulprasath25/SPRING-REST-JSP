package spring.mvc.crud.exception;

public class ValidInputException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6018668377189221920L;

	@Override
	public String getMessage() {
		
		return "Please Enter Valid Details";
	}
	
	

}
