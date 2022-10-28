package org.example.philo;

public class TestPhilo {
    public static void main(String[] args) {
        // Creation of the monitor
        // It is initialized with the 5 philos inside
        Monitor monitor = new Monitor(5);

        // launch each philo thread
        for (Philo philo: monitor.philoList) {
            philo.setIterationNumber(1);
            philo.start();
        }
    }
}
