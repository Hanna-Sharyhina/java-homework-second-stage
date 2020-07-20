package com.training.threads.optionaltask.entities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Plane implements Runnable {
    private Thread planeThread;
    private BlockingQueue<Runway> airField;
    private static final Logger LOGGER = LogManager.getLogger(Plane.class.getName());

    public Plane(BlockingQueue<Runway> airField) {
        planeThread = new Thread(this);
        this.airField = airField;
        planeThread.start();
    }

    @Override
    public void run() {
        Runway runway;
        PlaneModels model = PlaneModels.values()[new Random().nextInt(PlaneModels.values().length)];
        String planeName = model + " " + planeThread.getId();
        try {
            runway = airField.take();
            String takingRunwayMessage = "Plane " + planeName + " started to enter a runway № " + runway.getId();
            LOGGER.info(takingRunwayMessage);
            String successfulTakingRunwayMessage = "The runway " + runway.getId() + " successfully took the plane " + planeName;
            LOGGER.info(successfulTakingRunwayMessage);
            runway.using();
            airField.put(runway);
            String successfulTookOffMessage = "The plane " + planeName + " successfully took off and released the runway " + runway.getId();
            LOGGER.info(successfulTookOffMessage);
        } catch (InterruptedException e) {
            LOGGER.warn(String.valueOf(e));
            Thread.currentThread().interrupt();
        }
    }
}
