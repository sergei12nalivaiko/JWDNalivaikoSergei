package com.epam.task2.jwd.entity;

import com.epam.task2.jwd.repository.Repository;
import com.epam.task2.jwd.specification.*;
import com.epam.task2.jwd.specification.impl.AddShapeSpecification;
import com.epam.task2.jwd.specification.impl.FindByIdSpecification;
import com.epam.task2.jwd.specification.impl.RemoveSpecification;
import com.epam.task2.jwd.specification.impl.UpdateSpecification;

public class TriangleFactory {

    public static Triangle createShape(TrianglePoint a, TrianglePoint b, TrianglePoint c) {
        return new Triangle(a, b, c);
    }

    public static void main(String[] args) {
        Repository repository = new Repository();
        repository.createListOfTriangles();



    }
}
