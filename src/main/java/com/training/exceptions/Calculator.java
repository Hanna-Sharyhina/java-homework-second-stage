package com.training.exceptions;

import com.training.exceptions.customexceptions.FacultyHasNoGroupException;
import com.training.exceptions.customexceptions.GroupHasNoStudentException;
import com.training.exceptions.customexceptions.StudentHasNoGradesException;
import com.training.exceptions.customexceptions.UniversityHasNoFacultyException;
import com.training.exceptions.entities.Faculty;
import com.training.exceptions.entities.Group;
import com.training.exceptions.entities.Student;
import com.training.exceptions.entities.University;

import java.text.DecimalFormat;
import java.util.*;
import java.util.logging.Logger;

public class Calculator {
    private static SortingHat sortingHat = new SortingHat();
    protected static University university = sortingHat.createUniversity();
    private static final Logger LOGGER = Logger.getLogger(Calculator.class.getSimpleName());
    private DecimalFormat formattedDouble = new DecimalFormat("#0.00");

    public void calcRandomStudentAverageGrade() throws StudentHasNoGradesException, UniversityHasNoFacultyException, FacultyHasNoGroupException, GroupHasNoStudentException {
        Faculty randomFaculty = university.getFaculties().stream()
                .findAny()
                .orElseThrow(UniversityHasNoFacultyException::new);
        Group randomGroup = randomFaculty.getGroups().stream()
                .findAny()
                .orElseThrow(FacultyHasNoGroupException::new);
        Student randomStudent = randomGroup.getStudents().stream()
                .findAny()
                .orElseThrow(GroupHasNoStudentException::new);
        double averageGrade = randomStudent.getGrades().values().stream()
                .flatMap(Collection::stream)
                .mapToInt(p -> p).average()
                .orElseThrow(StudentHasNoGradesException::new);
        String message = "Студент " + randomStudent.getName() + " группы " + randomGroup.getName() + " курса "
                + randomFaculty.getName().getFacultyName() + " имеет средний балл: " + formattedDouble.format(averageGrade)
                + ", его оценки:\n" + randomStudent.getGrades();
        LOGGER.info(message);
    }

    public void calcAverageGradeForSpecificStudySubjectInSpecificGroupAtSpecificFaculty(Faculty userChosenFaculty, Group userChosenGroup, String userChosenSubject) throws StudentHasNoGradesException {
        double averageGrade = userChosenGroup.getStudents().stream()
                .map(Student::getGrades)
                .filter(grade -> grade.containsKey(userChosenSubject))
                .map(Map::values)
                .flatMap(Collection::stream)
                .flatMap(Collection::stream)
                .mapToInt(p -> p).average().orElseThrow(StudentHasNoGradesException::new);
        String message = "Средняя оценка в группе " + userChosenGroup.getName() + " " + userChosenFaculty.getName().getFacultyName() +
                " по предмету " + userChosenSubject + " равна: " + formattedDouble.format(averageGrade);
        LOGGER.info(message);

    }

    public void calcAverageGradeForSpecificStudySubjectAtWholeUniversity(String userChosenSubject) throws StudentHasNoGradesException {
        double averageGrade = university.getFaculties().stream()
                .map(Faculty::getGroups)
                .flatMap(Collection::stream)
                .map(Group::getStudents)
                .flatMap(Collection::stream)
                .map(Student::getGrades)
                .filter(grade -> grade.containsKey(userChosenSubject))
                .map(Map::values)
                .flatMap(Collection::stream)
                .flatMap(Collection::stream)
                .mapToInt(p -> p).average().orElseThrow(StudentHasNoGradesException::new);
        String message = "Средняя оценка по предмету '" + userChosenSubject + "' для всего университета равна: " + formattedDouble.format(averageGrade);
        LOGGER.info(message);
    }
}
