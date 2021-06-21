package jsp_project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import jsp_project.model.Acmd;
import jsp_project.model.Travel;

public class AcmdDao {
	private static AcmdDao instance = new AcmdDao();
	private AcmdDao() {}
	public static AcmdDao getInstance() {
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
	
	public List<Acmd> list(int startRow, int endRow){
		List<Acmd> list = new ArrayList<Acmd>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql = "select * from (select rowNum rn, a.* from "
				+ "(select * from acmd order by acmd_num desc) a)"
				+ "    where rn between ? and ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Acmd acmd = new Acmd();
				acmd.setAcmd_num(rs.getInt("acmd_num"));
				acmd.setAcmd_name(rs.getString("acmd_name"));
				acmd.setAcmd_img(rs.getString("acmd_img"));
				acmd.setAcmd_local(rs.getString("acmd_local"));
				acmd.setAcmd_content(rs.getString("acmd_content"));
				acmd.setAcmd_mini(rs.getString("acmd_mini"));
				acmd.setAcmd_travel(rs.getString("acmd_travel"));
				acmd.setAcmd_q(rs.getString("acmd_q"));
				
				list.add(acmd);
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
	
	public int insert(Acmd acmd) {
		int result = 0;
		PreparedStatement pstmt = null;
		Connection conn = getConnection();
		ResultSet rs = null;
		String sql = "insert into acmd values(?,?,?,?,?,?,?,?)";
		String sql2 = "select nvl(max(acmd_num),0) + 1 from acmd";
		try {
			pstmt = conn.prepareStatement(sql2);
			rs = pstmt.executeQuery();
			rs.next();
			int number = rs.getInt(1); //가장 큰 num에 1을 더한값		
			pstmt.close();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			pstmt.setString(2, acmd.getAcmd_name());
			pstmt.setString(3, acmd.getAcmd_img());
			pstmt.setString(4, acmd.getAcmd_local());
			pstmt.setString(5, acmd.getAcmd_content());
			pstmt.setString(6, acmd.getAcmd_mini());
			pstmt.setString(7, acmd.getAcmd_travel());
			pstmt.setString(8, acmd.getAcmd_q());

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
	
	public Acmd select(int acmd_num){
		Acmd acmd = new Acmd();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql = "select * from acmd where acmd_num=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, acmd_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				acmd.setAcmd_num(rs.getInt("acmd_num"));
				acmd.setAcmd_name(rs.getString("acmd_name"));
				acmd.setAcmd_img(rs.getString("acmd_img"));
				acmd.setAcmd_local(rs.getString("acmd_local"));
				acmd.setAcmd_content(rs.getString("acmd_content"));
				acmd.setAcmd_mini(rs.getString("acmd_mini"));
				acmd.setAcmd_travel(rs.getString("acmd_travel"));
				acmd.setAcmd_q(rs.getString("acmd_q1"));
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
		return acmd;
	}
	
	public int delete(int acmd_num){
		int result = 0;
		PreparedStatement pstmt = null;
		Connection conn = getConnection();
		String sql = "delete from acmd where acmd_num=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, acmd_num);
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
	
	public int update(Acmd acmd){ //board 화면에서 입력
		int result = 0;
		PreparedStatement pstmt = null;
		Connection conn = getConnection();
		String sql = "update acmd set acmd_name=?,acmd_img=?,acmd_local=?,acmd_content=?,acmd_mini=?,acmd_travel=?,acmd_q=? where acmd_num=?"; 
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, acmd.getAcmd_name());
			pstmt.setString(2, acmd.getAcmd_img());
			pstmt.setString(3, acmd.getAcmd_local());
			pstmt.setString(4, acmd.getAcmd_content());
			pstmt.setString(5, acmd.getAcmd_mini());
			pstmt.setString(6, acmd.getAcmd_travel());
			pstmt.setString(7, acmd.getAcmd_q());
			pstmt.setInt(8, acmd.getAcmd_num());
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
	
	public int getSearchTotal(String acmd_q) {
		int total = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql = "select count(*) from acmd where acmd_q=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, acmd_q);
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
	
	public List<Acmd> search(int startRow, int endRow, String acmd_q) {
		List<Acmd> list = new ArrayList<Acmd>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql = "select * from (select rowNum rn, a.* from "
				+ "(select * from acmd where acmd_q = ? order by acmd_num desc) a)"
				+ "    where rn between ? and ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, acmd_q);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Acmd acmd = new Acmd();
				acmd.setAcmd_num(rs.getInt("acmd_num"));
				acmd.setAcmd_name(rs.getString("acmd_name"));
				acmd.setAcmd_img(rs.getString("acmd_img"));
				acmd.setAcmd_local(rs.getString("acmd_local"));
				acmd.setAcmd_content(rs.getString("acmd_content"));
				acmd.setAcmd_mini(rs.getString("acmd_mini"));
				acmd.setAcmd_travel(rs.getString("acmd_travel"));
				acmd.setAcmd_q(rs.getString("acmd_q"));

				list.add(acmd);
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
		String sql = "select count(*) from acmd";
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
