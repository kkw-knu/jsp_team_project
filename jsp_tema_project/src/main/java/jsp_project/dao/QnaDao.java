package jsp_project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import jsp_project.model.Qna;

public class QnaDao {
	private static QnaDao instance = new QnaDao();
	private QnaDao() {}
	public static QnaDao getInstance() {
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
	public List<Qna> list(int startRow, int endRow){
		List<Qna> list = new ArrayList<Qna>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql = "select * from (select rowNum rn, a.* from "
				+ "(select * from qna order by qna_ref desc, qna_re_step) a)"
				+ "    where rn between ? and ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Qna qna = new Qna();
				qna.setQna_num(rs.getInt("qna_num"));
				qna.setQna_title(rs.getString("qna_title"));
				qna.setQna_content(rs.getString("qna_content"));
				qna.setQna_writer(rs.getString("qna_writer"));
				qna.setQna_readcount(rs.getInt("qna_readcount"));
				qna.setQna_ref(rs.getInt("qna_ref"));
				qna.setQna_re_step(rs.getInt("qna_re_step"));
				qna.setQna_re_level(rs.getInt("qna_re_level"));
				qna.setQna_reg_date(rs.getDate("qna_reg_date"));
				qna.setQna_del(rs.getString("qna_del"));
				
				list.add(qna);
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
	
	public Qna select(int qna_num){
		Qna qna = new Qna();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql = "select * from board where qna_num=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qna_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				qna.setQna_num(rs.getInt("qna_num"));
				qna.setQna_title(rs.getString("qna_title"));
				qna.setQna_content(rs.getString("qna_content"));
				qna.setQna_writer(rs.getString("qna_writer"));
				qna.setQna_readcount(rs.getInt("qna_readcount"));
				qna.setQna_ref(rs.getInt("qna_ref"));
				qna.setQna_re_step(rs.getInt("qna_re_step"));
				qna.setQna_re_level(rs.getInt("qna_re_level"));
				qna.setQna_reg_date(rs.getDate("qna_reg_date"));
				qna.setQna_del(rs.getString("qna_del"));
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
		return qna;
	}
	public int insert(Qna qna){
		int result = 0;
		PreparedStatement pstmt = null;
		Connection conn = getConnection();
		ResultSet rs = null;
		String sql = "insert into qna values(?,?,?,?,0,?,?,?,sysdate,'n')";
		String sql2 = "select nvl(max(qna_num),0) + 1 from qna";
		String sql3 = "update qna set qna_re_step = qna_re_step+1 where qna_ref=? and qna_re_step > ?";
		try {
			pstmt = conn.prepareStatement(sql2);
			rs = pstmt.executeQuery();
			rs.next();
			int number = rs.getInt(1); //가장 큰 num에 1을 더한값
			pstmt.close();
			
			
			if(qna.getQna_num() != 0) { //답변글
				pstmt = conn.prepareStatement(sql3);
				pstmt.setInt(1, qna.getQna_ref());
				pstmt.setInt(2, qna.getQna_re_step());
				pstmt.executeUpdate();
				pstmt.close();
				qna.setQna_re_step(qna.getQna_re_step()+1);
				qna.setQna_re_level(qna.getQna_re_level()+1);
			}else qna.setQna_ref(number); //답변글이 아니면 ref와 num은 같다.
			
			pstmt.close();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			pstmt.setString(2, qna.getQna_title());
			pstmt.setString(3, qna.getQna_content());
			pstmt.setString(4, qna.getQna_writer());
			//답변
			pstmt.setInt(5, qna.getQna_ref());
			pstmt.setInt(6, qna.getQna_re_step());
			pstmt.setInt(7, qna.getQna_re_level());
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
		String sql = "select count(*) from qna";
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
