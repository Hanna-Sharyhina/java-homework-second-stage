package com.training.exceptions.customexceptions;

public class FacultyHasNoGroupException extends Exception {
    public FacultyHasNoGroupException() {
        super();
    }

    public FacultyHasNoGroupException(String message) {
        super(message);
    }

    public FacultyHasNoGroupException(String message, Throwable cause) {
        super(message, cause);
    }

    public FacultyHasNoGroupException(Throwable cause) {
        super(cause);
    }

    @Override
    public String toString() {
        return "A faculty has no groups! ";
    }
}
