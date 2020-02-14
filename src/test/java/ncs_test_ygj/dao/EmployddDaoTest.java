package ncs_test_ygj.dao;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ncs_test_ygj.dao.impl.EmployeeDaoImpl;
import ncs_test_ygj.dto.Department;
import ncs_test_ygj.dto.Employee;
import ncs_test_ygj.dto.Title;
import ncs_test_ygj.util.LogUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployddDaoTest {
	static EmployddDao dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		dao = EmployeeDaoImpl.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		dao = null;
	}

	@Before
	public void setUp() throws Exception {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
	}

	@After
	public void tearDown() throws Exception {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		LogUtil.prnLog("\n");
	}

	@Test
	public void test03SelectEmployeeByCode() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Employee emp = dao.selectEmployeeByCode(new Employee("E017001"));
		Assert.assertNotNull(emp);
		LogUtil.prnLog(emp);
	}

	@Test
	public void test02SelectEmployeeByAll() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		List<Employee> lists = dao.selectEmployeeByAll();
		Assert.assertNotNull(lists);
		LogUtil.prnLog(lists);
		for(Employee e : lists) LogUtil.prnLog(e);
	}

	@Test
	public void test01InsertEmployee() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Calendar c = Calendar.getInstance();
		Date hireDate = new Date(c.getTimeInMillis());
		
		Employee emp = new Employee("E017006", "가사원", new Title("T005"), 2500000, 1, new Department("D003"), hireDate);
		LogUtil.prnLog(emp);
		int res = dao.insertEmployee(emp);
		Assert.assertEquals(1, res);
	}

	@Test
	public void test04UpdateEmployee() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Employee emp = new Employee("E017006", "다사원", new Title("T004"), 3000000, 1, new Department("D004"), new Date());
		int res = dao.updateEmployee(emp);
		Assert.assertEquals(1, res);
		LogUtil.prnLog(res);
		for(Employee e : dao.selectEmployeeByAll()) LogUtil.prnLog(e);
	}

	@Test
	public void test05DeleteEmployee() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Employee emp = new Employee("E017006");
		int res = dao.deleteEmployee(emp);
		Assert.assertEquals(1, res);
		LogUtil.prnLog(res);
		for(Employee e : dao.selectEmployeeByAll()) LogUtil.prnLog(e);
	}

}
