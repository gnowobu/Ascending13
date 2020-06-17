import com.tzy.model.Accounts;
import com.tzy.model.Department;
import com.tzy.model.Employee;
import com.tzy.repository.*;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AccountDaoTest {
    private DepartmentDao departmentDao;
    private EmployeeDao employeeDao;
    private AccountsDao accountsDao;
    private Accounts accounts;
    private Accounts accounts1;
    private Employee employee1;
    private Department department;

    @BeforeEach
    public void setUp(){
        departmentDao = new DepartmentDaoImp();
        employeeDao = new EmployeeDaoImp();
        accountsDao = new AccountsDaoImp();

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

        employee1.setHired_date(LocalDate.now());
        employee1.setDepartment(department);
        employeeDao.save(employee1);



        accounts = new Accounts();
        accounts.setId(1l);
        accounts.setAccount_type("Saving");
        accounts.setBalance(BigDecimal.valueOf(1000));
        accounts.setCreate_date(LocalDate.now());
        accounts.setEmployee(employee1);
        accountsDao.save(accounts);

        accounts1 = new Accounts();
        accounts1.setId(2l);
        accounts1.setAccount_type("Saving");
        accounts1.setBalance(BigDecimal.valueOf(1000));
        accounts1.setCreate_date(LocalDate.now());
        accounts1.setEmployee(employee1);

        accountsDao.save(accounts1);
    }

    @AfterEach
    public void tearDown(){
        accountsDao.delete(accounts);
        accountsDao.delete(accounts1);
        employeeDao.delete(employee1);
        departmentDao.delete(department);
        accountsDao = null;
        employeeDao = null;
        departmentDao = null;

    }

    @Test
    public void accountDaoTest(){
        Assert.assertEquals(accountsDao.getAccounts().size(),2);
        Assert.assertEquals(accountsDao.getAccounts().get(0).getEmployee().getName(),"TommyTao"); //use left join when lazy fetch

    }
}
