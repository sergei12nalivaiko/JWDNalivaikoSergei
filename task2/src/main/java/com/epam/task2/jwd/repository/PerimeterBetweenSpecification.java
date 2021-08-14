package com.epam.task2.jwd.repository;

import com.epam.task2.jwd.entity.Shape;

public class PerimeterBetweenSpecification implements Specification {

    private float from;
    private float to;

    public PerimeterBetweenSpecification(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean specify(Shape shape) {
        boolean result = shape.getPerimeter() >= from && shape.getPerimeter() <= to ? true : false;
        return result;
    }
}
