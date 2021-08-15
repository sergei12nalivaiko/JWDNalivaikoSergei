package com.epam.task2.jwd.specification.impl;

import com.epam.task2.jwd.entity.Shape;
import com.epam.task2.jwd.specification.CRUDSpecification;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortByIdSpecification implements CRUDSpecification {

    @Override
    public List<Shape> specify(List<Shape> shapeList) {
        Collections.sort(shapeList, new Comparator<Shape>() {
            @Override
            public int compare(Shape o1, Shape o2) {
                return o1.getID().compareTo(o2.getID());
            }
        });
        return shapeList;
    }

}
