package jsp_project.qnaservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp_project.dao.QnaDao;
import jsp_project.model.Qna;
import jsp_project.service.CommandProcess;

public class Qna_Content implements CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		int qna_num = Integer.parseInt(request.getParameter("qna_num"));
		QnaDao qd = QnaDao.getInstance();
		qd.readcoutUpdate(qna_num);
		Qna qna = qd.select(qna_num);
		
		request.setAttribute("qna_num", qna_num); 
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("qna", qna);
		return "qna_content.jsp";
	}

}
