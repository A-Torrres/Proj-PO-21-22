package ggc.core;

interface Observable {

    boolean addObserver(Observer obs);
    boolean removeObserver(Observer obs);
    void notifyObservers(NotificationType type, double price);
}
