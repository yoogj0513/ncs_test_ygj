package ncs_test_ygj.dao;

import java.util.List;

import ncs_test_ygj.dto.Employee;

public interface EmployddDao {
	Employee selectEmployeeByCode(Employee emp);
	List<Employee> selectEmployeeByAll();
	
	int insertEmployee(Employee emp);
	int updateEmployee(Employee emp);
	int deleteEmployee(Employee emp);
	
}
