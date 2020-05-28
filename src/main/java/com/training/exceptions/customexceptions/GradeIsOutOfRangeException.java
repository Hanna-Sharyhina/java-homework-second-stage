package com.training.exceptions.customexceptions;

public class GradeIsOutOfRangeException extends Exception {
    public GradeIsOutOfRangeException() {
        super();
    }

    public GradeIsOutOfRangeException(String message) {
        super(message);
    }

    public GradeIsOutOfRangeException(String message, Throwable cause) {
        super(message, cause);
    }

    public GradeIsOutOfRangeException(Throwable cause) {
        super(cause);
    }

    @Override
    public String toString() {
        return "Оценка вне допустимого диапазона! ";
    }
}