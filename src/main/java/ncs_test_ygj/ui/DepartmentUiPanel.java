package ncs_test_ygj.ui;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.BoxLayout;
import javax.swing.border.EmptyBorder;

import ncs_test_ygj.dto.Department;
import ncs_test_ygj.ui.content.DepartmentPanel;
import ncs_test_ygj.ui.exception.InvalidCheckException;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import ncs_test_ygj.ui.list.DepartmentTblPanel;
import ncs_test_ygj.ui.service.DepartmentUiService;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class DepartmentUiPanel extends JPanel implements ActionListener {
	private DepartmentTblPanel pDeptList;
	private DepartmentUiService service;
	private DepartmentPanel pDepartment;
	private JButton btnAdd;
	private JButton btnCancel;
	

	public DepartmentUiPanel() {
		service = new DepartmentUiService();
		initialize();
	}

	private void initialize() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel pContetn = new JPanel();
		pContetn.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(pContetn);
		pContetn.setLayout(new BorderLayout(0, 0));

		pDepartment = new DepartmentPanel();
		pContetn.add(pDepartment, BorderLayout.CENTER);

		JPanel pBtns = new JPanel();
		add(pBtns);

		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtns.add(btnAdd);

		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtns.add(btnCancel);

		JPanel pList = new JPanel();
		pList.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(pList);
		pList.setLayout(new BorderLayout(0, 0));

		pDeptList = new DepartmentTblPanel();
		pDeptList.loadData(service.showDepartmentList());
		pDeptList.setPopupMenu(createPopupMenu());
		pList.add(pDeptList, BorderLayout.CENTER);
	}
	
	private JPopupMenu createPopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();

		JMenuItem updateItem = new JMenuItem("수정");
		updateItem.addActionListener(myPopMenuListener);
		popMenu.add(updateItem);

		JMenuItem deleteItem = new JMenuItem("삭제");
		deleteItem.addActionListener(myPopMenuListener);
		popMenu.add(deleteItem);

		return popMenu;
	}

	ActionListener myPopMenuListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("수정")) {
				try {
					Department upDept = pDeptList.getSelectedItem();
					pDepartment.setItem(upDept);
					btnAdd.setText("수정");
				} catch (RuntimeException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				
			}
			if (e.getActionCommand().equals("삭제")) {
				try {
					Department delDept = pDeptList.getSelectedItem();
					service.removeDepartment(delDept);
					pDeptList.removeRow();
					pDeptList.loadData(service.showDepartmentList());
					JOptionPane.showMessageDialog(null, "삭제되었습니다.");
				} catch (RuntimeException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				
			}
		}
	};
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			btnCancelActionPerformed(e);
		}
		if (e.getSource() == btnAdd) {
			if(e.getActionCommand().contentEquals("수정")) {
				btnUpdateActionPerformed(e);
			} else {				
				btnAddActionPerformed(e);
			}
		}
	}

	private void btnUpdateActionPerformed(ActionEvent e) {
		Department newDept = pDepartment.getItem();
		service.modifyDepartment(newDept);
		pDeptList.updateRow(newDept, pDeptList.getSelectedRowIdx());
		btnAdd.setText("추가");
		pDepartment.clearTf();
		pDepartment.reloadData();
		pDeptList.loadData(service.showDepartmentList());
		JOptionPane.showMessageDialog(null, "부서가 수정되었습니다.");
	}
	
	protected void btnAddActionPerformed(ActionEvent e) {
		try {
			Department newDept = pDepartment.getItem();
			service.addDepartment(newDept);
			pDeptList.addItem(newDept);
			pDepartment.clearTf();
			pDepartment.setCode(1);
			pDeptList.loadData(service.showDepartmentList());
			JOptionPane.showMessageDialog(null, "부서가 추가되었습니다.");
		} catch (InvalidCheckException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (Exception e1) {
			SQLException e2 = (SQLException) e1;
			if (e2.getErrorCode() == 1062) {
				JOptionPane.showMessageDialog(null, "번호가 중복");
				System.err.println(e2.getMessage());
				return;
			}
			e1.printStackTrace();
		}
	}

	protected void btnCancelActionPerformed(ActionEvent e) {
		pDepartment.clearTf();
		pDepartment.reloadData();
		btnAdd.setText("추가");
	}
}
