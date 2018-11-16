package exceptions;

public class VariabilityException extends Exception {
	private String message;
	public VariabilityException(String m)
	{
		super();
		message = m;
	}
	public String getMessage()
	{
		return message;
	}
}
