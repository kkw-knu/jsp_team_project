package review;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.*;
import bean.*;

/**
 * Servlet implementation class review_write
 */
@WebServlet("/review_write")
public class review_write extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public review_write() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	
		System.out.println("review_write 서블릿 요청");
		
		//파라미터 추출
		
		String user_id = request.getParameter("user_id");
		String review_title = request.getParameter("review_title");
		String review_thema = request.getParameter("review_thema");
		String review_place = request.getParameter("review_place");
		String review_content = request.getParameter("review_content");
		
		
		int review_star = Integer.parseInt(request.getParameter("review_star"));
		
		
		// 값 출력 test
		System.out.println(user_id);
		System.out.println(review_title);
		System.out.println(review_thema);
		System.out.println(review_place);
		System.out.println(review_content);
		System.out.println(review_star);
		
		
		// 빈 객체에 데이터 set
		review_Bean bean = new review_Bean();
		
		bean.setUser_id(user_id);
		bean.setReview_title(review_title);
		bean.setReview_thema(review_thema);
		bean.setReview_place(review_place);
		bean.setReview_content(review_content);
		bean.setReview_star(review_star);
		
		
		//DB에 저장
		review_DAO.review_write(bean);
		
		//페이지 이동
		String site = "review.credu";
		response.sendRedirect(site);
		
		
		
		
			
				
		
		
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
