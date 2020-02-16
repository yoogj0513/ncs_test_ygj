package ncs_test_ygj.ui.service;

import java.util.List;

import ncs_test_ygj.dao.DepartmentDao;
import ncs_test_ygj.dao.impl.DepartmentDaoImpl;
import ncs_test_ygj.dto.Department;

public class DepartmentUiService {
	private DepartmentDao deptDao = DepartmentDaoImpl.getInstance();

	public List<Department> showDepartmentList() {
		return deptDao.selectDepartmentByAll();
	}

	public void removeDepartment(Department delDept) {
		deptDao.deleteDepartment(delDept);
	}

	public void addDepartment(Department newDept) {
		deptDao.insertDepartment(newDept);
	}

	public void modifyDepartment(Department newDept) {
		deptDao.updateDepartment(newDept);
	}

	public String getLastCode() {
		return deptDao.selectDepartmentLastCode();
	}

	
}
