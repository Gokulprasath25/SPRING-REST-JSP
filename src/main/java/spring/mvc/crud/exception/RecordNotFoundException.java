package spring.mvc.crud.exception;

public class RecordNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4991041949381363594L;

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "No record found";
	}

}
