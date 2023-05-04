package org.test.task.exception;

public class InvalidStartArgumentException extends RuntimeException {

    private static final String MESSAGE = "CLI arguments should contain path to file";

    public InvalidStartArgumentException() {
        super(MESSAGE);
    }
}
