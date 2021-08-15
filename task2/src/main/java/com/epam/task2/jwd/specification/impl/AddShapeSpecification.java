package com.epam.task2.jwd.specification.impl;

import com.epam.task2.jwd.entity.Shape;
import com.epam.task2.jwd.entity.Triangle;
import com.epam.task2.jwd.service.impl.TriangleServiceImpl;
import com.epam.task2.jwd.specification.CRUDSpecification;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AddShapeSpecification implements CRUDSpecification {

    private final Shape newShape;

    public AddShapeSpecification(Shape newShape) {
        this.newShape = newShape;
    }

    @Override
    public List<Shape> specify(List<Shape> shapeList) {
        Collections.sort(shapeList, new Comparator<Shape>() {
            @Override
            public int compare(Shape o1, Shape o2) {
                return o1.getID().compareTo(o2.getID());
            }
        });
        int id = shapeList.get(shapeList.size() - 1).getID() + 1;
        newShape.setID(id);
        shapeList.add(newShape);
        TriangleServiceImpl.getInstance().perimeter((Triangle) newShape);
        TriangleServiceImpl.getInstance().square((Triangle) newShape);
        return shapeList;
    }
}
