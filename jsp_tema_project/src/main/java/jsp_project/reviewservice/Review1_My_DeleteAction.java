package jsp_project.reviewservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp_project.dao.Review1Dao;
import jsp_project.service.CommandProcess;

public class Review1_My_DeleteAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		int review_num = Integer.parseInt(request.getParameter("review_num"));
		String pageNum = request.getParameter("pageNum");
		
		Review1Dao rd = Review1Dao.getInstance();
		int result = rd.delete(review_num);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("result", result);
		return "myreview1_delete.jsp";
	}

}
