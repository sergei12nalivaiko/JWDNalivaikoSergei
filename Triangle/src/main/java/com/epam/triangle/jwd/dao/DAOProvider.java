package com.epam.triangle.jwd.dao;

import com.epam.triangle.jwd.dao.imp.TriangleDAOImpl;

public final class DAOProvider {
    private  static final DAOProvider instance = new DAOProvider();

    private  TriangleDAO  triangleDAO= new TriangleDAOImpl();

    private DAOProvider(){}

    public static DAOProvider getInstance(){
        return instance;
    }
    public TriangleDAO getTriangleDAO() {
       return triangleDAO;
   }
}
