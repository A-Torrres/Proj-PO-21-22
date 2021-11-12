package ggc.core;

interface Observable {

    void addObserver(Observer obs);
    void removeObserver(Observer obs);
    void notifyObservers();
}
