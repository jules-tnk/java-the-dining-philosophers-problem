package org.example.philo;

import java.util.Random;

public class Sleep extends Thread{
    static Random r = new Random();
    private static int low = 100;
    private static int high = 1000;
    public static int getRandomSleepTime(){
        return r.nextInt(high - low) + low;
    }

    public static void sleepRandomTime(){
        try {
            sleep(getRandomSleepTime());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
