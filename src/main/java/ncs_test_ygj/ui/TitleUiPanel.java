package ncs_test_ygj.ui;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.border.EmptyBorder;

import ncs_test_ygj.dto.Title;
import ncs_test_ygj.ui.content.TitlePanel;
import ncs_test_ygj.ui.exception.InvalidCheckException;
import ncs_test_ygj.ui.list.TitleTblPanel;
import ncs_test_ygj.ui.service.TitleUiService;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class TitleUiPanel extends JPanel implements ActionListener {
	private JButton btnAdd;
	private JButton btnCancel;
	private TitleTblPanel pTitleList;
	private TitlePanel pTitleContent;
	private TitleUiService service;

	public TitleUiPanel() {
		service = new TitleUiService(); 
		initialize();
	}

	private void initialize() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel pContent = new JPanel();
		pContent.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(pContent);
		pContent.setLayout(new BorderLayout(0, 0));

		pTitleContent = new TitlePanel();
		pContent.add(pTitleContent, BorderLayout.CENTER);

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

		pTitleList = new TitleTblPanel();
		pTitleList.loadData(service.showTitleList());
		pTitleList.setPopupMenu(createPopupMenu());
		pList.add(pTitleList, BorderLayout.CENTER);
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
					Title updateTitle = pTitleList.getSelectedItem();
					pTitleContent.setItem(updateTitle);
					btnAdd.setText("수정");
				} catch (RuntimeException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				
			}
			if(e.getActionCommand().equals("삭제")) {
				try {
					Title dleTitle = pTitleList.getSelectedItem();
					service.removeTitle(dleTitle);
					pTitleList.removeRow();
					pTitleList.loadData(service.showTitleList());
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
		Title newTitle = pTitleContent.getItem();
		service.modifyTitle(newTitle);
		pTitleList.updateRow(newTitle, pTitleList.getSelectedRowIdx());
		btnAdd.setText("추가");
		pTitleContent.clearTf();
		pTitleContent.reloadData();
		pTitleList.loadData(service.showTitleList());
		JOptionPane.showMessageDialog(null, "직책이 수정되었습니다");
	}

	protected void btnAddActionPerformed(ActionEvent e) {
		try {
			Title newTitle = pTitleContent.getItem();
			service.addTitle(newTitle);
			pTitleList.addItem(newTitle);
			pTitleContent.clearTf();
			pTitleList.loadData(service.showTitleList());
			pTitleContent.setCode(1);
			JOptionPane.showMessageDialog(null, "직책이 추가되었습니다.");
		}catch(InvalidCheckException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}catch(Exception e1) {
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
		pTitleContent.clearTf();
		pTitleContent.reloadData();
		btnAdd.setText("추가");
	}
}
