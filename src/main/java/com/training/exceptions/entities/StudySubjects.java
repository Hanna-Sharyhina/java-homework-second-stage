package com.training.exceptions.entities;

public enum StudySubjects {
    CHARMS("Заклинания"),
    HISTORY_OF_MAGIC("История магии"),
    TRANSFIGURATION("Трансфигурация"),
    DEFENCE_AGAINST_THE_DARK_ARTS("Защита от темных искусств"),
    POTIONS("Зельеварение"),
    HERBOLOGY("Травология");

    private String studySubjectsName;

    StudySubjects(String studySubjectsName) {
        this.studySubjectsName = studySubjectsName;
    }

    public String getStudySubjectsName() {
        return studySubjectsName;
    }
}
