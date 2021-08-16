package com.epam.task2.jwd.entity;

import com.epam.task2.jwd.registar.Observable;
import com.epam.task2.jwd.registar.ShapeEvent;
import com.epam.task2.jwd.registar.ShapeObserver;
import com.epam.task2.jwd.registar.impl.TriangleObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Triangle implements Shape, Observable {
    private Integer id = 0;
    private TrianglePoint a;
    private TrianglePoint b;
    private TrianglePoint c;
    private float square;
    private float perimeter;
    private List<ShapeObserver> observerList = new ArrayList<>();

    public Triangle() {
    }

    public Triangle(TrianglePoint a, TrianglePoint b, TrianglePoint c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public Integer getID() {
        return id;
    }

    @Override
    public void setID(Integer id) {
        this.id = id;
    }


    @Override
    public float getPerimeter() {
        return this.perimeter;
    }

    @Override
    public float getSquare() {
        return this.square;
    }

    public TrianglePoint getA() {
        return a;
    }

    public void setA(TrianglePoint a) {

        this.a = a;
        notifyObservers();
    }

    public TrianglePoint getB() {
        return b;
    }

    public void setB(TrianglePoint b) {
        this.b = b;
        notifyObservers();
    }

    public TrianglePoint getC() {
        return c;
    }

    public void setC(TrianglePoint c) {
        this.c = c;
        notifyObservers();
    }

    public void setSquare(float square) {
        this.square = square;
    }

    public void setPerimeter(float perimeter) {
        this.perimeter = perimeter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return Float.compare(triangle.square, square) == 0 && Float.compare(triangle.perimeter, perimeter) == 0 && id.equals(triangle.id) && a.equals(triangle.a) && b.equals(triangle.b) && c.equals(triangle.c);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, a, b, c, square, perimeter);
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "id=" + id +
                ", a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", square=" + square +
                ", perimeter=" + perimeter +
                '}';
    }

    @Override
    public void attach(ShapeObserver observer) {
        if (observer != null) {
            observerList.add(observer);
        }
    }

    @Override
    public void detach(ShapeObserver observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObservers() {
        ShapeEvent shapeEvent = new ShapeEvent(this);
        attach(TriangleObserver.getInstance());
        for (ShapeObserver shapeObserver : observerList) {
            observerList.forEach(o -> o.parameterChanged(shapeEvent));
        }
    }
}
