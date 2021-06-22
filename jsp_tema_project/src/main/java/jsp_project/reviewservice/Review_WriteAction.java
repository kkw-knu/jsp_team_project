package jsp_project.reviewservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp_project.dao.ReviewDao;
import jsp_project.model.Review;
import jsp_project.service.CommandProcess;

public class Review_WriteAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (java.io.UnsupportedEncodingException e) {
			System.out.println("¿¡·¯ qna_writeaction : "+e.getMessage());
		}
		
		Review review = new Review();
		
		int review_num = Integer.parseInt(request.getParameter("review_num"));
		String review_id = request.getParameter("review_id");
		String review_travel = request.getParameter("review_travel");
		String review_title= request.getParameter("review_title");
		String review_content = request.getParameter("review_content");
		int review_star = Integer.parseInt(request.getParameter("review_star"));
		int travel_num = Integer.parseInt(request.getParameter("travel_num"));
		String pageNum = request.getParameter("pageNum");
		String pageNum1 = request.getParameter("pageNum1");
		
		review.setReview_num(review_num);
		review.setReview_id(review_id);
		review.setReview_travel(review_travel);
		review.setReview_title(review_title);
		review.setReview_content(review_content);
		review.setReview_star(review_star);
		
		ReviewDao rd = ReviewDao.getInstance();
		int result = rd.insert(review);
		rd.starupdate(review_travel);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageNum1", pageNum1);
		request.setAttribute("travel_num", travel_num);
		request.setAttribute("result", result);
		return "review_write.jsp";
	}

}
