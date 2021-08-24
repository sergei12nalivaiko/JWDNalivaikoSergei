package com.epam.task2.jwd.registar;

public interface Observable {
    void attach(ShapeObserver observer);

    void detach(ShapeObserver observer);

    void notifyObservers();
}
