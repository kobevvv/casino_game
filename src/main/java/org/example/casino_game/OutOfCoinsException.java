package org.example.casino_game;

public class OutOfCoinsException extends RuntimeException {
    public OutOfCoinsException(String message) {
        super(message);
    }
}
