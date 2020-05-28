package com.training.exceptions.customexceptions;

public class StudentHasNoStudySubjectException extends Exception {
    public StudentHasNoStudySubjectException() {
        super();
    }

    public StudentHasNoStudySubjectException(String message) {
        super(message);
    }

    public StudentHasNoStudySubjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentHasNoStudySubjectException(Throwable cause) {
        super(cause);
    }

    @Override
    public String toString() {
        return "У студента отсутствуют учебные предметы! ";
    }
}

