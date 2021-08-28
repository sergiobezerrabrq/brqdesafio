package com.brq.aviaoms.exception;

import com.brq.aviaoms.messages.Message;

public class NotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public NotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException() {
        super(Message.NOT_FOUND);
    }
}
