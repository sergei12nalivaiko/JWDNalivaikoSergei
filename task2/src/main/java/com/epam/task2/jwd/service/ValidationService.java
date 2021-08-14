package com.epam.task2.jwd.service;

import com.epam.task2.jwd.entity.Shape;
import com.epam.task2.jwd.entity.Triangle;

public interface ValidationService {
    boolean isTriangle(Triangle triangle);

    boolean isCorrectData(String str);

    boolean isRightTriangle(Triangle triangle);
}
