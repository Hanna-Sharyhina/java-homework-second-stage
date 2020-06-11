package com.training.exceptions.entities;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Student {
    private String name;
    private Map<String, List<Integer>> grades;

    public Student(String name, Map<String, List<Integer>> grades) {
        this.name = name;
        this.grades = grades;
    }

    public String getName() {
        return name;
    }

    public Map<String, List<Integer>> getGrades() {
        return grades;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;

        Student student = (Student) o;

        if (!Objects.equals(name, student.name)) return false;
        return Objects.equals(grades, student.grades);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (grades != null ? grades.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Student {" + "name = '" + name + '\'' +
                ", grades = " + grades +
                "}\n";
    }
}

