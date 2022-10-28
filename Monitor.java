package org.example.philo;

import java.util.ArrayList;
import java.util.List;

public class Monitor {
    List<Philo> philoList = new ArrayList<>();
    List<Fork> forkList = new ArrayList<>();
    int philoNumber;

    // CONSTRUCTORS
    public Monitor(int philoNumber){
        for (int i = 0; i <= philoNumber; i++) {
            philoList.add(new Philo(i,this));
            forkList.add(new Fork());
        }
        this.philoNumber = philoNumber;
        showGlobalState();
    }

    // METHODS
    synchronized public void takeFork(Philo philo){
        philo.setStateToHungry();
        showGlobalState();
        Sleep.sleepRandomTime();
        testIfForksAvailable(philo); // will loop until forks are available
        forkList.get(philo.getNum()).setToNotAvailable();
        forkList.get((philo.getNum()+1)%philoNumber).setToNotAvailable();
        showGlobalState();
    }

    synchronized public void letFork(Philo philo){
        philo.setStateToThinking();
        forkList.get(philo.getNum()).setToAvailable();
        forkList.get((philo.getNum()+1)%philoNumber).setToAvailable();
        Sleep.sleepRandomTime();
        notifyAll();
        showGlobalState();
    }

    synchronized public void testIfForksAvailable(Philo philo){
        boolean condition1 = philo.isHungry();
        boolean condition2 = forkList.get(philo.getNum()).isAvailable();
        boolean condition3 = forkList.get((philo.getNum()+1)%philoNumber).isAvailable();

        boolean isPhiloAbleToEat = condition1 && condition2 && condition3;

        if (!isPhiloAbleToEat){
            waitUntilNotify();
            testIfForksAvailable(philo);
        }
        // else the process continue (the philo eat)
    }

    private void waitUntilNotify(){
        try {
            wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    synchronized public void showGlobalState(){
        System.out.println();
        for (Philo philo: philoList){
            String columnString = philo.getNum() + ": " + philo.getPhiloState();
            int whiteSpaceToAdd = 11 - columnString.length();
            for (int i = 0; i < whiteSpaceToAdd; i++) {
                columnString += " ";
            }
            System.out.print(columnString + " | ");
        }
    }
}
