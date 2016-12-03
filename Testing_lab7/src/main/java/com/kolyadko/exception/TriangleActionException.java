package com.kolyadko.exception;

/**
 * Created by DaryaKolyadko on 03.12.2016.
 */
public class TriangleActionException extends Exception {
    public TriangleActionException(String message) {
        super(message);
    }

    public TriangleActionException(String message, Throwable cause) {
        super(message, cause);
    }

    public TriangleActionException(Throwable cause) {
        super(cause);
    }
}