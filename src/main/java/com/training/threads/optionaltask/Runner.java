package com.training.threads.optionaltask;

import com.training.threads.optionaltask.entities.Plane;
import com.training.threads.optionaltask.entities.Runway;

import java.util.concurrent.ArrayBlockingQueue;

public class Runner {
    public static void main(String[] args) {
        ArrayBlockingQueue<Runway> airfield = new ArrayBlockingQueue<>(5);
        for (int i = 1; i < 6; i++) {
            airfield.add(new Runway(i));
        }
        for (int i = 0; i <= 10; i++) {
            new Plane(airfield);
        }
    }
}
