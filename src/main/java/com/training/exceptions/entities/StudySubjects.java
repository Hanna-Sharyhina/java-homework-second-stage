package com.training.exceptions.entities;

public enum StudySubjects {
    CHARMS("Charms"),
    HISTORY_OF_MAGIC("History of magic"),
    TRANSFIGURATION("Transfiguration"),
    DEFENCE_AGAINST_THE_DARK_ARTS("Defence against the dark arts"),
    POTIONS("Potions"),
    HERBOLOGY("Herbology");

    private String studySubjectsName;

    StudySubjects(String studySubjectsName) {
        this.studySubjectsName = studySubjectsName;
    }

    public String getStudySubjectsName() {
        return studySubjectsName;
    }
}
