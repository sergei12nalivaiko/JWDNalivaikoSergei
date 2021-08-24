package com.epam.task2.jwd.entity;

import com.epam.task2.jwd.registar.Observable;
import com.epam.task2.jwd.registar.ShapeEvent;
import com.epam.task2.jwd.registar.ShapeObserver;
import com.epam.task2.jwd.registar.impl.TriangleObserver;

import java.util.ArrayList;
import java.util.List;


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

        if (Float.compare(triangle.square, square) != 0) return false;
        if (Float.compare(triangle.perimeter, perimeter) != 0) return false;
        if (!id.equals(triangle.id)) return false;
        if (!a.equals(triangle.a)) return false;
        if (!b.equals(triangle.b)) return false;
        return c.equals(triangle.c);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + a.hashCode();
        result = 31 * result + b.hashCode();
        result = 31 * result + c.hashCode();
        result = 31 * result + (square != +0.0f ? Float.floatToIntBits(square) : 0);
        result = 31 * result + (perimeter != +0.0f ? Float.floatToIntBits(perimeter) : 0);
        return result;
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
