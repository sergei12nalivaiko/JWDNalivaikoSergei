import com.epam.triangle.jwd.entity.Triangle;
import com.epam.triangle.jwd.entity.TrianglePoint;
import com.epam.triangle.jwd.service.TriangleService;
import com.epam.triangle.jwd.service.impl.TriangleServiceImpl;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TriangleServiceImplTest {
    TriangleService triangleService = new TriangleServiceImpl();

    @Test(dataProvider = "providePerimeter")
    public void testPerimeter(Triangle triangle) {

        triangleService.perimeter(triangle);

        Assert.assertEquals(triangle.getPerimeter(), 0.1, 34.1421356);

    }

    @DataProvider(name = "providePerimeter")
    public Object[][] providePerimeter() {

        return new Object[][]{
                {new Triangle(new TrianglePoint(0, 0), new TrianglePoint(10, 10), new TrianglePoint(10, 0))},
        };
    }

    @Test(dataProvider = "provideSquare")
    public void testSquare(Triangle triangle) {

        triangleService.square(triangle);

        Assert.assertEquals(triangle.getSquare(),0.0,50.0);

    }

    @DataProvider(name = "provideSquare")
    public Object[][] provideSquare() {

        return new Object[][]{
                {new Triangle(new TrianglePoint(0, 0), new TrianglePoint(10, 10), new TrianglePoint(10, 0))},
        };
    }
}
