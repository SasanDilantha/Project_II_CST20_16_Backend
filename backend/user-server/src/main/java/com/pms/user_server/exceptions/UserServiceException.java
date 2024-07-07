package com.pms.user_server.exceptions;

public class UserServiceException extends RuntimeException {
    public UserServiceException(String messages) {
        super(messages);
    }
}
