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
			System.out.println("¿¡·¯: "+e.getMessage());
		}
		return conn;
	}
	public List<Qna> list(int startRow, int endRow){
		List<Qna> list = new ArrayList<Qna>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql = "select * from (select rowNum rn, a.* from "
				+ "(select * from qna order by ref desc, re_step) a)"
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
				qna.setDel(rs.getString("del"));
				
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
