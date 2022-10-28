package org.example.philo;

public class Philo extends Thread {
    int num;
    int iterationNumber;
    PhiloState philoState = PhiloState.THINKING;
    Monitor monitor;

    // CONSTRUCTORS
    public Philo(int num, Monitor monitor){
        setMonitor(monitor);
        setNum(num);
    }

    public Philo(int num, Monitor monitor, int iterationNumber){
        setMonitor(monitor);
        setNum(num);
        setIterationNumber(iterationNumber);
    }

    // GETTERS
    public boolean isThinking() { return philoState.equals(PhiloState.THINKING); }

    public boolean isHungry() { return philoState.equals(PhiloState.HUNGRY); }

    public boolean isEating() { return philoState.equals(PhiloState.EATING); }

    public int getNum(){return num;}

    public PhiloState getPhiloState(){ return philoState; }

    // SETTERS
    public void setNum(int num) { this.num = num; }

    public void setIterationNumber(int iterationNumber) { this.iterationNumber = iterationNumber; }

    public void setStateToThinking() { philoState = PhiloState.THINKING; }

    public void setStateToHungry() { philoState = PhiloState.HUNGRY; }

    public void setStateToEating() { philoState = PhiloState.EATING; }

    public void setStateToFinished() { philoState = PhiloState.FINISHED; }

    public void setMonitor(Monitor monitor) { this.monitor = monitor; }


    // METHODS
    @Override
    public void run() {
        for (int i = 0; i < iterationNumber; i++) {
            think();
            monitor.takeFork(this);
            eat();
            monitor.letFork(this);
        }
        setStateToFinished();
        monitor.showGlobalState();
    }

    private void think(){
        setStateToThinking();
        Sleep.sleepRandomTime();
    }

    private void eat(){
        setStateToEating();
        Sleep.sleepRandomTime();
    }
}
