package com.training.exceptions;

import com.training.exceptions.customexceptions.FacultyHasNoGroupException;
import com.training.exceptions.customexceptions.GroupHasNoStudentException;
import com.training.exceptions.customexceptions.StudentHasNoGradesException;
import com.training.exceptions.customexceptions.UniversityHasNoFacultyException;
import com.training.exceptions.entities.Faculty;
import com.training.exceptions.entities.Group;
import com.training.exceptions.entities.StudySubjects;

import java.util.Scanner;
import java.util.logging.Logger;

import static com.training.exceptions.Calculator.university;
import static com.training.exceptions.entities.FacultyNames.*;
import static com.training.exceptions.entities.StudySubjects.*;

public class Console {
    private static final Logger LOGGER = Logger.getLogger(Console.class.getSimpleName());
    private static final String FALSE_INPUT = "Ввод осуществлен неверно. Попробуйте повторить. ";
    private static String userChosenSubject;
    private static Faculty userChosenFaculty;
    private static Group userChosenGroup;
    private String criteria;
    private Calculator calculator = new Calculator();

    public void createCriteriaForUserChoice() {
        String menuForUserChoose = "Введите номер действия, где: \n" +
                "1 - Посчитать средний балл по всем предметам студента. \n" +
                "2 - Посчитать средний балл по конкретному предмету в конкретной группе и на конкретном факультете. \n" +
                "3 - Посчитать средний балл по предмету для всего университета. \n" +
                "4 - Выход из приложения. \n";
        LOGGER.info(menuForUserChoose);
        Scanner scan = new Scanner(System.in);
        criteria = scan.next().trim();
    }

    public void runByUserChoice() {

        while (true) {
            createCriteriaForUserChoice();
            try {
                switch (criteria) {
                    case "1":
                        calculator.calcRandomStudentAverageGrade();
                        break;
                    case "2":
                        setUserChosenGroup();
                        setUserChosenSubject();
                        calculator.calcAverageGradeForSpecificStudySubjectInSpecificGroupAtSpecificFaculty(userChosenFaculty, userChosenGroup, userChosenSubject);
                        break;
                    case "3":
                        setUserChosenSubject();
                        calculator.calcAverageGradeForSpecificStudySubjectAtWholeUniversity(userChosenSubject);
                        break;
                    case "4":
                        System.exit(0);
                        break;
                    default:
                        LOGGER.warning("Неверный ввод. Попробуйте повторить. ");
                }
            } catch (StudentHasNoGradesException | GroupHasNoStudentException | FacultyHasNoGroupException | UniversityHasNoFacultyException e) {
                LOGGER.warning(String.valueOf(e));
            }
        }
    }

    public static void setUserChosenSubject() {
        userChosenSubject = null;
        String userInput;
        Scanner scanner = new Scanner(System.in);
        LOGGER.info("Выберите один из предметов: \n" +
                HERBOLOGY.getStudySubjectsName() + ", " + POTIONS.getStudySubjectsName() + ", " +
                DEFENCE_AGAINST_THE_DARK_ARTS.getStudySubjectsName() + ", " + TRANSFIGURATION.getStudySubjectsName() + ", " +
                HISTORY_OF_MAGIC.getStudySubjectsName() + ", " + CHARMS.getStudySubjectsName());
        userInput = scanner.next().trim();
        for (StudySubjects subject : StudySubjects.values()) {
            if (subject.getStudySubjectsName().toUpperCase().startsWith((userInput).toUpperCase())) {
                userChosenSubject = subject.getStudySubjectsName();
                break;
            }
        }
        if (userChosenSubject == null) {
            LOGGER.warning(FALSE_INPUT);
            setUserChosenSubject();
        }
    }

    public static void setUserChosenFaculty() {
        userChosenFaculty = null;
        String userInput;
        Scanner scanner = new Scanner(System.in);
        LOGGER.info("Выберите факультет из предложенных :\n" +
                SLYTHERIN.getFacultyName() + ", " + GRYFFINDOR.getFacultyName() + ", " +
                RAVENCLAW.getFacultyName() + ", " + HUFFLEPUFF.getFacultyName());
        userInput = scanner.next().trim();
        for (Faculty faculty : university.getFaculties()) {
            if (userInput.equalsIgnoreCase(faculty.getName().getFacultyName())) {
                userChosenFaculty = faculty;
                break;
            }
        }
        if (userChosenFaculty == null) {
            LOGGER.warning(FALSE_INPUT);
            setUserChosenFaculty();
        }
    }

    public static void setUserChosenGroup() {
        userChosenGroup = null;
        setUserChosenFaculty();
        String userInput;
        Scanner scanner = new Scanner(System.in);
        LOGGER.info("Выберите номер группы из предложенных: \n");
        for (Group group : userChosenFaculty.getGroups()) {
            LOGGER.info(group.getName() + " группа");
        }
        userInput = scanner.next().trim();
        for (Group group : userChosenFaculty.getGroups()) {
            if (userInput.equals(group.getName())) {
                userChosenGroup = group;
            }
        }
        if (userChosenGroup == null) {
            LOGGER.warning(FALSE_INPUT);
            setUserChosenGroup();
        }
    }
}
