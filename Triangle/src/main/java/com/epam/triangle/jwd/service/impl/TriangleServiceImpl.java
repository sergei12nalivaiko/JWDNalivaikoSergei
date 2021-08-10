package com.epam.triangle.jwd.service.impl;

import com.epam.triangle.jwd.entity.Triangle;
import com.epam.triangle.jwd.service.TriangleService;

public class TriangleServiceImpl implements TriangleService {


    @Override
    public void square(Triangle triangle) {

        triangle.setSquare((Math.abs((((triangle.getB().getX() - triangle.getA().getX())) *
                ((triangle.getC().getY() - triangle.getA().getY()))) -
                (((triangle.getC().getX() - triangle.getA().getX())) *
                        ((triangle.getB().getY() - triangle.getA().getY()))))) / 2);
    }

    @Override
    public void perimeter(Triangle triangle) {

        sideLength(triangle);
        triangle.setPerimeter(triangle.getTriangleSideA() + triangle.getTriangleSideB() + triangle.getTriangleSideC());
    }

    private void sideLength(Triangle triangle) {
        triangle.setTriangleSideA((float) (Math.sqrt((Math.abs(Math.pow(triangle.getA().getX() - triangle.getB().getX(), 2))) +
                (Math.abs(Math.pow(triangle.getA().getY() - triangle.getB().getY(), 2))))));

        triangle.setTriangleSideB((float) (Math.sqrt((Math.abs(Math.pow(triangle.getB().getX() - triangle.getC().getX(), 2))) +
                (Math.abs(Math.pow(triangle.getB().getY() - triangle.getC().getY(), 2))))));

        triangle.setTriangleSideC((float) (Math.sqrt((Math.abs(Math.pow(triangle.getA().getX() - triangle.getC().getX(), 2))) +
                (Math.abs(Math.pow(triangle.getA().getY() - triangle.getC().getY(), 2))))));
    }
}
