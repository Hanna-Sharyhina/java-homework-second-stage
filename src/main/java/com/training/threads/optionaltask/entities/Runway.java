package com.training.threads.optionaltask.entities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class Runway {
    private int id;
    private static final Logger LOGGER = LogManager.getLogger(Runway.class);

    public Runway(int id) {
        super();
        this.id = id;
    }

    int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    void using() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            LOGGER.warn(String.valueOf(e));
            Thread.currentThread().interrupt();
        }
    }
}
