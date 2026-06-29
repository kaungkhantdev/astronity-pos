package org.astronity.pos.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message)
    {
        super(message);
    }
}
