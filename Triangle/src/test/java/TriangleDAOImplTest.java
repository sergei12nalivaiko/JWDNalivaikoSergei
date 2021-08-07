import com.epam.triangle.jwd.dao.TriangleDAO;
import com.epam.triangle.jwd.dao.imp.TriangleDAOImpl;
import com.epam.triangle.jwd.entity.Triangle;
import com.epam.triangle.jwd.exception.FilePointsNotExistException;
import com.epam.triangle.jwd.exception.IncorrectArgumentException;
import com.epam.triangle.jwd.exception.TriangleNullException;
import org.testng.annotations.Test;

import java.io.IOException;

public class TriangleDAOImplTest {
    TriangleDAO triangleDAO = new TriangleDAOImpl();

    @Test(expectedExceptions = TriangleNullException.class)
    public void updateTest() throws IOException, TriangleNullException, FilePointsNotExistException {

        Triangle triangle = null;

        triangleDAO.update(triangle);
    }

    @Test(expectedExceptions = IncorrectArgumentException.class)
    public void deleteTest() throws IncorrectArgumentException {

        triangleDAO.delete(-1);
    }
}
