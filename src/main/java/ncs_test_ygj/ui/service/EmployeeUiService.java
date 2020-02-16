package ncs_test_ygj.ui.service;

import java.util.List;

import ncs_test_ygj.dao.DepartmentDao;
import ncs_test_ygj.dao.EmployeeDao;
import ncs_test_ygj.dao.TitleDao;
import ncs_test_ygj.dao.impl.DepartmentDaoImpl;
import ncs_test_ygj.dao.impl.EmployeeDaoImpl;
import ncs_test_ygj.dao.impl.TItleDaoImpl;
import ncs_test_ygj.dto.Department;
import ncs_test_ygj.dto.Employee;
import ncs_test_ygj.dto.Title;

public class EmployeeUiService {
	private EmployeeDao empDao;
	private DepartmentDao deptDao;
	private TitleDao titleDao;
	public EmployeeUiService() {
		empDao = EmployeeDaoImpl.getInstance();
		deptDao = DepartmentDaoImpl.getInstance();
		titleDao = TItleDaoImpl.getInstance();
	}
	public List<Employee> showEmployeeList() {
		return empDao.selectEmployeeByAll();
	}
	public List<Department> showDeptList() {
		return deptDao.selectDepartmentByAll();
	}
	public List<Title> showTitleList() {
		return titleDao.selectTitleByAll();
	}
	public void removeEmployee(Employee delEmp) {
		empDao.deleteEmployee(delEmp);
	}
	public void addEmployee(Employee newEmp) {
		empDao.insertEmployee(newEmp);
	}
	public void modifyEmployee(Employee upEmp) {
		empDao.updateEmployee(upEmp);
	}
	public int getLastCode(String year) {
		return empDao.selectEmployeeLastCode(year);
	}
	
}
