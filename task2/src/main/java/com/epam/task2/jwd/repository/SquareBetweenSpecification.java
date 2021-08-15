package com.epam.task2.jwd.repository;

import com.epam.task2.jwd.entity.Shape;

public class SquareBetweenSpecification implements Specification {

    private float from;
    private float to;

    public SquareBetweenSpecification(float from, float to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean specify(Shape shape) {
        boolean result = shape.getSquare() >= from && shape.getSquare() <= to ? true : false;
        return result;
    }
}