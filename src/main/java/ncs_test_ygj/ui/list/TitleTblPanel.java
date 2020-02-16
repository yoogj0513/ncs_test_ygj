package ncs_test_ygj.ui.list;

import javax.swing.SwingConstants;

import ncs_test_ygj.dto.Title;

@SuppressWarnings("serial")
public class TitleTblPanel extends AbstractTblPanel<Title> {

	@Override
	protected void setTblWidthAlign() {
		tableSetWidth(50, 150);
		tableCellAlign(SwingConstants.CENTER, 0, 1);
	}

	@Override
	protected String[] getColNames() {
		return new String[] {"번호", "직책"};
	}

	@Override
	protected Object[] toArray(Title item) {
		return new Object[] {
				item.getTitleNo(), 
				item.getTitleName()
				};
	}

	@Override
	public void updateRow(Title item, int updateIdx) {
		model.setValueAt(item.getTitleNo(), updateIdx, 0);
		model.setValueAt(item.getTitleName(), updateIdx, 1);	
	}

}
