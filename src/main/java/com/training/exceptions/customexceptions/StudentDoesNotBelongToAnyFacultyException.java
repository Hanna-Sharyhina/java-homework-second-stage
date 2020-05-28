package com.training.exceptions.customexceptions;

public class StudentDoesNotBelongToAnyFacultyException extends Exception {
    public StudentDoesNotBelongToAnyFacultyException() {
        super();
    }

    public StudentDoesNotBelongToAnyFacultyException(String message) {
        super(message);
    }

    public StudentDoesNotBelongToAnyFacultyException(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentDoesNotBelongToAnyFacultyException(Throwable cause) {
        super(cause);
    }

    @Override
    public String toString(){
        return "Студент не принадлежит ни к одному факультету! ";
    }
}
