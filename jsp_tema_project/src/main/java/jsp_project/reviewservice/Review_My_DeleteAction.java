package jsp_project.reviewservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp_project.dao.ReviewDao;
import jsp_project.model.Review;
import jsp_project.service.CommandProcess;

public class Review_My_DeleteAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		int review_num = Integer.parseInt(request.getParameter("review_num"));
		String pageNum = request.getParameter("pageNum");
		
		ReviewDao rd = ReviewDao.getInstance();
		Review review = rd.select(review_num); 
		int result = rd.delete(review_num);
		rd.starupdate(review.getReview_travel());
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("result", result);
		return "myreview_delete.jsp";
	}

}
