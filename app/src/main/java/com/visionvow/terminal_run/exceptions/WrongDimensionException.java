package com.visionvow.terminal_run.exceptions;

/**
 * This exception should be thrown when a matrix/vector operation is attempted, but one
 * or more of the matrices/vectors has the wrong number of rows/columns
 */
public class WrongDimensionException extends RuntimeException {
    /**
     * Constructs a wrong dimension exception
     */
    public WrongDimensionException() {
        super();
    }


    /**
     * Constructs a wrong dimension exception with a message
     *
     * @param message The message
     */
    public WrongDimensionException(String message) {
        super(message);
    }
}
