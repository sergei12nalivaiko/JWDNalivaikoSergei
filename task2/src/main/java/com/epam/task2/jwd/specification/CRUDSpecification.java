package com.epam.task2.jwd.specification;

import com.epam.task2.jwd.entity.Shape;

import java.util.List;

public interface CRUDSpecification {
    List<Shape> specify(List<Shape> shapeList);
}
