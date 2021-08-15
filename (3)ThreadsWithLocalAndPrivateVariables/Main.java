package com.company;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {
        Thread A = new Thread(new MyThreadPrivateValue(), "A private");
        Thread B = new Thread(new MyThreadPrivateValue(), "B private");
        A.start();
        B.start();
        try {
            A.join();
            System.out.println("Thread A with private variable \"i\" finished");
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        try {
            B.join();
            System.out.println("Thread B with private variable \"i\" finished");
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Both threads with private values \"i\" finished");
        A = new Thread(new MyThreadLocalValue(), "A local");
        B = new Thread(new MyThreadLocalValue(), "B local");
        A.start();
        B.start();
        try {
            A.join();
            System.out.println("Thread A with local variable \"i\" finished");
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        try {
            B.join();
            System.out.println("Thread B with local variable \"i\" finished");
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Both threads with local variable \"i\" finished");
    }

}

class MyThreadPrivateValue implements Runnable {
    private int i = 0;

    @Override
    public void run() {
        for(i = 0; i < 100; i++) {
            System.out.println("Thread name: " + Thread.currentThread().getName() + "; \"i\" value is: " + i);
        }
    }
}

class MyThreadLocalValue implements Runnable {
    @Override
    public void run() {
        int i = 0;
        for(; i < 100; i++) {
            System.out.println("Thread name: " + Thread.currentThread().getName() + "; \"i\" value is: " + i);
        }
    }
}

/* OUTPUT:

    Thread name: A private; "i" value is: 0
    Thread name: B private; "i" value is: 0
    Thread name: A private; "i" value is: 1
    Thread name: B private; "i" value is: 1
    Thread name: A private; "i" value is: 2
    Thread name: B private; "i" value is: 2
    Thread name: A private; "i" value is: 3
    Thread name: B private; "i" value is: 3
    Thread name: A private; "i" value is: 4
    Thread name: B private; "i" value is: 4
    Thread name: A private; "i" value is: 5
    Thread name: B private; "i" value is: 5
    Thread name: A private; "i" value is: 6
    Thread name: B private; "i" value is: 6
    Thread name: A private; "i" value is: 7
    Thread name: B private; "i" value is: 7
    Thread name: A private; "i" value is: 8
    Thread name: B private; "i" value is: 8
    Thread name: A private; "i" value is: 9
    Thread name: B private; "i" value is: 9
    Thread name: A private; "i" value is: 10
    Thread name: B private; "i" value is: 10
    Thread name: A private; "i" value is: 11
    Thread name: A private; "i" value is: 12
    Thread name: B private; "i" value is: 11
    Thread name: A private; "i" value is: 13
    Thread name: B private; "i" value is: 12
    Thread name: A private; "i" value is: 14
    Thread name: B private; "i" value is: 13
    Thread name: A private; "i" value is: 15
    Thread name: B private; "i" value is: 14
    Thread name: A private; "i" value is: 16
    Thread name: B private; "i" value is: 15
    Thread name: A private; "i" value is: 17
    Thread name: B private; "i" value is: 16
    Thread name: A private; "i" value is: 18
    Thread name: B private; "i" value is: 17
    Thread name: A private; "i" value is: 19
    Thread name: A private; "i" value is: 20
    Thread name: A private; "i" value is: 21
    Thread name: A private; "i" value is: 22
    Thread name: A private; "i" value is: 23
    Thread name: A private; "i" value is: 24
    Thread name: A private; "i" value is: 25
    Thread name: A private; "i" value is: 26
    Thread name: A private; "i" value is: 27
    Thread name: A private; "i" value is: 28
    Thread name: A private; "i" value is: 29
    Thread name: A private; "i" value is: 30
    Thread name: A private; "i" value is: 31
    Thread name: A private; "i" value is: 32
    Thread name: A private; "i" value is: 33
    Thread name: A private; "i" value is: 34
    Thread name: A private; "i" value is: 35
    Thread name: A private; "i" value is: 36
    Thread name: A private; "i" value is: 37
    Thread name: A private; "i" value is: 38
    Thread name: A private; "i" value is: 39
    Thread name: A private; "i" value is: 40
    Thread name: A private; "i" value is: 41
    Thread name: A private; "i" value is: 42
    Thread name: A private; "i" value is: 43
    Thread name: A private; "i" value is: 44
    Thread name: A private; "i" value is: 45
    Thread name: A private; "i" value is: 46
    Thread name: A private; "i" value is: 47
    Thread name: A private; "i" value is: 48
    Thread name: A private; "i" value is: 49
    Thread name: A private; "i" value is: 50
    Thread name: A private; "i" value is: 51
    Thread name: A private; "i" value is: 52
    Thread name: A private; "i" value is: 53
    Thread name: A private; "i" value is: 54
    Thread name: A private; "i" value is: 55
    Thread name: B private; "i" value is: 18
    Thread name: A private; "i" value is: 56
    Thread name: B private; "i" value is: 19
    Thread name: A private; "i" value is: 57
    Thread name: B private; "i" value is: 20
    Thread name: A private; "i" value is: 58
    Thread name: B private; "i" value is: 21
    Thread name: A private; "i" value is: 59
    Thread name: B private; "i" value is: 22
    Thread name: A private; "i" value is: 60
    Thread name: B private; "i" value is: 23
    Thread name: A private; "i" value is: 61
    Thread name: B private; "i" value is: 24
    Thread name: A private; "i" value is: 62
    Thread name: B private; "i" value is: 25
    Thread name: A private; "i" value is: 63
    Thread name: B private; "i" value is: 26
    Thread name: A private; "i" value is: 64
    Thread name: B private; "i" value is: 27
    Thread name: A private; "i" value is: 65
    Thread name: B private; "i" value is: 28
    Thread name: A private; "i" value is: 66
    Thread name: B private; "i" value is: 29
    Thread name: A private; "i" value is: 67
    Thread name: B private; "i" value is: 30
    Thread name: A private; "i" value is: 68
    Thread name: B private; "i" value is: 31
    Thread name: B private; "i" value is: 32
    Thread name: A private; "i" value is: 69
    Thread name: B private; "i" value is: 33
    Thread name: B private; "i" value is: 34
    Thread name: B private; "i" value is: 35
    Thread name: B private; "i" value is: 36
    Thread name: A private; "i" value is: 70
    Thread name: B private; "i" value is: 37
    Thread name: A private; "i" value is: 71
    Thread name: A private; "i" value is: 72
    Thread name: B private; "i" value is: 38
    Thread name: A private; "i" value is: 73
    Thread name: A private; "i" value is: 74
    Thread name: A private; "i" value is: 75
    Thread name: B private; "i" value is: 39
    Thread name: A private; "i" value is: 76
    Thread name: B private; "i" value is: 40
    Thread name: A private; "i" value is: 77
    Thread name: B private; "i" value is: 41
    Thread name: A private; "i" value is: 78
    Thread name: B private; "i" value is: 42
    Thread name: A private; "i" value is: 79
    Thread name: B private; "i" value is: 43
    Thread name: A private; "i" value is: 80
    Thread name: B private; "i" value is: 44
    Thread name: A private; "i" value is: 81
    Thread name: B private; "i" value is: 45
    Thread name: A private; "i" value is: 82
    Thread name: B private; "i" value is: 46
    Thread name: A private; "i" value is: 83
    Thread name: B private; "i" value is: 47
    Thread name: A private; "i" value is: 84
    Thread name: B private; "i" value is: 48
    Thread name: A private; "i" value is: 85
    Thread name: B private; "i" value is: 49
    Thread name: A private; "i" value is: 86
    Thread name: B private; "i" value is: 50
    Thread name: A private; "i" value is: 87
    Thread name: A private; "i" value is: 88
    Thread name: A private; "i" value is: 89
    Thread name: A private; "i" value is: 90
    Thread name: B private; "i" value is: 51
    Thread name: A private; "i" value is: 91
    Thread name: B private; "i" value is: 52
    Thread name: A private; "i" value is: 92
    Thread name: B private; "i" value is: 53
    Thread name: A private; "i" value is: 93
    Thread name: B private; "i" value is: 54
    Thread name: A private; "i" value is: 94
    Thread name: B private; "i" value is: 55
    Thread name: A private; "i" value is: 95
    Thread name: B private; "i" value is: 56
    Thread name: A private; "i" value is: 96
    Thread name: B private; "i" value is: 57
    Thread name: A private; "i" value is: 97
    Thread name: B private; "i" value is: 58
    Thread name: A private; "i" value is: 98
    Thread name: B private; "i" value is: 59
    Thread name: A private; "i" value is: 99
    Thread name: B private; "i" value is: 60
    Thread name: B private; "i" value is: 61
    Thread A with private variable "i" finished
    Thread name: B private; "i" value is: 62
    Thread name: B private; "i" value is: 63
    Thread name: B private; "i" value is: 64
    Thread name: B private; "i" value is: 65
    Thread name: B private; "i" value is: 66
    Thread name: B private; "i" value is: 67
    Thread name: B private; "i" value is: 68
    Thread name: B private; "i" value is: 69
    Thread name: B private; "i" value is: 70
    Thread name: B private; "i" value is: 71
    Thread name: B private; "i" value is: 72
    Thread name: B private; "i" value is: 73
    Thread name: B private; "i" value is: 74
    Thread name: B private; "i" value is: 75
    Thread name: B private; "i" value is: 76
    Thread name: B private; "i" value is: 77
    Thread name: B private; "i" value is: 78
    Thread name: B private; "i" value is: 79
    Thread name: B private; "i" value is: 80
    Thread name: B private; "i" value is: 81
    Thread name: B private; "i" value is: 82
    Thread name: B private; "i" value is: 83
    Thread name: B private; "i" value is: 84
    Thread name: B private; "i" value is: 85
    Thread name: B private; "i" value is: 86
    Thread name: B private; "i" value is: 87
    Thread name: B private; "i" value is: 88
    Thread name: B private; "i" value is: 89
    Thread name: B private; "i" value is: 90
    Thread name: B private; "i" value is: 91
    Thread name: B private; "i" value is: 92
    Thread name: B private; "i" value is: 93
    Thread name: B private; "i" value is: 94
    Thread name: B private; "i" value is: 95
    Thread name: B private; "i" value is: 96
    Thread name: B private; "i" value is: 97
    Thread name: B private; "i" value is: 98
    Thread name: B private; "i" value is: 99
    Thread B with private variable "i" finished
    Both threads with private values "i" finished
    Thread name: A local; "i" value is: 0
    Thread name: A local; "i" value is: 1
    Thread name: B local; "i" value is: 0
    Thread name: B local; "i" value is: 1
    Thread name: A local; "i" value is: 2
    Thread name: B local; "i" value is: 2
    Thread name: A local; "i" value is: 3
    Thread name: B local; "i" value is: 3
    Thread name: A local; "i" value is: 4
    Thread name: B local; "i" value is: 4
    Thread name: A local; "i" value is: 5
    Thread name: B local; "i" value is: 5
    Thread name: A local; "i" value is: 6
    Thread name: B local; "i" value is: 6
    Thread name: A local; "i" value is: 7
    Thread name: B local; "i" value is: 7
    Thread name: A local; "i" value is: 8
    Thread name: B local; "i" value is: 8
    Thread name: A local; "i" value is: 9
    Thread name: B local; "i" value is: 9
    Thread name: A local; "i" value is: 10
    Thread name: B local; "i" value is: 10
    Thread name: A local; "i" value is: 11
    Thread name: B local; "i" value is: 11
    Thread name: A local; "i" value is: 12
    Thread name: B local; "i" value is: 12
    Thread name: A local; "i" value is: 13
    Thread name: B local; "i" value is: 13
    Thread name: A local; "i" value is: 14
    Thread name: B local; "i" value is: 14
    Thread name: A local; "i" value is: 15
    Thread name: B local; "i" value is: 15
    Thread name: A local; "i" value is: 16
    Thread name: B local; "i" value is: 16
    Thread name: A local; "i" value is: 17
    Thread name: B local; "i" value is: 17
    Thread name: A local; "i" value is: 18
    Thread name: B local; "i" value is: 18
    Thread name: A local; "i" value is: 19
    Thread name: B local; "i" value is: 19
    Thread name: A local; "i" value is: 20
    Thread name: B local; "i" value is: 20
    Thread name: A local; "i" value is: 21
    Thread name: B local; "i" value is: 21
    Thread name: A local; "i" value is: 22
    Thread name: B local; "i" value is: 22
    Thread name: A local; "i" value is: 23
    Thread name: B local; "i" value is: 23
    Thread name: A local; "i" value is: 24
    Thread name: B local; "i" value is: 24
    Thread name: A local; "i" value is: 25
    Thread name: B local; "i" value is: 25
    Thread name: A local; "i" value is: 26
    Thread name: B local; "i" value is: 26
    Thread name: A local; "i" value is: 27
    Thread name: B local; "i" value is: 27
    Thread name: B local; "i" value is: 28
    Thread name: A local; "i" value is: 28
    Thread name: B local; "i" value is: 29
    Thread name: A local; "i" value is: 29
    Thread name: B local; "i" value is: 30
    Thread name: A local; "i" value is: 30
    Thread name: B local; "i" value is: 31
    Thread name: A local; "i" value is: 31
    Thread name: B local; "i" value is: 32
    Thread name: A local; "i" value is: 32
    Thread name: B local; "i" value is: 33
    Thread name: A local; "i" value is: 33
    Thread name: B local; "i" value is: 34
    Thread name: A local; "i" value is: 34
    Thread name: B local; "i" value is: 35
    Thread name: A local; "i" value is: 35
    Thread name: B local; "i" value is: 36
    Thread name: A local; "i" value is: 36
    Thread name: A local; "i" value is: 37
    Thread name: B local; "i" value is: 37
    Thread name: A local; "i" value is: 38
    Thread name: B local; "i" value is: 38
    Thread name: B local; "i" value is: 39
    Thread name: A local; "i" value is: 39
    Thread name: B local; "i" value is: 40
    Thread name: A local; "i" value is: 40
    Thread name: B local; "i" value is: 41
    Thread name: A local; "i" value is: 41
    Thread name: B local; "i" value is: 42
    Thread name: A local; "i" value is: 42
    Thread name: B local; "i" value is: 43
    Thread name: B local; "i" value is: 44
    Thread name: A local; "i" value is: 43
    Thread name: B local; "i" value is: 45
    Thread name: A local; "i" value is: 44
    Thread name: B local; "i" value is: 46
    Thread name: A local; "i" value is: 45
    Thread name: B local; "i" value is: 47
    Thread name: A local; "i" value is: 46
    Thread name: B local; "i" value is: 48
    Thread name: A local; "i" value is: 47
    Thread name: B local; "i" value is: 49
    Thread name: A local; "i" value is: 48
    Thread name: B local; "i" value is: 50
    Thread name: A local; "i" value is: 49
    Thread name: B local; "i" value is: 51
    Thread name: A local; "i" value is: 50
    Thread name: B local; "i" value is: 52
    Thread name: A local; "i" value is: 51
    Thread name: B local; "i" value is: 53
    Thread name: A local; "i" value is: 52
    Thread name: B local; "i" value is: 54
    Thread name: A local; "i" value is: 53
    Thread name: B local; "i" value is: 55
    Thread name: A local; "i" value is: 54
    Thread name: B local; "i" value is: 56
    Thread name: A local; "i" value is: 55
    Thread name: B local; "i" value is: 57
    Thread name: A local; "i" value is: 56
    Thread name: B local; "i" value is: 58
    Thread name: B local; "i" value is: 59
    Thread name: A local; "i" value is: 57
    Thread name: B local; "i" value is: 60
    Thread name: A local; "i" value is: 58
    Thread name: A local; "i" value is: 59
    Thread name: B local; "i" value is: 61
    Thread name: A local; "i" value is: 60
    Thread name: A local; "i" value is: 61
    Thread name: B local; "i" value is: 62
    Thread name: A local; "i" value is: 62
    Thread name: B local; "i" value is: 63
    Thread name: A local; "i" value is: 63
    Thread name: B local; "i" value is: 64
    Thread name: A local; "i" value is: 64
    Thread name: B local; "i" value is: 65
    Thread name: A local; "i" value is: 65
    Thread name: B local; "i" value is: 66
    Thread name: A local; "i" value is: 66
    Thread name: B local; "i" value is: 67
    Thread name: A local; "i" value is: 67
    Thread name: B local; "i" value is: 68
    Thread name: A local; "i" value is: 68
    Thread name: B local; "i" value is: 69
    Thread name: A local; "i" value is: 69
    Thread name: B local; "i" value is: 70
    Thread name: A local; "i" value is: 70
    Thread name: B local; "i" value is: 71
    Thread name: A local; "i" value is: 71
    Thread name: B local; "i" value is: 72
    Thread name: A local; "i" value is: 72
    Thread name: B local; "i" value is: 73
    Thread name: A local; "i" value is: 73
    Thread name: B local; "i" value is: 74
    Thread name: A local; "i" value is: 74
    Thread name: A local; "i" value is: 75
    Thread name: A local; "i" value is: 76
    Thread name: A local; "i" value is: 77
    Thread name: B local; "i" value is: 75
    Thread name: A local; "i" value is: 78
    Thread name: B local; "i" value is: 76
    Thread name: A local; "i" value is: 79
    Thread name: B local; "i" value is: 77
    Thread name: A local; "i" value is: 80
    Thread name: B local; "i" value is: 78
    Thread name: A local; "i" value is: 81
    Thread name: B local; "i" value is: 79
    Thread name: A local; "i" value is: 82
    Thread name: B local; "i" value is: 80
    Thread name: A local; "i" value is: 83
    Thread name: B local; "i" value is: 81
    Thread name: B local; "i" value is: 82
    Thread name: A local; "i" value is: 84
    Thread name: B local; "i" value is: 83
    Thread name: A local; "i" value is: 85
    Thread name: B local; "i" value is: 84
    Thread name: A local; "i" value is: 86
    Thread name: B local; "i" value is: 85
    Thread name: A local; "i" value is: 87
    Thread name: B local; "i" value is: 86
    Thread name: A local; "i" value is: 88
    Thread name: B local; "i" value is: 87
    Thread name: A local; "i" value is: 89
    Thread name: B local; "i" value is: 88
    Thread name: A local; "i" value is: 90
    Thread name: B local; "i" value is: 89
    Thread name: A local; "i" value is: 91
    Thread name: B local; "i" value is: 90
    Thread name: A local; "i" value is: 92
    Thread name: B local; "i" value is: 91
    Thread name: A local; "i" value is: 93
    Thread name: B local; "i" value is: 92
    Thread name: A local; "i" value is: 94
    Thread name: B local; "i" value is: 93
    Thread name: A local; "i" value is: 95
    Thread name: B local; "i" value is: 94
    Thread name: A local; "i" value is: 96
    Thread name: B local; "i" value is: 95
    Thread name: A local; "i" value is: 97
    Thread name: B local; "i" value is: 96
    Thread name: A local; "i" value is: 98
    Thread name: B local; "i" value is: 97
    Thread name: A local; "i" value is: 99
    Thread name: B local; "i" value is: 98
    Thread A with local variable "i" finished
    Thread name: B local; "i" value is: 99
    Thread B with local variable "i" finished
    Both threads with local variable "i" finished

 */