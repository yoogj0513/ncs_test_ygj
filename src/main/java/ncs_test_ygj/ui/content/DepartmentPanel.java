package ncs_test_ygj.ui.content;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ncs_test_ygj.dto.Department;
import ncs_test_ygj.ui.exception.InvalidCheckException;
import ncs_test_ygj.ui.service.DepartmentUiService;

@SuppressWarnings("serial")
public class DepartmentPanel extends AbsItemPanel<Department> {
	private JTextField tfNo;
	private JTextField tfName;
	private JTextField tfFloor;
	private DepartmentUiService service;
	private static int lastNum;
	private String lastCode;

	public DepartmentPanel() {
		service = new DepartmentUiService();
		lastCode = service.getLastCode();
		lastNum = 1 + Integer.parseInt(lastCode.substring(lastCode.length()-1, lastCode.length()));
		initialize();
	}
	private void initialize() {
		setLayout(new GridLayout(0, 2, 10, 5));
		
		JLabel lblNo = new JLabel("번호");
		lblNo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblNo);
		
		tfNo = new JTextField();
		add(tfNo);
		tfNo.setColumns(10);
		tfNo.setEditable(false);
		
		JLabel lblName = new JLabel("부서명");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblName);
		
		tfName = new JTextField();
		add(tfName);
		tfName.setColumns(10);
		
		JLabel lblFloor = new JLabel("위치");
		lblFloor.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblFloor);
		
		tfFloor = new JTextField();
		add(tfFloor);
		tfFloor.setColumns(10);
		
		reloadData();
	}
	
	@Override
	public Department getItem() {
		validCheck();
		String deptNo = tfNo.getText().trim();
		String deptName = tfName.getText().trim();
		int floor = Integer.parseInt(tfFloor.getText().trim());
		return new Department(deptNo, deptName, floor);
	}

	@Override
	public void setItem(Department item) {
		tfNo.setText(item.getDeptNo());
		tfName.setText(item.getDeptName());
		tfFloor.setText(item.getFloor()+"");
	}

	@Override
	public void clearTf() {
		tfName.setText("");
		tfFloor.setText("");
	}

	@Override
	public void validCheck() {
		if(tfNo.getText().contentEquals("") || tfName.getText().contentEquals("") || tfFloor.getText().contentEquals("")) {
			throw new InvalidCheckException();
		}
	}
	
	@Override
	public void setCode(int num) {
		lastNum += num;
		tfNo.setText(String.format("D%03d", lastNum));
	}
	
	@Override
	public void reloadData() {
		tfNo.setText(String.format("D%03d", lastNum));
	}
	
}