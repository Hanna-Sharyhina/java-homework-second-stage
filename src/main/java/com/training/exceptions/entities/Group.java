package com.training.exceptions.entities;

import java.util.List;
import java.util.Objects;

public class Group {
    private String name;
    private List<Student> students;

    public Group(String name, List<Student> students) {
        this.name = name;
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public List<Student> getStudents() {
        return students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group)) return false;

        Group group = (Group) o;

        if (!Objects.equals(name, group.name)) return false;
        return Objects.equals(students, group.students);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (students != null ? students.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name + " group" + "'\n" + students;
    }
}
