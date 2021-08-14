package com.epam.task2.jwd.repository;

import com.epam.task2.jwd.entity.Shape;


public class IdSpecification implements Specification {

    private int id;

    public IdSpecification(int id) {
        this.id = id;
    }

    @Override
    public boolean specify(Shape shape) {
        boolean result = shape.getID() == id;
        return result;
    }
}
