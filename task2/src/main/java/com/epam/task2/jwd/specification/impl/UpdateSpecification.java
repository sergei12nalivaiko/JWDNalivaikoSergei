package com.epam.task2.jwd.specification.impl;

import com.epam.task2.jwd.entity.Shape;
import com.epam.task2.jwd.specification.CRUDSpecification;

import java.util.List;

public class UpdateSpecification implements CRUDSpecification {
    private Shape newShape;
    private int id;

    public UpdateSpecification(Shape newShape, int id) {
        this.newShape = newShape;
        this.id = id;
    }

    @Override
    public List<Shape> specify(List<Shape> shapeList) {
        int index;
        for (Shape shape : shapeList) {
            if (shape.getID() == id) {
                index = shapeList.indexOf(shape);
                shapeList.remove(shape);
                newShape.setID(id);
                shapeList.add(index, newShape);
                break;
            }
        }
        return shapeList;
    }
}
