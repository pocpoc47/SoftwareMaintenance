package exceptions;

public class SearchException extends Exception {
	private String message;
	public SearchException(String m)
	{
		super();
		message = m;
	}
	public String getMessage()
	{
		return message;
	}
}
