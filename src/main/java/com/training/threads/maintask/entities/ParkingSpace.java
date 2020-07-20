package com.training.threads.maintask.entities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParkingSpace {
    private static final Logger LOGGER = LogManager.getLogger(ParkingSpace.class.getName());
    private int parkingSpaceId;

    public ParkingSpace(int parkingSpaceId) {
        super();
        this.parkingSpaceId = parkingSpaceId;
    }

    int getParkingSpaceId() {
        return parkingSpaceId;
    }

    public void setParkingSpaceId(int parkingSpaceId) {
        this.parkingSpaceId = parkingSpaceId;
    }

    void using() {
        try {
            Thread.sleep(new java.util.Random().nextInt(500));
        } catch (InterruptedException e) {
            LOGGER.warn(e.toString());
            Thread.currentThread().interrupt();
        }
    }
}