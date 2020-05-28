package com.training.exceptions.customexceptions;

public class StudentHasNoGradesException extends Exception {
    public StudentHasNoGradesException() {
        super();
    }

    public StudentHasNoGradesException(String message) {
        super(message);
    }

    public StudentHasNoGradesException(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentHasNoGradesException(Throwable cause) {
        super(cause);
    }

    @Override
    public String toString() {
        return "У студента отсутствуют оценки! ";
    }
}
