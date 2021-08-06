package com.epam.triangle.jwd.dao.imp;

import com.epam.triangle.jwd.dao.TriangleDAO;
import com.epam.triangle.jwd.entity.Triangle;
import com.epam.triangle.jwd.entity.TrianglePoint;
import com.epam.triangle.jwd.service.TriangleService;
import com.epam.triangle.jwd.service.ValidationService;
import com.epam.triangle.jwd.service.impl.TriangleServiceImpl;
import com.epam.triangle.jwd.service.impl.ValidationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TriangleDAOImpl implements TriangleDAO {

    private static final String FILENAME = "points.txt";
    private static final Logger LOGGER = LoggerFactory.getLogger("TriangleDAOImpl.class");
    private List<String> lines = new ArrayList<>();
    private List<Triangle> triangles = new ArrayList<>();
    private Path path = Paths.get(FILENAME);
    private ValidationService validationService = new ValidationServiceImpl();
    private TriangleService triangleService = new TriangleServiceImpl();

    @Override
    public List<Triangle> create() throws IOException {
        readFile();
        Pattern pattern = Pattern.compile("\\b[\\d]+\\b");
        List<Integer> pointList = new ArrayList<>();

        for (String s : lines) {
            if (validationService.isCorrectData(s)) {
                Matcher matcher = pattern.matcher(s);
                while (matcher.find()) {
                    s = matcher.group();
                    pointList.add(Integer.valueOf(s));
                }
            }
        }
        LOGGER.info(pointList.toString());

        for (int i = 0; i < pointList.size(); ) {
            Triangle triangle = new Triangle(new TrianglePoint(pointList.get(i), pointList.get(i + 1)),
                    new TrianglePoint(pointList.get(i + 2), pointList.get(i + 3)),
                    new TrianglePoint(pointList.get(i + 4), pointList.get(i + 5)));
            if(validationService.isTriangle(triangle)){
                triangles.add(triangle);
            }

            i += 6;
        }
        LOGGER.info(triangles.toString());
        return triangles;
    }

    @Override
    public Triangle read(int id) {

        return triangles.get(id);
    }

    @Override
    public void update(Triangle triangle) {
        triangleService.perimeter(triangle);
        triangleService.square(triangle);
    }

    @Override
    public void delete(int id) {
        triangles.remove(id);
    }

    private boolean fileExist() {

        if (Files.exists(path)) {
            return true;
        }
        return false;
    }

    private void readFile() throws IOException {
        if (fileExist()) {
            Stream<String> lineStream = Files.lines(path);
            lines = lineStream.collect(Collectors.toList());
        } else {
            LOGGER.info("file doesn't exist");
        }
    }
}
