package jsp_project.dao;

import java.io.Reader;

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
	private static SqlSession session;
	static {
		try {
			Reader reader = Resources.getResourceAsReader("configuration.xml");
			SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(reader);
			//true해야 입력/수정/삭제 ㅜㅎ에 commit 실행
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
	//기본 연결 틀
	public int update(Member member) {
		return session.update("memberns.update",member);
	}
}
