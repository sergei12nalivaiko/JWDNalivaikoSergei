package com.epam.task2.jwd.registar.impl;

import com.epam.task2.jwd.entity.Triangle;
import com.epam.task2.jwd.registar.ShapeEvent;
import com.epam.task2.jwd.registar.ShapeObserver;
import com.epam.task2.jwd.repository.Repository;
import com.epam.task2.jwd.service.impl.TriangleServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TriangleObserver implements ShapeObserver {
    private static final Logger LOGGER = LoggerFactory.getLogger("TriangleObserver.class");

    private TriangleServiceImpl triangleService = new TriangleServiceImpl();
    private Repository repository = new Repository();

    @Override
    public void parameterChanged(ShapeEvent event) {
        repository.createListOfTriangles();
        Triangle triangle = (Triangle) event.getSource();
        LOGGER.info(triangle.toString());
        triangleService.perimeter(triangle);
        triangleService.square(triangle);
        LOGGER.info(triangle.toString());
        repository.set(triangle.getID() - 1, triangle);
        LOGGER.info(repository.getList().toString());
    }
}
