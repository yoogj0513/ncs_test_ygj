package ncs_test_ygj.ui.content;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ncs_test_ygj.dto.Title;
import ncs_test_ygj.ui.exception.InvalidCheckException;
import ncs_test_ygj.ui.list.TitleTblPanel;
import ncs_test_ygj.ui.service.TitleUiService;

@SuppressWarnings("serial")
public class TitlePanel extends AbsItemPanel<Title> {
	private JTextField tfNo;
	private JTextField tfName;
	private static int lastNum;
	private TitleUiService service;
	private String lastCode;

	public TitlePanel() {
		service = new TitleUiService();
		lastCode = service.getLastCode();
		System.out.println(lastCode);
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
		
		JLabel lblName = new JLabel("직책명");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblName);
		
		tfName = new JTextField();
		add(tfName);
		tfName.setColumns(10);
		
		reloadData();
	}

	@Override
	public Title getItem() {
		validCheck();
		String titleNo = tfNo.getText().trim();
		String titleName = tfName.getText().trim();
		return new Title(titleNo, titleName);
	}

	@Override
	public void setItem(Title item) {
		tfNo.setText(item.getTitleNo());
		tfName.setText(item.getTitleName());
	}

	@Override
	public void clearTf() {
		tfName.setText("");
	}

	@Override
	public void validCheck() {
		if (tfNo.getText().contentEquals("") || tfName.getText().contentEquals("")) {
			throw new InvalidCheckException();
		}
	}

	@Override
	public void setCode(int num) {
		lastNum += num;
		tfNo.setText(String.format("T%03d", lastNum));
	}
	@Override
	public void reloadData() {
		tfNo.setText(String.format("T%03d", lastNum));
	}
	
}
