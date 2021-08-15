package com.epam.task2.jwd.repository;

import com.epam.task2.jwd.entity.Shape;
import com.epam.task2.jwd.entity.Triangle;
import com.epam.task2.jwd.entity.TriangleFactory;
import com.epam.task2.jwd.entity.TrianglePoint;
import com.epam.task2.jwd.exception.FilePointsNotExistException;
import com.epam.task2.jwd.exception.IncorrectArgumentException;
import com.epam.task2.jwd.service.impl.TriangleServiceImpl;
import com.epam.task2.jwd.service.impl.ValidationServiceImpl;
import com.epam.task2.jwd.specification.CRUDSpecification;
import com.epam.task2.jwd.specification.FilterSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Repository{
    private static final String FILENAME = "src/main/resources/points.txt";
    private static final Logger LOGGER = LoggerFactory.getLogger("TriangleDAOImpl.class");
    private static final String FILE_NOT_EXIST = "file points.txt not exist";
    private static final String NULL_EXCEPTION = "triangles is null";
    private static final String INCORRECT_ID_ARGUMENT = "id incorrect";
    private static final Pattern PATTERN = Pattern.compile("\\b[\\d]+\\b");
    private static final Path PATH = Paths.get(FILENAME);
    private List<String> lines = new ArrayList<>();
    private List<Integer> pointList = new ArrayList<>();

    private List<Shape> tList = new ArrayList<>();
    private Integer maxId = 0;


    public int size() {
        return tList.size();
    }

    public void createListOfTriangles() {

        read();
        validatePoints();

        for (int i = 0; i < pointList.size(); ) {

            Triangle triangle = TriangleFactory.createShape(new TrianglePoint(pointList.get(i++), pointList.get(i++)),
                    new TrianglePoint(pointList.get(i++), pointList.get(i++)),
                    new TrianglePoint(pointList.get(i++), pointList.get(i++)));
            if (ValidationServiceImpl.getInstance().isTriangle(triangle)) {
                triangle.setID(++maxId);
                TriangleServiceImpl.getInstance().perimeter(triangle);
                TriangleServiceImpl.getInstance().square(triangle);
                tList.add(triangle);
            }
        }
        LOGGER.info(tList.toString());
    }

    private void read() {
        try {
            readFile();
        } catch (FilePointsNotExistException | IOException e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(e.getStackTrace().toString());
        }
    }

    private void readFile() throws FilePointsNotExistException, IOException {
        if (fileExist()) {
            Stream<String> lineStream = Files.lines(PATH);
            lines = lineStream.collect(Collectors.toList());
            LOGGER.debug(lines.toString());
        } else {
            LOGGER.info("file doesn't exist");
            throw new FilePointsNotExistException(FILE_NOT_EXIST);
        }
    }

    private boolean fileExist() {

        if (Files.exists(PATH)) {
            return true;
        } else {
            return false;
        }
    }

    private void validatePoints() {
        for (String s : lines) {
            if (ValidationServiceImpl.getInstance().isCorrectData(s)) {
                Matcher matcher = PATTERN.matcher(s);
                while (matcher.find()) {
                    s = matcher.group();
                    pointList.add(Integer.valueOf(s));
                }
            }
        }
        LOGGER.debug(pointList.toString());
        LOGGER.info(pointList.toString());
    }

    private void rangeCheck(int index) throws IncorrectArgumentException {
        if (index >= size()) {
            throw new IncorrectArgumentException(INCORRECT_ID_ARGUMENT);
        }
    }

    public void set(int index, Triangle element){

        try {
            rangeCheck(index);
            tList.remove(index);
            tList.add(index, element);
        } catch (IncorrectArgumentException e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    public List<Shape> getList() {
        return tList;
    }



    public List<Shape> query(FilterSpecification specification) {
        List<Shape> list = tList.stream().filter(o -> specification.specify(o)).collect(Collectors.toList());
        LOGGER.info(list.toString());
        return list;
    }

    public List<Shape> CRUDquery(CRUDSpecification crudSpecification) {
        List<Shape> list = crudSpecification.specify(tList);
        LOGGER.info(tList.toString());
        return list;
    }

}
