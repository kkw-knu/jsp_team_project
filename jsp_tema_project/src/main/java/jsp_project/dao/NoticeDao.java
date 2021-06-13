package jsp_project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import jsp_project.model.Notice;

public class NoticeDao {
	private static NoticeDao instance = new NoticeDao();
	private NoticeDao() {}
	public static NoticeDao getInstance() {
		return instance;
	}
	
	private Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		}catch (Exception e) {
			System.out.println("¿¡·¯: "+e.getMessage());
		}
		return conn;
	}
	
	public List<Notice> list(int startRow, int endRow){
		List<Notice> list = new ArrayList<Notice>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql = "select * from (select rowNum rn, a.* from "
				+ "(select * from notice) a)"
				+ "    where rn between ? and ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Notice notice = new Notice();
				notice.setNotice_num(rs.getInt("notice_num"));
				notice.setNotice_title(rs.getString("notice_title"));
				notice.setNotice_content(rs.getString("notice_content"));
				notice.setNotice_writer(rs.getString("notice_writer"));
				notice.setNotice_readcount(rs.getInt("notice_readcount"));
				notice.setNotice_reg_date(rs.getDate("notice_reg_date"));
				notice.setNotice_del(rs.getString("notice_del"));
				
				list.add(notice);
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null)  conn.close();
			}catch (Exception e) {		}
		}
		return list;
	}
	public int getTotal() {
		int total = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql = "select count(*) from notice";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				total = rs.getInt(1);
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null)  conn.close();
			}catch (Exception e) {		}
		}
		return total;
	}
}
