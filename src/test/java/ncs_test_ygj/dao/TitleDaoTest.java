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

import ncs_test_ygj.dao.impl.TItleDaoImpl;
import ncs_test_ygj.dto.Title;
import ncs_test_ygj.util.LogUtil;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TitleDaoTest {
	static TitleDao dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		dao = TItleDaoImpl.getInstance();
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
	public void test03SelectTitleByCode() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Title t = dao.selectTitleByCode(new Title("T006"));
		Assert.assertNotNull(t);
		LogUtil.prnLog(t);
	}

	@Test
	public void test02SelectTitleByAll() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		List<Title> lists = dao.selectTitleByAll();
		Assert.assertNotNull(lists);
		
		for(Title t : lists) LogUtil.prnLog(t);
	}

	@Test
	public void test01InsertTitle() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Title newTitle = new Title("T006", "인턴");
		int res = dao.insertTitle(newTitle);
		Assert.assertEquals(1, res);
		LogUtil.prnLog(res);
	}

	@Test
	public void test04UpdateTitle() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Title newTitle = new Title("T006", "무기계약직");
		int res = dao.updateTitle(newTitle);
		Assert.assertEquals(1, res);
		LogUtil.prnLog(res);
	}

	@Test
	public void test05DeleteTitle() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Title newTitle = new Title("T006");
		int res = dao.deleteTitle(newTitle);
		Assert.assertEquals(1, res);
		LogUtil.prnLog(res);
	}

}
