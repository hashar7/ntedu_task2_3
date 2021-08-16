package com.company;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class CircularBuffer implements Buffer {
    private final Lock accessLock = new ReentrantLock();
    private final Condition canWrite = accessLock.newCondition();
    private final Condition canRead = accessLock.newCondition();

    private final int[] buffer = {-1, -1, -1};

    private int occupiedBuffers = 0;
    private int writeIndex = 0;
    private int readIndex = 0;

    public void set(int value) {
        accessLock.lock();

        try {
            while (occupiedBuffers == buffer.length) {
                System.out.print("All buffers full. Producer waits.\n");
                canWrite.await();
            }

            buffer[writeIndex] = value;
            displayState("Producer writes \"" + buffer[writeIndex] + "\" on index " + writeIndex);
            writeIndex = (writeIndex + 1) % buffer.length;

            occupiedBuffers++;
            canRead.signalAll();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } finally {
            accessLock.unlock();
        }
    }

    public int get() {
        int readValue = 0;
        accessLock.lock();

        try {
            while (occupiedBuffers == 0) {
                System.out.print("All buffers empty. Consumer waits.\n");
                canRead.await();
            }

            readValue = buffer[readIndex];
            displayState("Consumer reads \"" + readValue + "\" on index " + readIndex);

            readIndex = (readIndex + 1) % buffer.length;

            occupiedBuffers--;
            canWrite.signalAll();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } finally {
            accessLock.unlock();
        }

        return readValue;
    }

    public void displayState(String operation) {
        System.out.printf("%s%s%d)\n%s", operation,
                " (buffers occupied: ", occupiedBuffers, "buffers: ");

        for (int value : buffer)
            System.out.printf(" %2d ", value);

        System.out.print("\n ");
        for (int i = 0; i < buffer.length; i++)
            System.out.print("---- ");

        System.out.print("\n ");

        System.out.println("\n");
    }
}