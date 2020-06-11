package com.training.exceptions.customexceptions;

public class UniversityHasNoFacultyException extends Exception {
    public UniversityHasNoFacultyException() {
        super();
    }

    public UniversityHasNoFacultyException(String message) {
        super(message);
    }

    public UniversityHasNoFacultyException(String message, Throwable cause) {
        super(message, cause);
    }

    public UniversityHasNoFacultyException(Throwable cause) {
        super(cause);
    }

    @Override
    public String toString() {
        return "The university has no faculties! ";
    }
}
