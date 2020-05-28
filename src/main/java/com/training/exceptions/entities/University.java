package com.training.exceptions.entities;

import java.util.List;
import java.util.Objects;

public class University {
    private String name;
    private List<Faculty> faculties;

    public University(String name, List<Faculty> faculties) {
        this.name = name;
        this.faculties = faculties;
    }

    public String getName() {
        return name;
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof University)) return false;

        University that = (University) o;

        if (!Objects.equals(name, that.name)) return false;
        return Objects.equals(faculties, that.faculties);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (faculties != null ? faculties.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Университет '" + name + "'\n" +
                "Факультеты: \n" + faculties;
    }
}
