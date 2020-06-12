import com.tzy.model.Department;
import com.tzy.repository.DepartmentDao;
import com.tzy.repository.DepartmentDaoImp;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DepartmentDaoTest {

    private DepartmentDao departmentDao;
    private Department department;

    @BeforeEach
    public void setUp(){
        departmentDao = new DepartmentDaoImp();
        department = new Department();

        department.setName("test");
        department.setLocation("test");
        department.setDescription("test");
        department.setId(4L);
        departmentDao.save(department);
    }


    @AfterEach
    public void tearDown(){

        departmentDao.delete(department);
        departmentDao = null;
    }


    @Test
    public void departmentDaoTest(){

        Assert.assertEquals(departmentDao.getDepartments().get(1).getName(),"test");

    }
}
