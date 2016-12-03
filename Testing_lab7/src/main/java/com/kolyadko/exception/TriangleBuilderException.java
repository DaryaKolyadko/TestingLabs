package com.kolyadko.exception;

/**
 * Created by DaryaKolyadko on 03.12.2016.
 */
public class TriangleBuilderException extends Exception {
    public TriangleBuilderException(String message) {
        super(message);
    }

    public TriangleBuilderException(String message, Throwable cause) {
        super(message, cause);
    }

    public TriangleBuilderException(Throwable cause) {
        super(cause);
    }
}