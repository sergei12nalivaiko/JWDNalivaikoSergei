package com.epam.task2.jwd.service.impl;

import com.epam.task2.jwd.entity.Triangle;
import com.epam.task2.jwd.service.ValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public final class ValidationServiceImpl implements ValidationService {

    private static  final ValidationService INSTANCE = new ValidationServiceImpl();
    private static final Logger LOGGER = LoggerFactory.getLogger("ValidationServiceImpl.class");
    private static final String TEMPLATE = "-?\\d+\\.-?\\d+\\s-?\\d+\\.-?\\d+\\s-?\\d+\\.-?\\d+";

    private ValidationServiceImpl() {
    }

    public static ValidationService getInstance(){
        return INSTANCE;
    }

    @Override
    public boolean isTriangle(Triangle triangle) {
        if (0 == triangle.getA().getX() * (triangle.getB().getY() - triangle.getC().getY()) +
                triangle.getB().getX() * (triangle.getC().getY() - triangle.getA().getY()) +
                triangle.getC().getX() * (triangle.getA().getY() - triangle.getB().getY())) {

            LOGGER.info("NOT triangle - points are collinear");
            return false;
        } else {
            LOGGER.info("Is triangle - points are NOT collinear");
            return true;
        }
    }

    @Override
    public boolean isCorrectData(String str) {

        if (str.matches(TEMPLATE)) {
            LOGGER.info("data is correct");
            return true;
        } else {
            LOGGER.info("data is not correct");
            return false;
        }
    }

    @Override
    public boolean isRightTriangle(Triangle triangle) {

        float triangleSideA = ((float) (Math.sqrt((Math.abs(Math.pow(triangle.getA().getX() - triangle.getB().getX(), 2))) +
                (Math.abs(Math.pow(triangle.getA().getY() - triangle.getB().getY(), 2))))));

        float triangleSideB = ((float) (Math.sqrt((Math.abs(Math.pow(triangle.getB().getX() - triangle.getC().getX(), 2))) +
                (Math.abs(Math.pow(triangle.getB().getY() - triangle.getC().getY(), 2))))));

        float triangleSideC = ((float) (Math.sqrt((Math.abs(Math.pow(triangle.getA().getX() - triangle.getC().getX(), 2))) +
                (Math.abs(Math.pow(triangle.getA().getY() - triangle.getC().getY(), 2))))));


        if ((((float) Math.sqrt((Math.pow(triangleSideA, 2) + Math.pow(triangleSideB, 2)))
                == triangleSideC)
                || (((float) Math.sqrt(Math.pow(triangleSideB, 2) + Math.pow(triangleSideC, 2))
                == triangleSideA))
                || (((float) Math.sqrt(Math.pow(triangleSideC, 2) + Math.pow(triangleSideA, 2))
                == triangleSideB)))) {
            LOGGER.info("rightTriangle");
            return true;
        } else {
            LOGGER.info("NOT rightTriangle");
            return false;
        }
    }
}
