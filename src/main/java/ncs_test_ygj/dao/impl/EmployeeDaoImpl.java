package ncs_test_ygj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ncs_test_ygj.dao.EmployddDao;
import ncs_test_ygj.ds.MysqlDataSource;
import ncs_test_ygj.dto.Department;
import ncs_test_ygj.dto.Employee;
import ncs_test_ygj.dto.Title;
import ncs_test_ygj.util.LogUtil;

public class EmployeeDaoImpl implements EmployddDao {
	private static final EmployeeDaoImpl instance = new EmployeeDaoImpl();
	
	public static EmployeeDaoImpl getInstance(){
		return instance;
	}
	
	public EmployeeDaoImpl() {}

	@Override
	public Employee selectEmployeeByCode(Employee emp) {
		String sql = "select emp_no , emp_name , title , salary , gender , dno , hire_date " + 
					 "	from employee " + 
					 "	where emp_no = ?";
		try(Connection con = MysqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, emp.getEmpNo());
			LogUtil.prnLog(pstmt);
			try (ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					return getEmployee(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Employee getEmployee(ResultSet rs) throws SQLException {
		String empNo = rs.getString("emp_no");
		String empName = rs.getString("emp_name");
		Title title = new Title(rs.getString("title"));
		int salary = rs.getInt("salary");
		int gender = rs.getInt("gender");
		Department dno = new Department(rs.getString("dno"));
		Date hireDate = rs.getTimestamp("hire_date");
		return new Employee(empNo, empName, title, salary, gender, dno, hireDate);
	}

	@Override
	public List<Employee> selectEmployeeByAll() {
		String sql = "select emp_no , emp_name , title , salary , gender , dno , hire_date from employee";
		
		List<Employee> list = null;
		try(Connection con = MysqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			LogUtil.prnLog(pstmt);
			if(rs.next()) {
				list = new ArrayList<>();
				do {
					list.add(getEmployee(rs));
				} while(rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insertEmployee(Employee emp) {
		String sql = "insert into employee values(?, ?, ?, ?, ?, ?, ?)"; //7개
		try(Connection con = MysqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, emp.getEmpNo());
			pstmt.setString(2, emp.getEmpName());
			pstmt.setString(3, emp.getTitle().getTitleNo());
			pstmt.setInt(4, emp.getSalary());;
			pstmt.setInt(5, emp.getGender());
			pstmt.setString(6, emp.getDno().getDeptNo());
			pstmt.setTimestamp(7, new Timestamp(emp.getHireDate().getTime()));
			LogUtil.prnLog(pstmt);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateEmployee(Employee emp) {
		StringBuilder sql = new StringBuilder("update employee set ");
		if(emp.getEmpName() != null) sql.append("emp_name = ?, ");
		if(emp.getTitle() != null) sql.append("title = ?, ");
		if(emp.getSalary() != 0) sql.append("salary = ?, ");
		if(emp.getGender() != 0) sql.append("gender = ?, ");
		if(emp.getDno() != null) sql.append("dno = ?, ");
		if(emp.getHireDate() != null) sql.append("hire_date = ?, ");
		sql.replace(sql.lastIndexOf(","), sql.length(), " ");
		sql.append("where emp_no = ?");
		
		try(Connection con = MysqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql.toString())){
			int argCnt = 1;
			if(emp.getEmpName() != null) pstmt.setString(argCnt++, emp.getEmpName());
			if(emp.getTitle() != null) pstmt.setString(argCnt++, emp.getTitle().getTitleNo());
			if(emp.getSalary() != 0) pstmt.setInt(argCnt++, emp.getSalary());
			if(emp.getGender() != 0) pstmt.setInt(argCnt++, emp.getGender());
			if(emp.getDno() != null) pstmt.setString(argCnt++, emp.getDno().getDeptNo());
			if(emp.getHireDate() != null) pstmt.setTimestamp(argCnt++, new Timestamp(emp.getHireDate().getTime()));
			pstmt.setString(argCnt++, emp.getEmpNo());
			LogUtil.prnLog(pstmt);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteEmployee(Employee emp) {
		String sql = "delete from employee where emp_no = ?";
		try(Connection con = MysqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, emp.getEmpNo());
			LogUtil.prnLog(pstmt);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
