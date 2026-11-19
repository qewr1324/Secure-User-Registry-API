package ir.maktabsharif.exception;

public class UsernameAlreadyExistsException extends RuntimeException
{
    public UsernameAlreadyExistsException(String username)
    {
        super("Username already exists: %s".formatted(username));
    }
}
