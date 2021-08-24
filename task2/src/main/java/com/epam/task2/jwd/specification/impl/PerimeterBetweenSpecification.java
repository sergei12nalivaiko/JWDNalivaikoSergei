package com.epam.task2.jwd.specification.impl;

import com.epam.task2.jwd.entity.Shape;
import com.epam.task2.jwd.specification.FilterSpecification;

public class PerimeterBetweenSpecification implements FilterSpecification {

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
