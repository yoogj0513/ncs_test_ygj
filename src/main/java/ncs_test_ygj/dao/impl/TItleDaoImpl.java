package ncs_test_ygj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ncs_test_ygj.dao.TitleDao;
import ncs_test_ygj.ds.MysqlDataSource;
import ncs_test_ygj.dto.Title;
import ncs_test_ygj.util.LogUtil;

public class TItleDaoImpl implements TitleDao {
	private static final TItleDaoImpl instance = new TItleDaoImpl();
	
	public TItleDaoImpl() {}
	
	public static TItleDaoImpl getInstance() {
		return instance;
	}

	@Override
	public Title selectTitleByCode(Title title) {
		String sql = "select title_no, title_name from title where title_no = ?";
		try(Connection con = MysqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setString(1, title.getTitleNo());
			LogUtil.prnLog(pstmt);
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					return getTitle(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Title> selectTitleByAll() {
		String sql = "select title_no, title_name from title";
		try(Connection con = MysqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			LogUtil.prnLog(pstmt);
			if(rs.next()) {
				List<Title> list = new ArrayList<>();
				do {
					list.add(getTitle(rs));					
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private Title getTitle(ResultSet rs) throws SQLException {
		String titleNo = rs.getString("title_no");
		String titleName = rs.getString("title_name");
		return new Title(titleNo, titleName);
	}

	@Override
	public int insertTitle(Title title) {
		String sql = "insert into title values (?, ?)";
		try(Connection con = MysqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, title.getTitleNo());
			pstmt.setString(2, title.getTitleName());
			LogUtil.prnLog(pstmt);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateTitle(Title title) {
		String sql = "update title set title_name = ? where title_no = ?";
		try(Connection con = MysqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, title.getTitleName());
			pstmt.setString(2, title.getTitleNo());
			LogUtil.prnLog(pstmt);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteTitle(Title title) {
		String sql = "delete from title where title_no = ?";
		try(Connection con = MysqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, title.getTitleNo());
			LogUtil.prnLog(pstmt);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
