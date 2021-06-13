package review.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ReviewDAO {

	//DB 연결 부분
	private static ReviewDAO instance = new ReviewDAO();
    
 	public static ReviewDAO getInstance() {
        return instance;
    }

    private ReviewDAO() {}
    
    private Connection getConnection() throws Exception {
	    Connection conn=null; 	 
	    Context init = new InitialContext();
	   	DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
	   	conn = ds.getConnection();
	    return conn;
    }
    
    //리뷰 등록
    public int insertArticle(ReviewBean article) throws Exception {
    	Connection conn = null;
        PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="";
		int number=0;
		int r=0;
		
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("select max(review_no) from REVIEW");
            rs = pstmt.executeQuery();
            
            if (rs.next())
  		      number=rs.getInt(1)+1;
  		    else
  		      number=1;
			
            sql = "insert into REVIEW(review_no, review_pw, "
            	+ "review_category, review_subject, review_content, "
            	+ "review_imgfile, review_date) values(?,?,?,?,?,?,sysdate)";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, number);
            pstmt.setString(2, article.getReview_pw());
            pstmt.setInt(3, article.getReview_category());
            pstmt.setString(4, article.getReview_subject());
            pstmt.setString(5, article.getReview_content());
            pstmt.setString(6, article.getReview_imgfile());
            r=pstmt.executeUpdate();
            
        } catch(Exception ex) {
        	System.out.println(ex);
        	
        } finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
        return r;
    }
    
    //리뷰 개수 구하기
    public int getArticleCount() throws Exception {
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
    	
        int x=0;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("select count(*) from REVIEW");
            rs = pstmt.executeQuery();

            if (rs.next()) {
               x= rs.getInt(1);
			}
        } catch(Exception ex) {
        	System.out.println("오류내용 :" + ex);
        } finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
		return x;
    	
    }
    
    //카테고리별 개수 구하기
    public int getCategoryCount(int cate) throws Exception {
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int x = 0;

        try {
            conn = getConnection();
            
            String cCount = "select count(*) from (select * from REVIEW "
            			  + "where review_category = ?)";
            
            pstmt = conn.prepareStatement(cCount);
            pstmt.setInt(1, cate);
            rs = pstmt.executeQuery();

            if (rs.next()) {
               x= rs.getInt(1);
			}
        } catch(Exception ex) {
        	System.out.println("오류내용 :" + ex);
        } finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
		return x;
    	
    }
    
    //리뷰 목록 불러오기
    public ArrayList<ReviewBean> getArticles(int start, int end) throws Exception {
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<ReviewBean> articleList=null;
        try {
            conn = getConnection();
            
            //계층형이 빠진 게시판용 리스트 sql문
            //faq_no가 큰 순서대로 우선 정렬한 것을 rownum rn을 주어 고정시키고 그 rn을 10개씩 뽑아낸다.
            String all =             
            "select review_no, review_subject, review_category, "
            + "review_imgfile, review_count, review_date from "
            + "(select rownum rn, review_no, review_subject, review_category,"
            + "review_imgfile, review_count, review_date from "
            + "(select * from REVIEW order by review_no desc)) "
            + "where rn between ? and ?";
            
            pstmt = conn.prepareStatement(all);
          
            int k = start + 9 ;
            pstmt.setInt(1, start);
			pstmt.setInt(2, k);
            rs = pstmt.executeQuery();
            
           
                articleList = new ArrayList<ReviewBean>(end);
                while(rs.next()){
                	ReviewBean article= new ReviewBean();
					article.setReview_no(rs.getInt("review_no"));
					article.setReview_imgfile(rs.getString("review_imgfile"));
					article.setReview_category(rs.getInt("review_category"));
                	article.setReview_subject(rs.getString("review_subject"));
                	article.setReview_date(rs.getTimestamp("review_date"));
                	article.setReview_count(rs.getInt("review_count"));
                	
			    	
			    	//FK 처리는 어떻게 해야 하는가?
			    	
			    	articleList.add(article);
			}
        } catch(Exception ex) {
        	System.out.println("오류내용 :" + ex);
        } finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
		return articleList;
    	
    }
    
    //카테고리별 목록 불러오기
    public ArrayList<ReviewBean> getCategory(int cate, int start, int end) throws Exception {
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<ReviewBean> articleList=null;
        
        try {
            conn = getConnection();
            
            //추가로 카테고리 조건 삽입
            String cList =             
            		"select review_no, review_subject, review_category,"
            	    + "review_imgfile, review_count, review_date from "
            	    + "(select rownum rn, review_no, review_subject, review_category,"
            	    + "review_imgfile, review_count, review_date from "
            	    + "(select * from REVIEW where review_category = ? "
            	    + "order by review_no desc)) where rn between ? and ?";
            
            pstmt = conn.prepareStatement(cList);
          
            int k = start + 9 ;
            pstmt.setInt(1, cate);
            pstmt.setInt(2, start);
			pstmt.setInt(3, k);
            rs = pstmt.executeQuery();
            
           
                articleList = new ArrayList<ReviewBean>(end);
                while(rs.next()){
                	ReviewBean article= new ReviewBean();
                	article.setReview_no(rs.getInt("review_no"));
					article.setReview_imgfile(rs.getString("review_imgfile"));
					article.setReview_category(rs.getInt("review_category"));
                	article.setReview_subject(rs.getString("review_subject"));
                	article.setReview_date(rs.getTimestamp("review_date"));
                	article.setReview_count(rs.getInt("review_count"));
                	
			    	
			    	//FK 처리는 어떻게 해야 하는가?
			    	
			    	articleList.add(article);
			}
        } catch(Exception ex) {
        	System.out.println("오류내용 :" + ex);
        } finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
		return articleList;
    	
    }
    
    //리뷰 내용 불러오기
    public ReviewBean getArticle(int num) throws Exception{
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ReviewBean article=null;
        try {
            conn = getConnection();
            
            pstmt = conn.prepareStatement(
            	"update REVIEW set review_count=review_count+1 where review_no = ?");
			pstmt.setInt(1, num);
			pstmt.executeUpdate();

            pstmt = conn.prepareStatement(
            	"select * from REVIEW where review_no = ?");
            pstmt.setInt(1, num);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                article = new ReviewBean();
                article.setReview_no(rs.getInt("review_no"));
				article.setReview_category(rs.getInt("review_category"));
            	article.setReview_subject(rs.getString("review_subject"));
            	article.setReview_content(rs.getString("review_content"));
            	article.setReview_imgfile(rs.getString("review_imgfile"));
            	article.setReview_count(rs.getInt("review_count"));
            	article.setReview_recommend(rs.getInt("review_recommend"));
		    	article.setReview_date(rs.getTimestamp("review_date"));
                
			}
        } catch(Exception ex) {
        	System.out.println("오류내용 :" + ex);
        } finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
		return article;
    }
    
    //리뷰 추천하기
    public int getRecomm(int num) throws Exception{
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int x = 0;
        
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(
            	"update REVIEW set review_recommend = review_recommend + 1 where review_no = ?");
			pstmt.setInt(1, num);
			x = pstmt.executeUpdate();

            
        } catch(Exception ex) {
        	System.out.println("오류내용 :" + ex);
        } finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
        return x;
    }
    
    //수정할 리뷰 쓰기 창으로 불러오기
    public ReviewBean updateGetArticle(int num) throws Exception {
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ReviewBean article=null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(
            	"select * from REVIEW where review_no = ?");
            pstmt.setInt(1, num);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                article = new ReviewBean();
                article.setReview_no(rs.getInt("review_no"));
				article.setReview_category(rs.getInt("review_category"));
            	article.setReview_subject(rs.getString("review_subject"));
            	article.setReview_content(rs.getString("review_content"));
            	article.setReview_imgfile(rs.getString("review_imgfile"));
            	article.setReview_count(rs.getInt("review_count"));
		    	article.setReview_date(rs.getTimestamp("review_date"));
                 
			}
        } catch(Exception ex) {
        	System.out.println("오류내용 :" + ex);
        } finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
		return article;
    }
    
    //리뷰 수정
    public int updateArticle(ReviewBean article) throws Exception {
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs= null;
        
        String dbpasswd="";
        String sql="";
		int x=0;
		
        try {
            conn = getConnection();
			pstmt = conn.prepareStatement(
            	"select review_pw from REVIEW where review_no = ?");
            pstmt.setInt(1, article.getReview_no());
            rs = pstmt.executeQuery();
            
			if(rs.next()){
			  dbpasswd= rs.getString("review_pw"); 
			  if(dbpasswd.equals(article.getReview_pw())){
                sql="update REVIEW set review_category = ?, review_subject =  ?, "
                	+ "review_content = ?, review_imgfile = ? where review_no = ?";
                
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setInt(1, article.getReview_category());
		        pstmt.setString(2, article.getReview_subject());
		        pstmt.setString(3, article.getReview_content());
		        pstmt.setString(4, article.getReview_imgfile());
		        pstmt.setInt(5, article.getReview_no());
		        pstmt.executeUpdate();
		        x=1;
			  }else{
				x=-1;
			  }
            }
	    } catch(Exception ex) {
        	System.out.println("오류내용 :" + ex);
        } finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
		return x;
    }
    
    //관리자용 리뷰 삭제
    public int adminDeleteArticle(int num) throws Exception {
    	
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs= null;
	    int x=0;
	        
	    try {
	        	
			conn = getConnection();
	        pstmt = conn.prepareStatement("delete from REVIEW where review_no = ?");
	        pstmt.setInt(1, num);
	        x=pstmt.executeUpdate();
	            
	    } catch(Exception ex) {
	        	System.out.println("오류내용 : " + ex);
	    } finally {
	            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	    }
	    return x;
	}
    
    //리뷰 삭제
    public int deleteArticle(int num, String password) throws Exception {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs= null;
	    String dbpass = "";
	    int r=0;
	        
	    try {
        	
	    	conn = getConnection();

            pstmt = conn.prepareStatement(
            	"select review_pw from REVIEW where review_no = ?");
            pstmt.setInt(1, num);
            rs = pstmt.executeQuery();
            
			if(rs.next()){
				dbpass = rs.getString("review_pw"); 
				if(password.equals(dbpass)){
					pstmt = conn.prepareStatement(
            	      "delete from REVIEW where review_no=?");
                    pstmt.setInt(1, num);
                    pstmt.executeUpdate();
                    r= 1; //글삭제 성공
				}else
					r= 0; //비밀번호 틀림
			}
	    } catch(Exception ex) {
	        	System.out.println("오류내용 :" + ex);
	    } finally {
	            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	    }
	    return r;
	}

    //코멘트 등록
    public int insertComm(CommBean comment) throws Exception {
    	Connection conn = null;
        PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="";
		int number=0;
		int r=0;
		
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("select max(comment_no) from COMM");
            rs = pstmt.executeQuery();
            
            if (rs.next())
  		      number=rs.getInt(1)+1;
  		    else
  		      number=1;
			
            sql = "insert into COMM(comment_no, comment_name, comment_pw, comment_content, "
		    	+ "comment_date, review_no) values(?,?,?,?,sysdate,?)";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, number);
            pstmt.setString(2, comment.getComment_name());
            pstmt.setString(3, comment.getComment_pw());
            pstmt.setString(4, comment.getComment_content());
            pstmt.setInt(5, comment.getReview_no());
            r=pstmt.executeUpdate();
            
        } catch(Exception ex) {
        	System.out.println(ex);
        	
        } finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
        return r;
    }

  //코멘트 개수 구하기
    public int getCommCount() throws Exception {
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
    	
        int x=0;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("select count(*) from COMM");
            rs = pstmt.executeQuery();

            if (rs.next()) {
               x= rs.getInt(1);
			}
        } catch(Exception ex) {
        	System.out.println("오류내용 :" + ex);
        } finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
		return x;
    }
    
    //코멘트 목록 불러오기
    public ArrayList<CommBean> getComm(int reviewNum, int coCount) throws Exception {
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<CommBean> commentList=null;
        try {
            conn = getConnection();
            
            String sql =             
            	"select comment_no, comment_name, comment_content, "
                + "comment_date, review_no from COMM where review_no=? "
                + "order by comment_no desc";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, reviewNum);
            rs = pstmt.executeQuery();
            
           
                commentList = new ArrayList<CommBean>(coCount);
                while(rs.next()){
                	CommBean comment = new CommBean();
                	comment.setComment_no(rs.getInt("comment_no"));
                	comment.setComment_name(rs.getString("comment_name"));
                	comment.setComment_content(rs.getString("comment_content"));
                	comment.setReview_no(rs.getInt("review_no"));
                	comment.setComment_date(rs.getTimestamp("comment_date"));
			    	
                	commentList.add(comment);
			}
        } catch(Exception ex) {
        	System.out.println("오류내용 :" + ex);
        } finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
		return commentList;
    	
    }
    
    //코멘트 삭제
    public int deleteComm(int coNum, String CoPass) throws Exception {
    	Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs= null;
	    String c_pw = "";
	    int r=0;
	        
	    try {
	        	
	    	conn = getConnection();

            pstmt = conn.prepareStatement(
            	"select comment_pw from COMM where comment_no = ?");
            pstmt.setInt(1, coNum);
            rs = pstmt.executeQuery();
            
			if(rs.next()){
				c_pw= rs.getString("comment_pw"); 
				if(CoPass.equals(c_pw)){
					pstmt = conn.prepareStatement(
            	      "delete from COMM where comment_no=?");
                    pstmt.setInt(1, coNum);
                    pstmt.executeUpdate();
					r= 1; //글삭제 성공
				}else
					r= -1; //비밀번호 틀림
			}
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
		return r;
    }
    
    //관리자 코멘트 삭제
public int adminDeleteComm(int num) throws Exception {
    	
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs= null;
	    int x=0;
	        
	    try {
	        	
			conn = getConnection();
	        pstmt = conn.prepareStatement("delete from COMM where comment_no = ?");
	        pstmt.setInt(1, num);
	        x=pstmt.executeUpdate();
	            
	    } catch(Exception ex) {
	        	System.out.println("오류내용 : " + ex);
	    } finally {
	            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	    }
	    return x;
	}
}
