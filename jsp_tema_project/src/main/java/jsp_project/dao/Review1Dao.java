package jsp_project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import jsp_project.model.Review;
import jsp_project.model.Review1;

public class Review1Dao {
	private static Review1Dao instance = new Review1Dao();
	private Review1Dao() {}
	public static Review1Dao getInstance() {
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
	
	public List<Review1> search(int startRow, int endRow, String review_acmd) {
		List<Review1> list = new ArrayList<Review1>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql = "select * from (select rowNum rn, a.* from "
				+ "(select * from review1 where review_acmd = ? order by review_num desc) a)"
				+ "    where rn between ? and ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, review_acmd);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Review1 review = new Review1();
				review.setReview_num(rs.getInt("review_num"));
				review.setReview_id(rs.getString("review_id"));
				review.setReview_acmd(rs.getString("review_acmd"));
				review.setReview_title(rs.getString("review_title"));
				review.setReview_content(rs.getString("review_content"));
				review.setReview_star(rs.getInt("review_star"));
				review.setReview_reg_date(rs.getDate("review_reg_date"));
				
				list.add(review);
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
	
	public int update(Review1 review){ //board 화면에서 입력
		int result = 0;
		PreparedStatement pstmt = null;
		Connection conn = getConnection();
		String sql = "update review1 set review_content=?,review_title=?,review_star=? where review_num=?"; 
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, review.getReview_content());
			pstmt.setString(2, review.getReview_title());
			pstmt.setInt(3, review.getReview_star());
			pstmt.setInt(4, review.getReview_num());
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
	
	public int insert(Review1 review){
		int result = 0;
		PreparedStatement pstmt = null;
		Connection conn = getConnection();
		ResultSet rs = null;
		String sql = "insert into review1 values(?,?,?,?,?,?,sysdate)";
		String sql2 = "select nvl(max(review_num),0) + 1 from review1";
		try {
			pstmt = conn.prepareStatement(sql2);
			rs = pstmt.executeQuery();
			rs.next();
			int number = rs.getInt(1); //가장 큰 num에 1을 더한값		
			pstmt.close();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			pstmt.setString(2, review.getReview_id());
			pstmt.setString(3, review.getReview_acmd());
			pstmt.setString(4, review.getReview_title());
			pstmt.setString(5, review.getReview_content());
			pstmt.setInt(6, review.getReview_star());
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
	public int delete(int review_num){
		int result = 0;
		PreparedStatement pstmt = null; 
		Connection conn = getConnection();
		String sql = "delete from review1 where review_num=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, review_num);
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
	
	public int getTotal(String review_acmd) {
		int total = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql = "select count(*) from review1 where review_acmd=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, review_acmd);
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
	
	public int getMyTotal(String user_id) {
		int total = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql = "select count(*) from review1 where review_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
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
	
	public List<Review1> Mylist(int startRow, int endRow, String user_id) {
		List<Review1> list = new ArrayList<Review1>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql = "select * from (select rowNum rn, a.* from "
				+ "(select * from review1 where review_id = ? order by review_num desc) a)"
				+ "    where rn between ? and ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Review1 review = new Review1();
				review.setReview_num(rs.getInt("review_num"));
				review.setReview_id(rs.getString("review_id"));
				review.setReview_acmd(rs.getString("review_acmd"));
				review.setReview_title(rs.getString("review_title"));
				review.setReview_content(rs.getString("review_content"));
				review.setReview_star(rs.getInt("review_star"));
				review.setReview_reg_date(rs.getDate("review_reg_date"));
				
				list.add(review);
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
	
	public int getManagerTotal() {
		int total = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql = "select count(*) from review1";
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
	public List<Review1> list(int startRow, int endRow) {
		List<Review1> list = new ArrayList<Review1>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql = "select * from (select rowNum rn, a.* from "
				+ "(select * from review1 order by review_num desc) a)"
				+ "    where rn between ? and ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Review1 review = new Review1();
				review.setReview_num(rs.getInt("review_num"));
				review.setReview_id(rs.getString("review_id"));
				review.setReview_acmd(rs.getString("review_acmd"));
				review.setReview_title(rs.getString("review_title"));
				review.setReview_content(rs.getString("review_content"));
				review.setReview_star(rs.getInt("review_star"));
				review.setReview_reg_date(rs.getDate("review_reg_date"));
				
				list.add(review);
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
}
