package com.epam.task2.jwd.entity;

import com.epam.task2.jwd.repository.PerimeterBetweenSpecification;
import com.epam.task2.jwd.repository.Repository;
import com.epam.task2.jwd.repository.Specification;

public class TriangleFactory {

    public static Triangle createShape(TrianglePoint a, TrianglePoint b, TrianglePoint c) {
        return new Triangle(a, b, c);
    }

    public static void main(String[] args) {
        Repository repository = new Repository();
        repository.createListOfTriangles();
        System.out.println(repository.getList().size());
        Triangle triangle = (Triangle) repository.getList().get(1);
        triangle.setA(new TrianglePoint(2, 2));
    }
}
