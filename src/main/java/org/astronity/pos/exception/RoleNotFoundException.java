package org.astronity.pos.exception;

public class RoleNotFoundException extends RuntimeException{
    public RoleNotFoundException(String message)
    {
        super(message);
    }
}
