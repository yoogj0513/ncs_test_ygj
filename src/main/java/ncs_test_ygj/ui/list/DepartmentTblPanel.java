package ncs_test_ygj.ui.list;

import javax.swing.SwingConstants;

import ncs_test_ygj.dto.Department;

@SuppressWarnings("serial")
public class DepartmentTblPanel extends AbstractTblPanel<Department> {
	public DepartmentTblPanel() {
	}

	@Override
	protected void setTblWidthAlign() {
		tableSetWidth(50, 150, 50);
		tableCellAlign(SwingConstants.CENTER, 0, 1, 2);
	}

	@Override
	protected String[] getColNames() {
		return new String[] {"번호", "부서명", "위치"};
	}

	@Override
	protected Object[] toArray(Department item) {
		return new Object[] {
				item.getDeptNo(),
				item.getDeptName(),
				item.getFloor()
		};
	}

	@Override
	public void updateRow(Department item, int updateIdx) {
		model.setValueAt(item.getDeptNo(), updateIdx, 0);
		model.setValueAt(item.getDeptName(), updateIdx, 1);
		model.setValueAt(item.getFloor(), updateIdx, 2);
		
	}

}
