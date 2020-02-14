package ncs_test_ygj.dao;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ncs_test_ygj.dao.impl.DepartmentDaoImpl;
import ncs_test_ygj.dto.Department;
import ncs_test_ygj.util.LogUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentDaoTest {
	static DepartmentDao dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		dao = DepartmentDaoImpl.getInstance();
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
	public void test03SelectDepartmentByCode() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department dept = dao.selectDepartmentByCode(new Department("D001"));
		Assert.assertNotNull(dept);
		LogUtil.prnLog(dept);
	}

	@Test
	public void test02SelectDepartmentByAll() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		List<Department> lists = dao.selectDepartmentByAll();
		Assert.assertNotNull(lists);
		for(Department d : lists) LogUtil.prnLog(d);
	}

	@Test
	public void test01InsertDepartment() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department dept = new Department("D006", "회계", 5);
		int res = dao.insertDepartment(dept);
		Assert.assertEquals(1, res);
		LogUtil.prnLog(res);
		for(Department d : dao.selectDepartmentByAll()) LogUtil.prnLog(d);
	}

	@Test
	public void test04UpdateDepartment() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department dept = new Department("D006", "영업", 8);
		int res = dao.updateDepartment(dept);
		Assert.assertEquals(1, res);
		LogUtil.prnLog(res);
		for(Department d : dao.selectDepartmentByAll()) LogUtil.prnLog(d);
	}

	@Test
	public void test05DeleteDepartment() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department dept = new Department("D006");
		int res = dao.deleteDepartment(dept);
		Assert.assertEquals(1, res);
		LogUtil.prnLog(res);
		for(Department d : dao.selectDepartmentByAll()) LogUtil.prnLog(d);
	}

}
