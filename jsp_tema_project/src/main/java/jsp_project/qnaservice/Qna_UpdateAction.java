package jsp_project.qnaservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp_project.dao.QnaDao;
import jsp_project.model.Qna;
import jsp_project.service.CommandProcess;

public class Qna_UpdateAction implements CommandProcess{

	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (java.io.UnsupportedEncodingException e) {
			System.out.println("¿¡·¯ qna_writeaction : "+e.getMessage());
		}
		Qna qna = new Qna();
		int qna_num = Integer.parseInt(request.getParameter("qna_num"));
		String pageNum = request.getParameter("pageNum");
		String qna_title = request.getParameter("qna_title");
		String qna_content = request.getParameter("qna_content");
		qna.setQna_num(qna_num);
		qna.setQna_title(qna_title);
		qna.setQna_content(qna_content);
		QnaDao qd = QnaDao.getInstance();
		int result = qd.update(qna);
		
		request.setAttribute("result", result);
		request.setAttribute("pageNum", pageNum);
		return "qna_update.jsp";
	}

}
