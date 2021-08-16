import com.epam.task2.jwd.entity.Shape;
import com.epam.task2.jwd.entity.TriangleFactory;
import com.epam.task2.jwd.entity.TrianglePoint;
import com.epam.task2.jwd.repository.Repository;
import com.epam.task2.jwd.specification.CRUDSpecification;
import com.epam.task2.jwd.specification.FilterSpecification;
import com.epam.task2.jwd.specification.impl.AddShapeSpecification;
import com.epam.task2.jwd.specification.impl.FindByIdSpecification;
import com.epam.task2.jwd.specification.impl.PerimeterBetweenSpecification;
import com.epam.task2.jwd.specification.impl.SquareBetweenSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class RepositoryTest {

    private List<Shape> shapeList;
    private CRUDSpecification crudSpecification;

    @Test
    public void testAddTriangleQuery() {
        Repository repository = new Repository();
        Shape shape = TriangleFactory.createShape(new TrianglePoint(0, 0), new TrianglePoint(10, 10), new TrianglePoint(10, 0));
        CRUDSpecification crudSpecification = new AddShapeSpecification(shape);
        shapeList = repository.CRUDquery(crudSpecification);
        Assert.assertEquals(shape, shapeList.get(0));
        shapeList.clear();
    }

    @Test
    public void testFindByIdQuery() {
        Repository repository = new Repository();
        shapeList.clear();
        Shape shape = TriangleFactory.createShape(new TrianglePoint(0, 0), new TrianglePoint(10, 10), new TrianglePoint(10, 0));
        Shape shape1 = TriangleFactory.createShape(new TrianglePoint(10, 10), new TrianglePoint(20, 40), new TrianglePoint(10, 0));
        Shape shape2 = TriangleFactory.createShape(new TrianglePoint(0, 0), new TrianglePoint(20, 20), new TrianglePoint(10, 0));
        crudSpecification = new AddShapeSpecification(shape);
        shapeList = repository.CRUDquery(crudSpecification);
        crudSpecification = new AddShapeSpecification(shape1);
        shapeList = repository.CRUDquery(crudSpecification);
        crudSpecification = new AddShapeSpecification(shape2);
        shapeList = repository.CRUDquery(crudSpecification);
        FilterSpecification filterSpecification = new FindByIdSpecification(2);
        shapeList = repository.query(filterSpecification);
        Assert.assertEquals(shape1,shapeList.get(0));
        shapeList.clear();
    }

    @Test
    public void testPerimeterBetween() {
        Repository repository = new Repository();
        shapeList.clear();
        List<Shape> filterList;
        repository.createListOfTriangles();
        shapeList = repository.getList();
        FilterSpecification filterSpecification = new PerimeterBetweenSpecification(100,10000);
        filterList = repository.query(filterSpecification);
        Assert.assertEquals(repository.getList().get(2),filterList.get(0));
    }

    @Test
    public void testSquareBetween() {
        Repository repository = new Repository();
        shapeList.clear();
        List<Shape> filterList;
        repository.createListOfTriangles();
        shapeList = repository.getList();
        FilterSpecification filterSpecification = new SquareBetweenSpecification(100,10000);
        filterList = repository.query(filterSpecification);
        System.out.println(filterList);
        Assert.assertEquals(repository.getList().get(2),filterList.get(0));
    }
}

