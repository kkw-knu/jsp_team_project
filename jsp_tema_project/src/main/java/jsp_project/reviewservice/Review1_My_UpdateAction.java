package jsp_project.reviewservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp_project.dao.Review1Dao;
import jsp_project.model.Review1;
import jsp_project.service.CommandProcess;

public class Review1_My_UpdateAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (java.io.UnsupportedEncodingException e) {
			System.out.println("¿¡·¯ qna_writeaction : "+e.getMessage());
		}
		Review1 review = new Review1();
		int review_num = Integer.parseInt(request.getParameter("review_num"));
		String pageNum = request.getParameter("pageNum");
		String review_title= request.getParameter("review_title");
		String review_content = request.getParameter("review_content");
		int review_star = Integer.parseInt(request.getParameter("review_star"));
		
		review.setReview_content(review_content);
		review.setReview_num(review_num);
		review.setReview_star(review_star);
		review.setReview_title(review_title);
		
		Review1Dao rd = Review1Dao.getInstance();
		int result = rd.update(review);
		rd.starupdate(review);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("result", result);
		
		return "myreview1_update.jsp";
	}

}
