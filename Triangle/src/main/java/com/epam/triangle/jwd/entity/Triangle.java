package com.epam.triangle.jwd.entity;

import java.util.Objects;

public class Triangle {

    private TrianglePoint a;
    private TrianglePoint b;
    private TrianglePoint c;
    private  float triangleSideA;
    private  float triangleSideB;
    private  float triangleSideC;
    private float square;
    private float perimeter;

    public Triangle() {
    }

    public Triangle(TrianglePoint a, TrianglePoint b, TrianglePoint c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Triangle(TrianglePoint a, TrianglePoint b, TrianglePoint c, float square, float perimeter) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.square = square;
        this.perimeter = perimeter;
    }

    public TrianglePoint getA() {
        return a;
    }

    public void setA(TrianglePoint a) {
        this.a = a;
    }

    public TrianglePoint getB() {
        return b;
    }

    public void setB(TrianglePoint b) {
        this.b = b;
    }

    public TrianglePoint getC() {
        return c;
    }

    public void setC(TrianglePoint c) {
        this.c = c;
    }

    public float getTriangleSideA() {
        return triangleSideA;
    }

    public void setTriangleSideA(float triangleSideA) {
        this.triangleSideA = triangleSideA;
    }

    public float getTriangleSideB() {
        return triangleSideB;
    }

    public void setTriangleSideB(float triangleSideB) {
        this.triangleSideB = triangleSideB;
    }

    public float getTriangleSideC() {
        return triangleSideC;
    }

    public void setTriangleSideC(float triangleSideC) {
        this.triangleSideC = triangleSideC;
    }

    public float getSquare() {
        return square;
    }

    public void setSquare(float square) {
        this.square = square;
    }

    public float getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(float perimeter) {
        this.perimeter = perimeter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return Float.compare(triangle.square, square) == 0 && Float.compare(triangle.perimeter, perimeter) ==
                0 && Objects.equals(a, triangle.a) && Objects.equals(b, triangle.b) && Objects.equals(c, triangle.c);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c, square, perimeter);
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", square=" + square +
                ", perimeter=" + perimeter +
                '}';
    }
}
