package com.epam.task2.jwd.repository;

import com.epam.task2.jwd.entity.Shape;
import com.epam.task2.jwd.entity.Triangle;
import com.epam.task2.jwd.entity.TriangleFactory;
import com.epam.task2.jwd.entity.TrianglePoint;
import com.epam.task2.jwd.exception.FilePointsNotExistException;
import com.epam.task2.jwd.service.TriangleService;
import com.epam.task2.jwd.service.ValidationService;
import com.epam.task2.jwd.service.impl.TriangleServiceImpl;
import com.epam.task2.jwd.service.impl.ValidationServiceImpl;
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

public class Repository<T extends Shape> {
    private static final String FILENAME = "src/main/resources/points.txt";
    private static final Logger LOGGER = LoggerFactory.getLogger("TriangleDAOImpl.class");
    private static final String FILE_NOT_EXIST = "file points.txt not exist";
    private static final String NULL_EXCEPTION = "triangles is null";
    private static final String INCORRECT_ID_ARGUMENT = "id incorrect";
    private static final Pattern PATTERN = Pattern.compile("\\b[\\d]+\\b");
    private List<String> lines = new ArrayList<>();
    private List<Integer> pointList = new ArrayList<>();
    private Path path = Paths.get(FILENAME);
    private ValidationService validationService = new ValidationServiceImpl();
    private TriangleService triangleService = new TriangleServiceImpl();
    private List<Shape> tList = new ArrayList<>();
    ;
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
            if (validationService.isTriangle(triangle)) {
                triangle.setId(++maxId);
                triangleService.perimeter(triangle);
                triangleService.square(triangle);
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
            Stream<String> lineStream = Files.lines(path);
            lines = lineStream.collect(Collectors.toList());
        } else {
            LOGGER.info("file doesn't exist");
            throw new FilePointsNotExistException(FILE_NOT_EXIST);
        }
    }

    private boolean fileExist() {

        if (Files.exists(path)) {
            return true;
        } else {
            return false;
        }
    }

    private void validatePoints() {
        for (String s : lines) {
            if (validationService.isCorrectData(s)) {
                Matcher matcher = PATTERN.matcher(s);
                while (matcher.find()) {
                    s = matcher.group();
                    pointList.add(Integer.valueOf(s));
                }
            }
        }
        LOGGER.info(pointList.toString());
    }

    private void rangeCheck(int index) {
        System.out.println(size());
        System.out.println(index);
        if (index >= size()) {
            throw new IndexOutOfBoundsException();
        }

    }

    public void set(int index, Triangle element) {
        rangeCheck(index);
        tList.remove(index);
        tList.add(index, element);
    }

    public List<Shape> getList() {
        return tList;
    }

    public boolean isEmpty() {
        return tList.isEmpty();
    }

    public boolean contains(Object o) {
        return tList.contains(o);
    }

    public boolean add(Shape shape) {
        return tList.add(shape);
    }

    public boolean remove(Object o) {
        return tList.remove(o);
    }

    public void sort(Comparator<? super Shape> c) {
        tList.sort(c);
    }

    public void clear() {
        tList.clear();
    }

    public Shape get(int index) {
        return tList.get(index);
    }

    public Shape remove(int index) {
        return tList.remove(index);
    }

    public int indexOf(Object o) {
        return tList.indexOf(o);
    }

    public int lastIndexOf(Object o) {
        return tList.lastIndexOf(o);
    }

    public List<Shape> query(Specification specification) {
        List<Shape> list = tList.stream().filter(o -> specification.specify(o)).collect(Collectors.toList());
        LOGGER.info(list.toString());
        return list;
    }
}
