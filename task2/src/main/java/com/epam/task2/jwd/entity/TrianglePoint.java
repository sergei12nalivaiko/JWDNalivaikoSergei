package com.epam.task2.jwd.entity;

import java.util.Objects;

public class TrianglePoint {
    private Integer x;
    private Integer y;

    public TrianglePoint() {
    }

    public TrianglePoint(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrianglePoint point = (TrianglePoint) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
