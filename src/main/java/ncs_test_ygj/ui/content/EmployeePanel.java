package ncs_test_ygj.ui.content;

import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import ncs_test_ygj.dto.Department;
import ncs_test_ygj.dto.Employee;
import ncs_test_ygj.dto.Title;
import ncs_test_ygj.ui.exception.InvalidCheckException;
import ncs_test_ygj.ui.service.EmployeeUiService;

@SuppressWarnings("serial")
public class EmployeePanel extends AbsItemPanel<Employee> {
	private JTextField tfNo;
	private JTextField tfName;
	private JDateChooser tfHireDate;
	private JComboBox<Title> cmbTitle;
	private JComboBox<Department> cmbDept;
	private JSpinner spSalary;
	private ButtonGroup genderG;
	private EmployeeUiService service;
	private JRadioButton rdo1;
	private JRadioButton rdo2;
	
	private static int lastNum;
	private int lastCode;

	public EmployeePanel() {
		service = new EmployeeUiService();
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

		JLabel lblName = new JLabel("사원명");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblName);

		tfName = new JTextField();
		add(tfName);
		tfName.setColumns(10);

		JLabel lblTitle = new JLabel("직책");
		lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblTitle);

		cmbTitle = new JComboBox<>();
		add(cmbTitle);

		JLabel lblSalary = new JLabel("급여");
		lblSalary.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblSalary);

		spSalary = new JSpinner();
		spSalary.setModel(new SpinnerNumberModel(1500000, 1000000, 5000000, 100000));
		add(spSalary);

		JLabel lblGender = new JLabel("성별");
		lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblGender);

		JPanel pGender = new JPanel();
		add(pGender);
		pGender.setLayout(new GridLayout(0, 2, 0, 0));
		
		genderG = new ButtonGroup();
		
		rdo1 = new JRadioButton("남");
		rdo1.setSelected(true);
		pGender.add(rdo1);
		
		rdo2 = new JRadioButton("여");
		pGender.add(rdo2);
		
		genderG.add(rdo1);
		genderG.add(rdo2);
				
		
		JLabel lblDept = new JLabel("부서");
		lblDept.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblDept);

		cmbDept = new JComboBox<>();
		add(cmbDept);

		JLabel lblHireDate = new JLabel("입사일");
		lblHireDate.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblHireDate);

		
		tfHireDate = new JDateChooser(new Date(), "yyyy-MM-dd");
		add(tfHireDate);
		makeCode();
	}

	public void setService(EmployeeUiService service) {
		this.service = service;
		setCmbDeptList(service.showDeptList());
		setCmbTitleList(service.showTitleList());
	}
	
	public void setCmbDeptList(List<Department> deptList) {
		DefaultComboBoxModel<Department> model = new DefaultComboBoxModel<>(new Vector<>(deptList));
		cmbDept.setModel(model);
		cmbDept.setSelectedIndex(-1);
	}
	
	public void setCmbTitleList(List<Title> titleList) {
		DefaultComboBoxModel<Title> model = new DefaultComboBoxModel<>(new Vector<>(titleList));
		cmbTitle.setModel(model);
		cmbTitle.setSelectedIndex(-1);
	}

	@Override
	public Employee getItem() {
		validCheck();
		String empNo = tfNo.getText().trim();
		String empName = tfName.getText().trim();
		Title title = (Title) cmbTitle.getSelectedItem();
		int salary = (int) spSalary.getValue();
		
		int gender = 0;
		if(rdo1.isSelected()) {
			gender = 0;
		} else if(rdo2.isSelected()) {
			gender = 1;
		}
		System.out.println("gender = " + gender);
		Department dno = (Department) cmbDept.getSelectedItem();
		Date hireDate = tfHireDate.getDate();
		return new Employee(empNo, empName, title, salary, gender, dno, hireDate);
	}

	@Override
	public void setItem(Employee item) {
		tfNo.setText(item.getEmpNo());
		tfName.setText(item.getEmpName());
		cmbTitle.setSelectedItem(item.getTitle());
		spSalary.setValue(item.getSalary());
		if(item.getGender() == 0) {
			rdo1.setSelected(true);
		} else if (item.getGender() == 1){
			rdo2.setSelected(true);
		};
		cmbDept.setSelectedItem(item.getDno());
		tfHireDate.setDate(item.getHireDate());
	}

	@Override
	public void clearTf() {
		makeCode();
		tfName.setText("");
		cmbTitle.setSelectedIndex(-1);
		spSalary.setValue(1500000);
		rdo1.setSelected(true);
		cmbDept.setSelectedIndex(-1);
		tfHireDate.setDate(new Date());
	}

	@Override
	public void validCheck() {
		if (tfName.getText().contentEquals("") || tfName.getText().contentEquals("") 
				|| cmbDept.getSelectedIndex() == -1 || cmbTitle.getSelectedIndex() == -1) {
			throw new InvalidCheckException();
		}
	}

	@Override
	public void setCode(int num) {
		lastNum += num;
		makeCode();
	}

	@Override
	public void reloadData() {
		makeCode();
	}

	private void makeCode() {
		String getYear = tfHireDate.getJCalendar().getYearChooser().getYear()+"";
		String year = getYear.substring(getYear.length()-2, getYear.length());
		
		lastCode = service.getLastCode("%"+ year +"%");
		lastNum = 1 + lastCode;
		
		String empNo = String.format("E0%s%s", year, String.format("%03d", lastNum));
	
		tfNo.setText(empNo);
	}
}
