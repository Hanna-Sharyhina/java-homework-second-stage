package com.training.exceptions;

import com.training.exceptions.customexceptions.StudentHasNoGradesException;
import com.training.exceptions.entities.Faculty;
import com.training.exceptions.entities.Group;
import com.training.exceptions.entities.Student;
import com.training.exceptions.entities.University;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

public class Calculator {
    private static SortingHat sortingHat = new SortingHat();
    protected static University university = sortingHat.createUniversity();
    private static final Logger LOGGER = Logger.getLogger(Calculator.class.getSimpleName());
    private DecimalFormat formattedDouble = new DecimalFormat("#0.00");
    private Random random = new Random();

    public void calcRandomStudentAverageGrade() throws StudentHasNoGradesException {
        Faculty randomFaculty = university.getFaculties().get(random.nextInt(university.getFaculties().size()));
        Group randomGroup = randomFaculty.getGroups().get(random.nextInt(randomFaculty.getGroups().size()));
        Student randomStudent = randomGroup.getStudents().get(random.nextInt(randomGroup.getStudents().size()));
        double averageGrade = randomStudent.getGrades().values().stream()
                .flatMap(Collection::stream)
                .mapToInt(p -> p).average()
                .orElseThrow(StudentHasNoGradesException::new);
        String message = "The group " + randomGroup.getName() + " " + randomFaculty.getName().getFacultyName() +
                " student " + randomStudent.getName() + " has average grade: " + formattedDouble.format(averageGrade) +
                ", his grades:\n" + randomStudent.getGrades();
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
        String message = "Average grade in group " + userChosenGroup.getName() + " " + userChosenFaculty.getName().getFacultyName() +
                " for " + userChosenSubject + " study subject is: " + formattedDouble.format(averageGrade);
        LOGGER.info(message);

    }

    public void calcAverageGradeForSpecificStudySubjectForWholeUniversity(String userChosenSubject) throws StudentHasNoGradesException {
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
        String message = "Average grade for '" + userChosenSubject + "' study subject for whole university is: " + formattedDouble.format(averageGrade);
        LOGGER.info(message);
    }
}
