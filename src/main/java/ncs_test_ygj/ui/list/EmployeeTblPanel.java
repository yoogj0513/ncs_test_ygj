package ncs_test_ygj.ui.list;

import javax.swing.SwingConstants;

import ncs_test_ygj.dto.Employee;

@SuppressWarnings("serial")
public class EmployeeTblPanel extends AbstractTblPanel<Employee> {

	@Override
	protected void setTblWidthAlign() {
		tableSetWidth(100, 100, 60, 100, 60, 130, 110);
		tableCellAlign(SwingConstants.CENTER, 0, 1, 2, 4, 5, 6);	
		tableCellAlign(SwingConstants.RIGHT, 3);	
	}

	@Override
	protected String[] getColNames() {
		return new String[] {"번호", "사원명", "직책", "급여", "성별", "부서", "입사일"};
	}

	@Override
	protected Object[] toArray(Employee item) {
		return new Object[] {
				item.getEmpNo(),
				item.getEmpName(),
				String.format("%s", item.getTitle().getTitleName()),
				String.format("%,d", item.getSalary()),
				item.getGender() == 0 ? "남자" : "여자",
				String.format("%s(%d층)", item.getDno().getDeptName(), item.getDno().getFloor()),
				String.format("%tF", item.getHireDate())
		};
	}

	@Override
	public void updateRow(Employee item, int updateIdx) {
		model.setValueAt(item.getEmpNo(), updateIdx, 0);
		model.setValueAt(item.getEmpName(), updateIdx, 1);		
		model.setValueAt(item.getTitle().getTitleName(), updateIdx, 2);		
		model.setValueAt(String.format("%,d", item.getSalary()), updateIdx, 3);		
		model.setValueAt(item.getGender(), updateIdx, 4);		
		model.setValueAt(String.format("%s(%d층)", item.getDno().getDeptName(), item.getDno().getFloor()), updateIdx, 5);		
		model.setValueAt(String.format("%tF", item.getHireDate()), updateIdx, 6);		
	}

}
