package spring.mvc.crud.exception;

public class RecordExistException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8481443429062936763L;

	@Override
	public String getMessage() {
		
		return "Record Already Exist For the Id";
	}

	
}
