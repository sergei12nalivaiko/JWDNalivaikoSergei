
import com.epam.task2.jwd.entity.Triangle;
import com.epam.task2.jwd.entity.TrianglePoint;
import com.epam.task2.jwd.service.ValidationService;
import com.epam.task2.jwd.service.impl.ValidationServiceImpl;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ValidationServiceImpTest {


    @Test(dataProvider = "provideIsTriangles")
    @Parameters({"triangle"})
    public void testIsTriangle(Triangle triangle) {

        boolean actual = ValidationServiceImpl.getInstance().isTriangle(triangle);
        boolean expected = true;
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "provideIsTriangles")
    public Object[][] provideDataIsTriangle() {

        return new Object[][]{
                {new Triangle(new TrianglePoint(0, 0), new TrianglePoint(10, 10), new TrianglePoint(20, 0))},
                {new Triangle(new TrianglePoint(0, 0), new TrianglePoint(10, 10), new TrianglePoint(20, 40))},
                {new Triangle(new TrianglePoint(-10, 0), new TrianglePoint(10, 10), new TrianglePoint(20, 0))},
                {new Triangle(new TrianglePoint(0, 10), new TrianglePoint(10, 10), new TrianglePoint(20, 0))},
                {new Triangle(new TrianglePoint(-10, 0), new TrianglePoint(10, 10), new TrianglePoint(20, 0))}
        };
    }

    @Test(dataProvider = "provideIsNotTriangles")
    public void testIsNotTriangle(Triangle triangle) {

        boolean actual = ValidationServiceImpl.getInstance().isTriangle(triangle);
        boolean expected = false;
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "provideIsNotTriangles")
    public Object[][] provideDataIsNotTriangle() {

        return new Object[][]{
                {new Triangle(new TrianglePoint(0, 0), new TrianglePoint(0, 10), new TrianglePoint(0, 20))},
                {new Triangle(new TrianglePoint(0, 0), new TrianglePoint(10, 10), new TrianglePoint(20, 20))},
                {new Triangle(new TrianglePoint(0, 0), new TrianglePoint(10, 0), new TrianglePoint(20, 0))},
        };
    }


    @Test(dataProvider = "provideIsRightTriangles")
    public void testIsRightTriangle(Triangle triangle) {
        boolean actual = ValidationServiceImpl.getInstance().isRightTriangle(triangle);
        boolean expected = true;
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "provideIsRightTriangles")
    public Object[][] provideDataIsRightTriangles() {

        return new Object[][]{
                {new Triangle(new TrianglePoint(0, 0), new TrianglePoint(0, 10), new TrianglePoint(10, 0))},
                {new Triangle(new TrianglePoint(0, 0), new TrianglePoint(10, 10), new TrianglePoint(20, 0))},
        };
    }

    @Test(dataProvider = "provideIsNotRightTriangles")
    public void testIsNotRightTriangle(Triangle triangle) {
        boolean actual = ValidationServiceImpl.getInstance().isRightTriangle(triangle);
        boolean expected = false;
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "provideIsNotRightTriangles")
    public Object[][] provideDataIsNotRightTriangles() {

        return new Object[][]{
                {new Triangle(new TrianglePoint(0, 0), new TrianglePoint(5, 3), new TrianglePoint(20, 10))},
                {new Triangle(new TrianglePoint(-10, 0), new TrianglePoint(-5, 40), new TrianglePoint(0, 0))},
        };
    }

    @Test(dataProvider = "provideIsCorrectData")
    public void testIsCorrectData(String points) {
        boolean actual = ValidationServiceImpl.getInstance().isCorrectData(points);
        boolean expected = true;
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "provideIsCorrectData")
    public Object[][] provideDataIsCorrectData() {

        return new Object[][]{
                {"0.0 10.10 20.20"},
                {"10.0 20.0 15.20"},
        };
    }

    @Test(dataProvider = "provideIsNotCorrectData")
    public void testIsNotCorrectData(String points) {
        boolean actual = ValidationServiceImpl.getInstance().isCorrectData(points);
        boolean expected = false;
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "provideIsNotCorrectData")
    public Object[][] provideDataIsNotCorrectData() {

        return new Object[][]{
                {"0.0z 10.10 20.20"},
                {"10.0 2-.0 15.20"},
        };
    }
}
