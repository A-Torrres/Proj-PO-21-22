package ggc.core;

interface Observer {

    void update(NotificationType type, Product product, double price);
}
