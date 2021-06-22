package jsp_project.reviewservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp_project.dao.Review1Dao;
import jsp_project.model.Review1;
import jsp_project.service.CommandProcess;

public class Review1_WriteAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (java.io.UnsupportedEncodingException e) {
			System.out.println("¿¡·¯ qna_writeaction : "+e.getMessage());
		}
		
		Review1 review = new Review1();
		
		int review_num = Integer.parseInt(request.getParameter("review_num"));
		String review_id = request.getParameter("review_id");
		String review_acmd = request.getParameter("review_acmd");
		String review_title= request.getParameter("review_title");
		String review_content = request.getParameter("review_content");
		int review_star = Integer.parseInt(request.getParameter("review_star"));
		int acmd_num = Integer.parseInt(request.getParameter("acmd_num"));
		String pageNum = request.getParameter("pageNum");
		String pageNum1 = request.getParameter("pageNum1");
		
		review.setReview_num(review_num);
		review.setReview_id(review_id);
		review.setReview_acmd(review_acmd);
		review.setReview_title(review_title);
		review.setReview_content(review_content);
		review.setReview_star(review_star);
		
		Review1Dao rd = Review1Dao.getInstance();
		int result = rd.insert(review);
		rd.starupdate(review);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageNum1", pageNum1);
		request.setAttribute("acmd_num", acmd_num);
		request.setAttribute("result", result);
		return "review1_write.jsp";
	}

}
