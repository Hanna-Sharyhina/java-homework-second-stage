package com.training.threads.maintask.entities;

import com.training.threads.maintask.ResourceException;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Parking<T> {
    private static final int PARKING_SIZE = 10;
    private final Semaphore semaphore = new Semaphore(PARKING_SIZE, true);
    private final Queue<T> resources = new LinkedList<>();

    public Parking(Queue<T> sources) {
        resources.addAll(sources);
    }

    T getResource() throws ResourceException {
        try {
            if (semaphore.tryAcquire(200, TimeUnit.MILLISECONDS)) {
                return resources.poll();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ResourceException(e);
        }
        throw new ResourceException("Time's up. ");
    }

    void returnResource(T resource) {
        resources.add(resource);
        semaphore.release();
    }
}
