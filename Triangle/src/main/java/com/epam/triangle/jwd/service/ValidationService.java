package com.epam.triangle.jwd.service;

import com.epam.triangle.jwd.entity.Triangle;

public interface ValidationService {
    boolean isTriangle(Triangle triangle);

    boolean isCorrectData(String str);

    boolean isRightTriangle(Triangle triangle);
}
