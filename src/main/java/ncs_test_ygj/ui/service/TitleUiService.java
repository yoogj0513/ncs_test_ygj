package ncs_test_ygj.ui.service;

import java.util.List;

import ncs_test_ygj.dao.TitleDao;
import ncs_test_ygj.dao.impl.TItleDaoImpl;
import ncs_test_ygj.dto.Title;

public class TitleUiService {
	private TitleDao titleDao;
	
	public TitleUiService() {
		titleDao = TItleDaoImpl.getInstance();
	}

	public List<Title> showTitleList() {
		return titleDao.selectTitleByAll();
	}

	public void removeTitle(Title dleTitle) {
		titleDao.deleteTitle(dleTitle);
	}

	public void modifyTitle(Title newTitle) {
		titleDao.updateTitle(newTitle);
	}

	public void addTitle(Title newTitle) {
		titleDao.insertTitle(newTitle);
	}

	public String getLastCode() {
		return titleDao.selectTitleLastCode();
	}

	
}
