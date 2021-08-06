package com.epam.triangle.jwd.dao;

import com.epam.triangle.jwd.entity.Triangle;

import java.io.IOException;
import java.util.List;

public interface TriangleDAO {

    List<Triangle> create() throws IOException;

    Triangle read(int id);

    void update(Triangle triangle);

    void delete(int id);
}
