import com.tzy.model.Department;
import com.tzy.model.Employee;
import com.tzy.repository.DepartmentDao;
import com.tzy.repository.DepartmentDaoImp;
import com.tzy.repository.EmployeeDao;
import com.tzy.repository.EmployeeDaoImp;
import org.junit.After;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.Table;
import java.time.LocalDate;

public class EmployeeDaoTest {

    EmployeeDao employeeDao;
    DepartmentDao departmentDao;
    private Employee employee1, employee2;
    private Department department;

    @BeforeEach
    public void setUp(){
        employeeDao = new EmployeeDaoImp();
        departmentDao = new DepartmentDaoImp();

        department = new Department();
        department.setId(1l);
        department.setDescription("sales department");
        department.setLocation("VA");
        department.setName("Sales");
        departmentDao.save(department);

        employee1 = new Employee();
        employee1.setName("TommyTao");
        employee1.setFirst_name("Tommy");
        employee1.setAddress("test");
        employee1.setLast_name("Tao");
        employee1.setEmail("test");
        employee1.setId(1L);
        employee1.setHired_date(LocalDate.now());
        employee1.setDepartment(department);

        employee2 = new Employee();
        employee2.setName("SheraNing");
        employee2.setFirst_name("Shera");
        employee2.setAddress("test");
        employee2.setLast_name("Ning");
        employee2.setEmail("test");
        employee2.setId(2L);
        employee2.setHired_date(LocalDate.now());
        employee2.setDepartment(department);

        employeeDao.save(employee1);
        employeeDao.save(employee2);




    }

    @AfterEach
    public void tearDown(){

        employeeDao.delete(employee1);
        employeeDao.delete(employee2);
        departmentDao.delete(department);

    }

    @Test
    public void EmployeeDaoTest(){

        //Assert.assertEquals(employeeDao.getEmployee().get(0).getDepartment().getName(),"Sales");
        Assert.assertEquals(departmentDao.getDepartments().get(0).getEmployees().size(),2);

    }


}
