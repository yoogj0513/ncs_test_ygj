package ncs_test_ygj.ui;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnEmp;
	private JButton btnDept;
	private JButton btnTitle;
	private JFrame frame1;
	private JFrame frame2;
	private JFrame frame3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		initialize();
	}
	private void initialize() {
		setTitle("ERP관리프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 120);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 3, 10, 10));
		
		btnEmp = new JButton("사원관리");
		btnEmp.addActionListener(this);
		contentPane.add(btnEmp);
		
		btnDept = new JButton("부서관리");
		btnDept.addActionListener(this);
		contentPane.add(btnDept);
		
		btnTitle = new JButton("직책관리");
		btnTitle.addActionListener(this);
		contentPane.add(btnTitle);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnTitle) {
			btnTitleActionPerformed(e);
		} 
		if (e.getSource() == btnDept) {
			btnDeptActionPerformed(e);
		}
		if (e.getSource() == btnEmp) {
			btnEmpActionPerformed(e);
		}
	}
	
	protected void btnEmpActionPerformed(ActionEvent e) {
		if(frame1 == null) {			
			frame1 = new JFrame();
			frame1.setBounds(100, 100, 500, 550);
			frame1.setTitle("사원관리");
			EmployeeUiPanel tp = new EmployeeUiPanel();
			frame1.getContentPane().add(tp);
			frame1.setVisible(true);		
		} else {
			if(frame1.isVisible()) {
				return;
			}
			EmployeeUiPanel tp = new EmployeeUiPanel();
			frame1.getContentPane().add(tp);
			frame1.setVisible(true);
		}
	}
	protected void btnDeptActionPerformed(ActionEvent e) {
		if(frame2 == null) {			
			frame2 = new JFrame();
			frame2.setBounds(100, 100, 400, 300);
			frame2.setTitle("부서관리");
			DepartmentUiPanel tp = new DepartmentUiPanel();
			frame2.getContentPane().add(tp);
			frame2.setVisible(true);
		} else {
			if(frame2.isVisible()) {
				return;
			}
			DepartmentUiPanel tp = new DepartmentUiPanel();
			frame2.getContentPane().add(tp);
			frame2.setVisible(true);
		}
	}
	protected void btnTitleActionPerformed(ActionEvent e) {
		if(frame3 == null) {			
			frame3 = new JFrame();
			frame3.setBounds(100, 100, 400, 300);
			frame3.setTitle("직책관리");
			TitleUiPanel tp = new TitleUiPanel();
			frame3.getContentPane().add(tp);
			frame3.setVisible(true);
		} else {
			if(frame3.isVisible()) {
				return;
			}
			TitleUiPanel tp = new TitleUiPanel();
			frame3.getContentPane().add(tp);
			frame3.setVisible(true);
		}
	}
}
