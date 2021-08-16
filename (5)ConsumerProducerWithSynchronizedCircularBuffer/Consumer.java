package com.company;

import java.util.Random;

public class Consumer implements Runnable {
    private static final Random generator = new Random();
    private final Buffer sharedLocation;

    public Consumer(Buffer shared) {
        sharedLocation = shared;
    }

    public void run() {
        int sum = 0;

        for (int count = 1; count <= Constants.CONSUMER_COUNT; count++) {
            try {
                Thread.sleep(generator.nextInt(300));
                sum += sharedLocation.get();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }

        System.out.printf("\n%s %d.\n%s\n",
                "Consumer read values totaling", sum, "Terminating Consumer.");
    }
}