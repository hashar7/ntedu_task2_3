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

/* OUTPUT:

    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    Producer writes "1" on index 0 (buffers occupied: 0)
    buffers:   1  -1  -1
     ---- ---- ----


    Consumer reads "1" on index 0 (buffers occupied: 1)
    buffers:   1  -1  -1
     ---- ---- ----


    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    Producer writes "1" on index 1 (buffers occupied: 0)
    buffers:   1   1  -1
     ---- ---- ----


    Consumer reads "1" on index 1 (buffers occupied: 1)
    buffers:   1   1  -1
     ---- ---- ----


    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    Producer writes "2" on index 2 (buffers occupied: 0)
    buffers:   1   1   2
     ---- ---- ----


    Consumer reads "2" on index 2 (buffers occupied: 1)
    buffers:   1   1   2
     ---- ---- ----


    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    Producer writes "2" on index 0 (buffers occupied: 0)
    buffers:   2   1   2
     ---- ---- ----


    Consumer reads "2" on index 0 (buffers occupied: 1)
    buffers:   2   1   2
     ---- ---- ----


    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    Producer writes "3" on index 1 (buffers occupied: 0)
    buffers:   2   3   2
     ---- ---- ----


    Consumer reads "3" on index 1 (buffers occupied: 1)
    buffers:   2   3   2
     ---- ---- ----


    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    Producer writes "3" on index 2 (buffers occupied: 0)
    buffers:   2   3   3
     ---- ---- ----


    Consumer reads "3" on index 2 (buffers occupied: 1)
    buffers:   2   3   3
     ---- ---- ----


    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    Producer writes "4" on index 0 (buffers occupied: 0)
    buffers:   4   3   3
     ---- ---- ----


    Consumer reads "4" on index 0 (buffers occupied: 1)
    buffers:   4   3   3
     ---- ---- ----


    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    Producer writes "5" on index 1 (buffers occupied: 0)
    buffers:   4   5   3
     ---- ---- ----


    Consumer reads "5" on index 1 (buffers occupied: 1)
    buffers:   4   5   3
     ---- ---- ----


    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    Producer writes "6" on index 2 (buffers occupied: 0)
    buffers:   4   5   6
     ---- ---- ----


    Consumer reads "6" on index 2 (buffers occupied: 1)
    buffers:   4   5   6
     ---- ---- ----


    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    Producer writes "4" on index 0 (buffers occupied: 0)
    buffers:   4   5   6
     ---- ---- ----


    Consumer reads "4" on index 0 (buffers occupied: 1)
    buffers:   4   5   6
     ---- ---- ----


    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    Producer writes "7" on index 1 (buffers occupied: 0)
    buffers:   4   7   6
     ---- ---- ----


    Consumer reads "7" on index 1 (buffers occupied: 1)
    buffers:   4   7   6
     ---- ---- ----


    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    Producer writes "5" on index 2 (buffers occupied: 0)
    buffers:   4   7   5
     ---- ---- ----


    Consumer reads "5" on index 2 (buffers occupied: 1)
    buffers:   4   7   5
     ---- ---- ----


    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    Producer writes "8" on index 0 (buffers occupied: 0)
    buffers:   8   7   5
     ---- ---- ----


    Consumer reads "8" on index 0 (buffers occupied: 1)
    buffers:   8   7   5
     ---- ---- ----


    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    Producer writes "6" on index 1 (buffers occupied: 0)
    buffers:   8   6   5
     ---- ---- ----


    Consumer reads "6" on index 1 (buffers occupied: 1)
    buffers:   8   6   5
     ---- ---- ----


    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    Producer writes "9" on index 2 (buffers occupied: 0)
    buffers:   8   6   9
     ---- ---- ----


    Consumer reads "9" on index 2 (buffers occupied: 1)
    buffers:   8   6   9
     ---- ---- ----


    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    Producer writes "10" on index 0 (buffers occupied: 0)
    buffers:  10   6   9
     ---- ---- ----


    Consumer reads "10" on index 0 (buffers occupied: 1)
    buffers:  10   6   9
     ---- ---- ----


    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    Producer writes "7" on index 1 (buffers occupied: 0)
    buffers:  10   7   9
     ---- ---- ----


    Consumer reads "7" on index 1 (buffers occupied: 1)
    buffers:  10   7   9
     ---- ---- ----


    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    Producer writes "11" on index 2 (buffers occupied: 0)
    buffers:  10   7  11
     ---- ---- ----


    Consumer reads "11" on index 2 (buffers occupied: 1)
    buffers:  10   7  11
     ---- ---- ----


    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    Producer writes "8" on index 0 (buffers occupied: 0)
    buffers:   8   7  11
     ---- ---- ----


    Consumer reads "8" on index 0 (buffers occupied: 1)
    buffers:   8   7  11
     ---- ---- ----


    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    Producer writes "9" on index 1 (buffers occupied: 0)
    buffers:   8   9  11
     ---- ---- ----


    Consumer reads "9" on index 1 (buffers occupied: 1)
    buffers:   8   9  11
     ---- ---- ----


    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    Producer writes "12" on index 2 (buffers occupied: 0)
    buffers:   8   9  12
     ---- ---- ----


    Consumer reads "12" on index 2 (buffers occupied: 1)
    buffers:   8   9  12
     ---- ---- ----


    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    Producer writes "10" on index 0 (buffers occupied: 0)
    buffers:  10   9  12
     ---- ---- ----


    Consumer reads "10" on index 0 (buffers occupied: 1)
    buffers:  10   9  12
     ---- ---- ----


    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    Producer writes "13" on index 1 (buffers occupied: 0)
    buffers:  10  13  12
     ---- ---- ----


    Consumer reads "13" on index 1 (buffers occupied: 1)
    buffers:  10  13  12
     ---- ---- ----


    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    Producer writes "11" on index 2 (buffers occupied: 0)
    buffers:  10  13  11
     ---- ---- ----


    Consumer reads "11" on index 2 (buffers occupied: 1)
    buffers:  10  13  11
     ---- ---- ----


    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    Producer writes "14" on index 0 (buffers occupied: 0)
    buffers:  14  13  11
     ---- ---- ----


    Consumer reads "14" on index 0 (buffers occupied: 1)
    buffers:  14  13  11
     ---- ---- ----


    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    Producer writes "12" on index 1 (buffers occupied: 0)
    buffers:  14  12  11
     ---- ---- ----


    Consumer reads "12" on index 1 (buffers occupied: 1)
    buffers:  14  12  11
     ---- ---- ----


    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    Producer writes "15" on index 2 (buffers occupied: 0)
    buffers:  14  12  15
     ---- ---- ----



    Producer done producing.
    Terminating Producer.
    Consumer reads "15" on index 2 (buffers occupied: 1)
    buffers:  14  12  15
     ---- ---- ----


    All buffers empty. Consumer waits.

    Consumer read values totaling 74.
    Terminating Consumer.
    All buffers empty. Consumer waits.
    Producer writes "13" on index 0 (buffers occupied: 0)
    buffers:  13  12  15
     ---- ---- ----


    Consumer reads "13" on index 0 (buffers occupied: 1)
    buffers:  13  12  15
     ---- ---- ----


    All buffers empty. Consumer waits.
    All buffers empty. Consumer waits.
    Producer writes "14" on index 1 (buffers occupied: 0)
    buffers:  13  14  15
     ---- ---- ----


    Consumer reads "14" on index 1 (buffers occupied: 1)
    buffers:  13  14  15
     ---- ---- ----



    Consumer read values totaling 77.
    Terminating Consumer.
    All buffers empty. Consumer waits.
    Producer writes "15" on index 2 (buffers occupied: 0)
    buffers:  13  14  15
     ---- ---- ----



    Producer done producing.
    Terminating Producer.
    Consumer reads "15" on index 2 (buffers occupied: 1)
    buffers:  13  14  15
     ---- ---- ----



    Consumer read values totaling 89.
    Terminating Consumer.

 */
