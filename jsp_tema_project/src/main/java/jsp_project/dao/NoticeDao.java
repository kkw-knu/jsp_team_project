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
import jsp_project.model.Qna;

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
			System.out.println("에러: "+e.getMessage());
		}
		return conn;
	}
	
	public List<Notice> list(int startRow, int endRow){
		List<Notice> list = new ArrayList<Notice>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql = "select * from (select rowNum rn, a.* from "
				+ "(select * from notice order by notice_num desc) a)"
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
	
	public Notice select(int notice_num){
		Notice notice = new Notice();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql = "select * from notice where notice_num=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, notice_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				notice.setNotice_num(rs.getInt("notice_num"));
				notice.setNotice_title(rs.getString("notice_title"));
				notice.setNotice_content(rs.getString("notice_content"));
				notice.setNotice_writer(rs.getString("notice_writer"));
				notice.setNotice_readcount(rs.getInt("notice_readcount"));
				notice.setNotice_reg_date(rs.getDate("notice_reg_date"));
				notice.setNotice_del(rs.getString("notice_del"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return notice;
	}
	
	public int insert(Notice notice){
		int result = 0;
		PreparedStatement pstmt = null;
		Connection conn = getConnection();
		ResultSet rs = null;
		String sql = "insert into notice values(?,?,?,?,0,sysdate,'n')";
		String sql2 = "select nvl(max(notice_num),0) + 1 from notice";
		try {
			pstmt = conn.prepareStatement(sql2);
			rs = pstmt.executeQuery();
			rs.next();
			int number = rs.getInt(1); //가장 큰 num에 1을 더한값		
			pstmt.close();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			pstmt.setString(2, notice.getNotice_title());
			pstmt.setString(3, notice.getNotice_content());
			pstmt.setString(4, notice.getNotice_writer());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return result;
	}
	
	public void readcoutUpdate(int notice_num){
		PreparedStatement pstmt = null;
		Connection conn = getConnection();
		String sql = "update notice set notice_readcount = notice_readcount + 1 where notice_num=?";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, notice_num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
	}
	
	public int update(Notice notice){ //board 화면에서 입력
		int result = 0;
		PreparedStatement pstmt = null;
		Connection conn = getConnection();
		String sql = "update notice set notice_content=?,notice_title=? where notice_num=?"; 
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, notice.getNotice_content());
			pstmt.setString(2, notice.getNotice_title());
			pstmt.setInt(3, notice.getNotice_num());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return result;
	}
	
	public int delete(int notice_num){
		int result = 0;
		PreparedStatement pstmt = null;
		Connection conn = getConnection();
		String sql = "update notice set notice_del='y' where notice_num=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, notice_num);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return result;
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
	public List<Notice> newnotice() {
		List<Notice> noticelist = new ArrayList<Notice>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql = "SELECT * FROM (SELECT * FROM notice ORDER BY notice_reg_date desc)"
				+ " WHERE rownum <= 3";
		try {
			pstmt = conn.prepareStatement(sql);
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
				
				noticelist.add(notice);
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
		return noticelist;
	}
}
