package com.training.threads.maintask;

import com.training.threads.maintask.entities.Car;
import com.training.threads.maintask.entities.Parking;
import com.training.threads.maintask.entities.ParkingSpace;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;

public class Runner {
    public static void main(String[] args) {
        final Logger logger = LogManager.getLogger(Runner.class.getName());
        LinkedList<ParkingSpace> list = new LinkedList<>();
        for (int i = 1; i <= 10; i++) {
            list.add(new ParkingSpace(i));
        }
        Parking<ParkingSpace> space = new Parking<>(list);
        for (int i = 0; i < 40; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                logger.warn(String.valueOf(e));
                Thread.currentThread().interrupt();
            }
            new Car(space);
        }
    }
}
