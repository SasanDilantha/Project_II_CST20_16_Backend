package com.pms.user_server.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String messages) {
        super(messages);
    }
}
