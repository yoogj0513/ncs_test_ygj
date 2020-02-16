package ncs_test_ygj.dao;

import java.util.List;

import ncs_test_ygj.dto.Department;

public interface DepartmentDao {
	Department selectDepartmentByCode(Department dept);
	List<Department> selectDepartmentByAll();
	
	int insertDepartment(Department dept);
	int updateDepartment(Department dept);
	int deleteDepartment(Department dept);
	String selectDepartmentLastCode();
}
