package com.company;

import java.util.Random;

public class Producer implements Runnable {
    private static final Random generator = new Random();
    private final Buffer sharedLocation;

    public Producer(Buffer shared) {
        sharedLocation = shared;
    }

    public void run() {
        int sum = 0;

        for (int count = 1; count <= Constants.PRODUCER_COUNT; count++) {
            try {
                Thread.sleep(generator.nextInt(3000));
                sharedLocation.set(count);
                sum += count;
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }

        System.out.printf("\n%s\n%s\n", "Producer done producing.",
                "Terminating Producer.");
    }
}