package com.epam.triangle.jwd.dao;

import com.epam.triangle.jwd.entity.Triangle;
import com.epam.triangle.jwd.exception.FilePointsNotExistException;
import com.epam.triangle.jwd.exception.IncorrectArgumentException;
import com.epam.triangle.jwd.exception.TriangleNullException;

import java.io.IOException;
import java.util.List;

public interface TriangleDAO {

    List<Triangle> createListOfTriangles();

    void read();

    void update(Triangle triangle) throws FilePointsNotExistException, IOException, TriangleNullException;

    void delete(int id) throws IncorrectArgumentException;
}
