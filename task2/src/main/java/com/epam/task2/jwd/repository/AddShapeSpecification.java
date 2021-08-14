package com.epam.task2.jwd.repository;

import com.epam.task2.jwd.entity.Shape;

import java.util.List;

public class AddShapeSpecification implements Specification {
    @Override
    public boolean specify(Shape shape) {
        Repository repository = new Repository();
        boolean result = repository.getList().add(shape);
        return result;
    }
}
