package jsp_project.qnaservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp_project.dao.QnaDao;
import jsp_project.service.CommandProcess;

public class Qna_DeleteAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		int qna_num = Integer.parseInt(request.getParameter("qna_num"));
		String pageNum = request.getParameter("pageNum");
		QnaDao qd = QnaDao.getInstance();
		int result = qd.delete(qna_num);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("result", result);

		return "qna_delete.jsp";
	}

}
