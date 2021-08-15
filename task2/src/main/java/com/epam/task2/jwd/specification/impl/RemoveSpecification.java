package com.epam.task2.jwd.specification.impl;

import com.epam.task2.jwd.entity.Shape;
import com.epam.task2.jwd.specification.CRUDSpecification;

import java.util.List;

public class RemoveSpecification implements CRUDSpecification {

    private int id;

    public RemoveSpecification(int id) {
        this.id = id;
    }

    @Override
    public List<Shape> specify(List<Shape> shapeList) {

        for (Shape shape : shapeList) {
            if (shape.getID() == id) {
                shapeList.remove(shape);
                break;
            }
        }
        return shapeList;
    }
}
