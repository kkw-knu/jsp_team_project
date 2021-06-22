package jsp_project.reviewservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp_project.dao.Review1Dao;
import jsp_project.model.Review1;
import jsp_project.service.CommandProcess;

public class Review1_DeleteAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		int acmd_num = Integer.parseInt(request.getParameter("acmd_num"));
		int review_num = Integer.parseInt(request.getParameter("review_num"));
		String pageNum = request.getParameter("pageNum");
		String pageNum1 = request.getParameter("pageNum1");
		
		Review1Dao rd = Review1Dao.getInstance();
		Review1 review = rd.select(review_num);
		int result = rd.delete(review_num);
		rd.starupdate(review);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageNum1", pageNum1);
		request.setAttribute("acmd_num", acmd_num);
		request.setAttribute("result", result);
		return "review1_delete.jsp";
	}
 
}
