package exceptions;

public class LockingException extends Exception {
	private String message;
	public LockingException(String m)
	{
		super();
		message = m;
	}
	public String getMessage()
	{
		return message;
	}
}
