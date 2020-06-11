package com.training.exceptions.entities;

public enum FacultyNames {
    SLYTHERIN("Slytherin"),
    HUFFLEPUFF("Hufflepuff"),
    RAVENCLAW("Ravenclaw"),
    GRYFFINDOR("Griffindor");

    private String facultyName;

    FacultyNames(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getFacultyName() {
        return facultyName;
    }

}
