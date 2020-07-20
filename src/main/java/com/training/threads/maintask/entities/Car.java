package com.training.threads.maintask.entities;

import com.training.threads.maintask.ResourceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Car implements Runnable {
    private Thread carThread;
    private Parking<ParkingSpace> parking;
    private static final Logger LOGGER = LogManager.getLogger(Car.class);

    public Car(Parking<ParkingSpace> parking) {
        carThread = new Thread(this);
        this.parking = parking;
        carThread.start();
    }

    @Override
    public void run() {
        ParkingSpace space = null;
        try {
            space = parking.getResource();
            String successfulTakingPlaceMessage = "Car № " + carThread.getId() + " took parking space № " + space.getParkingSpaceId();
            LOGGER.info(successfulTakingPlaceMessage);
            space.using();
        } catch (ResourceException e) {
            String failureTakingPlaceMessage = "Car №" + carThread.getId() + " couldn't get a free parking space and moved to another parking. " + e.getMessage();
            LOGGER.info(failureTakingPlaceMessage);
        } finally {
            if (space != null) {
                String placeIsReleasedMessage = "Car № " + carThread.getId() + " released parking space № : " + space.getParkingSpaceId();
                LOGGER.info(placeIsReleasedMessage);
                parking.returnResource(space);
            }
        }
    }
}
