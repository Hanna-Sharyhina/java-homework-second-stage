package com.training.threads.optionaltask.entities;

public enum PlaneModels {
    BOEING_737("Boeing 737"),
    BOEING_737_800("Boeing 737-800"),
    BOEING_747("Boeing 747"),
    AIRBUS_A320("Airbus A320"),
    AIRBUS_A330("Airbus A330"),
    EMBRAER_190("Embraer 190"),
    SUKHOI_SUPERJET_100("SU SuperJet 100"),
    BOMBARDIER_CS300("Bombardier CS300");

    private String planeModelString;

    PlaneModels(String planeModelName) {
        this.planeModelString = planeModelName;
    }

    public String getPlaneModelString() {
        return planeModelString;
    }
}
