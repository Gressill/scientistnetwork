package services.employee;

public class DAOException extends RuntimeException
{
	
	public static final long serialVersionUID=4L;
	
	public DAOException(String message)
	{
		super(message);
	}

	public DAOException(Throwable cause)
	{
		super(cause);
	}

	public DAOException(String message, Throwable cause)
	{
		super(message, cause);
	}

}