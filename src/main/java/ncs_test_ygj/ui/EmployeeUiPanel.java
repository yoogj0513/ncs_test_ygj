package ncs_test_ygj.ui;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.border.EmptyBorder;

import ncs_test_ygj.dto.Employee;
import ncs_test_ygj.ui.content.EmployeePanel;
import ncs_test_ygj.ui.exception.InvalidCheckException;
import ncs_test_ygj.ui.list.EmployeeTblPanel;
import ncs_test_ygj.ui.service.EmployeeUiService;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class EmployeeUiPanel extends JPanel implements ActionListener {
	private JButton btnAdd;
	private JButton btnCancel;
	private EmployeeTblPanel pEmpList;
	private EmployeeUiService service;
	private EmployeePanel pEmpContent;

	public EmployeeUiPanel() {
		service = new EmployeeUiService();
		initialize();
	}

	private void initialize() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel pContent = new JPanel();
		pContent.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(pContent);
		pContent.setLayout(new BorderLayout(0, 0));

		pEmpContent = new EmployeePanel();
		pEmpContent.setService(service);
		pContent.add(pEmpContent, BorderLayout.CENTER);

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

		pEmpList = new EmployeeTblPanel();
		pEmpList.loadData(service.showEmployeeList());
		pEmpList.setPopupMenu(createPopupMenu());
		pList.add(pEmpList, BorderLayout.CENTER);
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
			if(e.getActionCommand().equals("수정")) {
				try {
					pEmpContent.reloadData();
					Employee upEmp = pEmpList.getSelectedItem();
					pEmpContent.setItem(upEmp);
					btnAdd.setText("수정");
				} catch (RuntimeException e3){
					JOptionPane.showMessageDialog(null, e3.getMessage());
				}
			}
			if(e.getActionCommand().equals("삭제")) {
				try {
					Employee delEmp = pEmpList.getSelectedItem();
					service.removeEmployee(delEmp);
					pEmpList.removeRow();
					pEmpList.loadData(service.showEmployeeList());
					JOptionPane.showMessageDialog(null, "삭제되었습니다");
				} catch (RuntimeException e3){
					JOptionPane.showMessageDialog(null, e3.getMessage());
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
		Employee upEmp = pEmpContent.getItem();
		service.modifyEmployee(upEmp);
		pEmpList.updateRow(upEmp, pEmpList.getSelectedRowIdx());
		
		btnAdd.setText("추가");
		pEmpContent.clearTf();
		pEmpContent.reloadData();
		pEmpList.loadData(service.showEmployeeList());
		JOptionPane.showMessageDialog(null, "직원 정보가 수정되었습니다.");
	}

	protected void btnAddActionPerformed(ActionEvent e) {
		try {
			Employee newEmp = pEmpContent.getItem();
			service.addEmployee(newEmp);
			pEmpList.addItem(newEmp);
			pEmpContent.clearTf();
			pEmpContent.setCode(1);
			pEmpList.loadData(service.showEmployeeList());
			JOptionPane.showMessageDialog(null, String.format("%s(%s)추가되었습니다.", newEmp.getEmpName(), newEmp.getEmpNo()));
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
		pEmpContent.clearTf();
		pEmpContent.reloadData();
		btnAdd.setText("추가");
	}
}
