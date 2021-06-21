package jsp_project.reviewservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp_project.dao.ReviewDao;
import jsp_project.service.CommandProcess;

public class Review_My_DeleteAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		int review_num = Integer.parseInt(request.getParameter("review_num"));
		String pageNum = request.getParameter("pageNum");
		
		ReviewDao rd = ReviewDao.getInstance();
		int result = rd.delete(review_num);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("result", result);
		return "myreview_delete.jsp";
	}

}
