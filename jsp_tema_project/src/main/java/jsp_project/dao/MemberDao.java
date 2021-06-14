package jsp_project.dao;

import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import jsp_project.model.Member;
public class MemberDao {
	//singleton
	private static MemberDao instance = new MemberDao();
	private MemberDao() {}
	public static MemberDao getInstance() {
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
	private static SqlSession session;
	static {
		try {
			Reader reader = Resources.getResourceAsReader("configuration.xml");
			SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(reader);
			//true해야 입력/수정/삭제 후에 commit 실행
			session = ssf.openSession(true);
		} catch (Exception e) {
			System.out.println("에러 : "+e.getMessage());
		}
	}
	
	public Member select(String user_id) {
		return (Member)session.selectOne("memberns.select", user_id); 
	}
	
	public int insert(Member member) {
		return session.insert("memberns.insert",member);
	}
	public int update(Member member) {
		return session.update("memberns.update",member);
	}
	public int updatepass(Member member) {
		return session.update("memberns.updatepass",member);
	}
	public int delete(Member member) {
		return session.update("memberns.delete",member);
	}
	
	public List<Member> list(int startRow, int endRow){
		List<Member> list = new ArrayList<Member>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql = "select * from (select rowNum rn, a.* from "
				+ "(select * from member order by user_id) a)"
				+ "    where rn between ? and ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Member member = new Member();
				member.setUser_id(rs.getString("user_id"));
				member.setUser_password(rs.getString("user_password"));
				member.setUser_name(rs.getString("user_name"));
				member.setUser_tel(rs.getString("user_tel"));
				member.setUser_address(rs.getString("user_address"));
				member.setUser_email(rs.getString("user_email"));
				member.setUser_date(rs.getDate("user_date"));
				member.setUser_del(rs.getString("user_del"));
				
				list.add(member);
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
	
	public int realdelete(String user_id){
		int result = 0;
		PreparedStatement pstmt = null;
		Connection conn = getConnection();
		String sql = "delete from member where user_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
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
		String sql = "select count(*) from member";
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
