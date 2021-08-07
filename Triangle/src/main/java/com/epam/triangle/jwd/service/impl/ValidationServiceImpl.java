package com.epam.triangle.jwd.service.impl;

import com.epam.triangle.jwd.dao.DAOProvider;
import com.epam.triangle.jwd.entity.Triangle;
import com.epam.triangle.jwd.service.TriangleService;
import com.epam.triangle.jwd.service.ValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.List;


public class ValidationServiceImpl implements ValidationService {

    private static final Logger LOGGER = LoggerFactory.getLogger("ValidationServiceImpl.class");
    private static final String TEMPLATE = "-?\\d+\\.-?\\d+\\s-?\\d+\\.-?\\d+\\s-?\\d+\\.-?\\d+";
    private static DAOProvider  provider = DAOProvider.getInstance();
    private TriangleService  triangleService= new TriangleServiceImpl();



    @Override
    public boolean isTriangle(Triangle triangle) {
        if(0 == triangle.getA().getX() * (triangle.getB().getY() - triangle.getC().getY()) +
                triangle.getB().getX() * (triangle.getC().getY() - triangle.getA().getY()) +
                triangle.getC().getX() * (triangle.getA().getY() - triangle.getB().getY())) {

            LOGGER.info("NOT triangle - points are collinear");
            return false;
        }else{
            LOGGER.info("Is triangle - points are NOT collinear");
            return true;
        }
    }

    @Override
    public boolean isCorrectData(String str){

            if(str.matches(TEMPLATE)){
                LOGGER.info("data is correct");
               return true;
            }else{
                LOGGER.info("data is not correct");
               return false;
            }
    }

    @Override
    public boolean isRightTriangle(Triangle triangle) {

        triangleService.perimeter(triangle);

        if((((float)Math.sqrt((Math.pow(triangle.getTriangleSideA(),2) + Math.pow(triangle.getTriangleSideB(),2))) == triangle.getTriangleSideC()) ||
                (((float)Math.sqrt(Math.pow(triangle.getTriangleSideB(),2) + Math.pow(triangle.getTriangleSideC(),2)) == triangle.getTriangleSideA())) ||
                (((float)Math.sqrt(Math.pow(triangle.getTriangleSideC(),2) + Math.pow(triangle.getTriangleSideA(),2))== triangle.getTriangleSideB())))){
                LOGGER.info("rightTriangle");
                return true;
        }else{
            LOGGER.info("NOT rightTriangle");
            return false;
        }
    }

    public static void main(String[] args) throws IOException {

        List<Triangle> triangles= provider.getTriangleDAO().create();
        provider.getTriangleDAO().update(triangles.get(0));
    }
}
