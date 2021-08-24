package com.epam.task2.jwd.entity;

import com.epam.task2.jwd.repository.Repository;

public class TriangleFactory {

    public static Triangle createShape(TrianglePoint a, TrianglePoint b, TrianglePoint c) {
        return new Triangle(a, b, c);
    }
}
