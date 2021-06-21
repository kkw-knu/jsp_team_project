package jsp_project.reviewservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp_project.dao.ReviewDao;
import jsp_project.service.CommandProcess;

public class Review_DeleteAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		int travel_num = Integer.parseInt(request.getParameter("travel_num"));
		int review_num = Integer.parseInt(request.getParameter("review_num"));
		String pageNum = request.getParameter("pageNum");
		String pageNum1 = request.getParameter("pageNum1");
		
		ReviewDao rd = ReviewDao.getInstance();
		int result = rd.delete(review_num);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageNum1", pageNum1);
		request.setAttribute("travel_num", travel_num);
		request.setAttribute("result", result);
		return "review_delete.jsp";
	}
 
}
