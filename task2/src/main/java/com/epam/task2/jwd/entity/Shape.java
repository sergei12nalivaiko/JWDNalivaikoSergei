package com.epam.task2.jwd.entity;

public interface Shape {
    Integer getID();

    void setID(Integer id);

    TrianglePoint getA();

    void setA(TrianglePoint a);

    TrianglePoint getB();

    void setB(TrianglePoint b);

    TrianglePoint getC();

    void setC(TrianglePoint c);

    float getPerimeter();

    float getSquare();
}
