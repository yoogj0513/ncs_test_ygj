package ncs_test_ygj.dao;

import java.util.List;

import ncs_test_ygj.dto.Title;

public interface TitleDao {
	Title selectTitleByCode(Title title);
	List<Title> selectTitleByAll();
	
	int insertTitle(Title title);
	int updateTitle(Title title);
	int deleteTitle(Title title);
}
