package com.epam.triangle.jwd.dao.imp;

import com.epam.triangle.jwd.dao.TriangleDAO;
import com.epam.triangle.jwd.entity.Triangle;
import com.epam.triangle.jwd.entity.TrianglePoint;
import com.epam.triangle.jwd.exception.FilePointsNotExistException;
import com.epam.triangle.jwd.exception.IncorrectArgumentException;
import com.epam.triangle.jwd.exception.TriangleNullException;
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

    private static final String FILENAME = "src/main/resources/points.txt";
    private static final Logger LOGGER = LoggerFactory.getLogger("TriangleDAOImpl.class");
    private static final String FILE_NOT_EXIST = "file points.txt not exist";
    private static final String NULL_EXCEPTION = "triangles is null";
    private static final String INCORRECT_ID_ARGUMENT = "id incorrect";
    private static final Pattern PATTERN = Pattern.compile("\\b[\\d]+\\b");
    private List<String> lines = new ArrayList<>();
    private List<Triangle> triangles = new ArrayList<>();
    private List<Integer> pointList = new ArrayList<>();
    private Path path = Paths.get(FILENAME);
    private ValidationService validationService = new ValidationServiceImpl();
    private TriangleService triangleService = new TriangleServiceImpl();

    @Override
    public List<Triangle> createListOfTriangles(){
        read();
        validatePoints();

        for (int i = 0; i < pointList.size(); ) {
            Triangle triangle = new Triangle(new TrianglePoint(pointList.get(i++), pointList.get(i++)),
                    new TrianglePoint(pointList.get(i++), pointList.get(i++)),
                    new TrianglePoint(pointList.get(i++), pointList.get(i++)));
            if(validationService.isTriangle(triangle)){
                triangles.add(triangle);
            }
        }
        LOGGER.info(triangles.toString());
        return triangles;
    }

    @Override
    public void read() {
        try {
            readFile();
        }catch (FilePointsNotExistException | IOException e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(e.getStackTrace().toString());
        }
    }

    @Override
    public void update(Triangle triangle) throws TriangleNullException {
        if(triangle != null) {
            triangleService.perimeter(triangle);
            triangleService.square(triangle);
        }else{
            throw new TriangleNullException(NULL_EXCEPTION);
        }
    }

    @Override
    public void delete(int id) throws IncorrectArgumentException {
        if(id >= 0){
            triangles.remove(id);
        }else{
            throw new IncorrectArgumentException(INCORRECT_ID_ARGUMENT);
        }
    }

    private void validatePoints(){
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

    private boolean fileExist() {

        if (Files.exists(path)) {
            return true;
        }else{
            return false;
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

    public static void main (String[] args){
        TriangleDAOImpl triangleDAO = new TriangleDAOImpl();
        triangleDAO.createListOfTriangles();
    }
}
