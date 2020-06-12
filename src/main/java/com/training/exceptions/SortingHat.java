package com.training.exceptions;

import com.training.exceptions.customexceptions.*;
import com.training.exceptions.entities.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.training.exceptions.entities.FacultyNames.*;

public class SortingHat {
    private Random random = new Random();
    private static final Logger LOGGER = Logger.getLogger(SortingHat.class.getSimpleName());
    private static List<String> allStudentNames = new ArrayList<>();

    public void getAllNamesFromFile() throws StudentDoesNotBelongToAnyFacultyException {
        try (BufferedReader reader
                     = new BufferedReader(new FileReader("src\\main\\resources\\StudentNames.txt"))) {
            reader.lines().forEach(allStudentNames::add);
        } catch (IOException e) {
            LOGGER.warning(String.valueOf(e));
        }
        if (!allStudentNames.stream()
                .allMatch(s -> Arrays.stream(FacultyNames.class.getEnumConstants()).anyMatch(enumValue -> s.contains(enumValue.toString())))) {
            throw new StudentDoesNotBelongToAnyFacultyException();
        }
    }

    private List<String> getListWithStudentNamesForRandomFaculty(FacultyNames facultyName) {
        return allStudentNames.stream()
                .filter(s -> s.contains(facultyName.toString()))
                .map(s -> s.split("/"))
                .map(strings -> strings[0])
                .collect(Collectors.toList());
    }

    public Map<String, List<Integer>> addRandomGradesToEachStudySubject() throws GradeIsOutOfRangeException, StudentHasNoStudySubjectException {
        Map<String, List<Integer>> randomGrades = new HashMap<>();
        for (StudySubjects subject : StudySubjects.values()) {
            List<Integer> randomGradesValues = new ArrayList<>();
            int numberOfGrades = random.nextInt(6) + 1;
            for (int i = 0; i < numberOfGrades; i++) {
                int randomGrade = random.nextInt(10) + 1;
                if (randomGrade <= 10 && randomGrade >= 1) {
                    randomGradesValues.add(randomGrade);
                } else throw new GradeIsOutOfRangeException();
            }
            randomGrades.put(subject.getStudySubjectsName(), randomGradesValues);
        }
        if (randomGrades.keySet().isEmpty()) {
            throw new StudentHasNoStudySubjectException();
        }
        return randomGrades;
    }

    private Group getRandomGroup(List<String> names) throws GroupHasNoStudentException {
        List<Student> randomGroup = new ArrayList<>();
        int numberOfStudents = (random.nextInt(names.size()) / 3 + 1);
        for (int i = 0; i < numberOfStudents; i++) {
            try {
                if (!names.isEmpty()) {
                    randomGroup.add(new Student(names.get(i), addRandomGradesToEachStudySubject()));
                    names.remove(i);
                }
            } catch (GradeIsOutOfRangeException | StudentHasNoStudySubjectException e) {
                LOGGER.warning(e.toString());
            }
        }
        String groupNumber = String.valueOf(random.nextInt(7) + 1);
        if (randomGroup.isEmpty()) {
            throw new GroupHasNoStudentException();
        }

        return new Group(groupNumber, randomGroup);
    }

    private Faculty getRandomFaculty(List<String> names, FacultyNames name) throws FacultyHasNoGroupException {
        List<Group> groups = new ArrayList<>();
        int numberOfGroups = random.nextInt(3) + 1;
        for (int i = 0; i < numberOfGroups; i++) {
            try {
                groups.add(getRandomGroup(names));
            } catch (GroupHasNoStudentException e) {
                LOGGER.warning(e.toString());
            }
        }
        if (groups.isEmpty()) {
            throw new FacultyHasNoGroupException();
        }
        return new Faculty(name, groups);
    }

    public List<Faculty> addFacultiesToUniversity() throws UniversityHasNoFacultyException {
        try {
            getAllNamesFromFile();
        } catch (StudentDoesNotBelongToAnyFacultyException e) {
            LOGGER.warning(String.valueOf(e));
        }
        List<Faculty> allFaculties = new ArrayList<>();
        try {
            allFaculties.add(getRandomFaculty(getListWithStudentNamesForRandomFaculty(SLYTHERIN), SLYTHERIN));
            allFaculties.add(getRandomFaculty(getListWithStudentNamesForRandomFaculty(GRYFFINDOR), GRYFFINDOR));
            allFaculties.add(getRandomFaculty(getListWithStudentNamesForRandomFaculty(RAVENCLAW), RAVENCLAW));
            allFaculties.add(getRandomFaculty(getListWithStudentNamesForRandomFaculty(HUFFLEPUFF), HUFFLEPUFF));
        } catch (FacultyHasNoGroupException e) {
            LOGGER.warning(e.toString());
        }
        if (allFaculties.isEmpty()) {
            throw new UniversityHasNoFacultyException();
        }
        return allFaculties;
    }

    public University createUniversity() {
        List<Faculty> faculties = null;
        try {
            faculties = addFacultiesToUniversity();
        } catch (UniversityHasNoFacultyException e) {
            LOGGER.warning(e.toString());
        }
        return new University("Hogwarts", faculties);
    }
}
