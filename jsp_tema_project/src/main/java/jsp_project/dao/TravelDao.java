package jsp_project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import jsp_project.model.Travel;

public class TravelDao {
	private static TravelDao instance = new TravelDao();
	private TravelDao() {}
	public static TravelDao getInstance() {
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
	
	public List<Travel> list(int startRow, int endRow){
		List<Travel> list = new ArrayList<Travel>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql = "select * from (select rowNum rn, a.* from "
				+ "(select * from travel order by travel_num desc) a)"
				+ "    where rn between ? and ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Travel travel = new Travel();
				travel.setTravel_num(rs.getInt("travel_num"));
				travel.setTravel_name(rs.getString("travel_name"));
				travel.setTravel_img(rs.getString("travel_img"));
				travel.setTravel_local(rs.getString("travel_local"));
				travel.setTravel_content(rs.getString("travel_content"));
				travel.setTravel_mini(rs.getString("travel_mini"));
				travel.setTravel_q1(rs.getString("travel_q1"));
				travel.setTravel_q1(rs.getString("travel_q2"));
				travel.setTravel_q1(rs.getString("travel_q3"));
				travel.setTravel_q1(rs.getString("travel_q4"));
				travel.setTravel_star(rs.getFloat("travel_star"));
				
				list.add(travel);
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
		String sql = "select count(*) from travel";
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
	public int getSearchTotal(String travel_q3) {
		int total = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql = "select count(*) from travel where travel_q3=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, travel_q3);
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
	
	public Travel select(int travel_num){
		Travel travel = new Travel();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql = "select * from travel where travel_num=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, travel_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				travel.setTravel_num(rs.getInt("travel_num"));
				travel.setTravel_name(rs.getString("travel_name"));
				travel.setTravel_img(rs.getString("travel_img"));
				travel.setTravel_local(rs.getString("travel_local"));
				travel.setTravel_content(rs.getString("travel_content"));
				travel.setTravel_mini(rs.getString("travel_mini"));
				travel.setTravel_q1(rs.getString("travel_q1"));
				travel.setTravel_q1(rs.getString("travel_q2"));
				travel.setTravel_q1(rs.getString("travel_q3"));
				travel.setTravel_q1(rs.getString("travel_q4"));
				travel.setTravel_star(rs.getFloat("travel_star"));
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
		return travel;
	}
	
	public int insert(Travel travel) {
		int result = 0;
		PreparedStatement pstmt = null;
		Connection conn = getConnection();
		ResultSet rs = null;
		String sql = "insert into travel values(?,?,?,?,?,?,?,?,?,?,0)";
		String sql2 = "select nvl(max(travel_num),0) + 1 from travel";
		try {
			pstmt = conn.prepareStatement(sql2);
			rs = pstmt.executeQuery();
			rs.next();
			int number = rs.getInt(1); //가장 큰 num에 1을 더한값		
			pstmt.close();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			pstmt.setString(2, travel.getTravel_name());
			pstmt.setString(3, travel.getTravel_img());
			pstmt.setString(4, travel.getTravel_local());
			pstmt.setString(5, travel.getTravel_content());
			pstmt.setString(6, travel.getTravel_mini());
			pstmt.setString(7, travel.getTravel_q1());
			pstmt.setString(8, travel.getTravel_q2());
			pstmt.setString(9, travel.getTravel_q3());
			pstmt.setString(10, travel.getTravel_q4());

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
	
	public int update(Travel travel){ //board 화면에서 입력
		int result = 0;
		PreparedStatement pstmt = null;
		Connection conn = getConnection();
		String sql = "update travel set travel_name=?,travel_img=?,travel_local=?,travel_content=?,travel_mini=?,travel_q1=?,travel_q2=?,travel_q3=?,travel_q4=? where travel_num=?"; 
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, travel.getTravel_name());
			pstmt.setString(2, travel.getTravel_img());
			pstmt.setString(3, travel.getTravel_local());
			pstmt.setString(4, travel.getTravel_content());
			pstmt.setString(5, travel.getTravel_mini());
			pstmt.setString(6, travel.getTravel_q1());
			pstmt.setString(7, travel.getTravel_q2());
			pstmt.setString(8, travel.getTravel_q3());
			pstmt.setString(9, travel.getTravel_q4());
			pstmt.setFloat(10, travel.getTravel_num());
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
	
	public int delete(int travel_num){
		int result = 0;
		PreparedStatement pstmt = null;
		Connection conn = getConnection();
		String sql = "delete from travel where travel_num=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, travel_num);
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
	public List<Travel> search(int startRow, int endRow, String travel_q3) {
		List<Travel> list = new ArrayList<Travel>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql = "select * from (select rowNum rn, a.* from "
				+ "(select * from travel where travel_q3 = ? order by travel_num desc) a)"
				+ "    where rn between ? and ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, travel_q3);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Travel travel = new Travel();
				travel.setTravel_num(rs.getInt("travel_num"));
				travel.setTravel_name(rs.getString("travel_name"));
				travel.setTravel_img(rs.getString("travel_img"));
				travel.setTravel_local(rs.getString("travel_local"));
				travel.setTravel_content(rs.getString("travel_content"));
				travel.setTravel_mini(rs.getString("travel_mini"));
				travel.setTravel_q1(rs.getString("travel_q1"));
				travel.setTravel_q1(rs.getString("travel_q2"));
				travel.setTravel_q1(rs.getString("travel_q3"));
				travel.setTravel_q1(rs.getString("travel_q4"));
				travel.setTravel_star(rs.getFloat("travel_star"));
				
				list.add(travel);
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
