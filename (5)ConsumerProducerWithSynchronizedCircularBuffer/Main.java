package com.company;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        ExecutorService application = Executors.newFixedThreadPool(5);
        CircularBuffer sharedLocation = new CircularBuffer();
        try {
            application.execute(new Producer(sharedLocation));
            application.execute(new Producer(sharedLocation));
            application.execute(new Consumer(sharedLocation));
            application.execute(new Consumer(sharedLocation));
            application.execute(new Consumer(sharedLocation));
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        application.shutdown();
    }

}