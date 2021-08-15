package com.epam.task2.jwd.specification.impl;

import com.epam.task2.jwd.entity.Shape;
import com.epam.task2.jwd.specification.FilterSpecification;


public class FindByIdSpecification implements FilterSpecification {

    private int id;

    public FindByIdSpecification(int id) {
        this.id = id;
    }

    @Override
    public boolean specify(Shape shape) {
        boolean result = shape.getID() == id;
        return result;
    }
}
