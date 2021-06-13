package jsp_project.qnaservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp_project.dao.MemberDao;
import jsp_project.dao.QnaDao;
import jsp_project.model.Member;
import jsp_project.model.Qna;
import jsp_project.service.CommandProcess;

public class Qna_UpdateForm implements CommandProcess {

	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		if(session.getAttribute("user_id")!=null) {
			String user_id = (String)session.getAttribute("user_id");
			MemberDao md = MemberDao.getInstance();
			Member member  = md.select(user_id);
			String pageNum = request.getParameter("pageNum");
			int qna_num = Integer.parseInt(request.getParameter("qna_num"));
			QnaDao qd = QnaDao.getInstance();
			Qna qna = qd.select(qna_num); //게시글 보는용
			request.setAttribute("member", member);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("qna", qna);
			return "qna_updateForm.jsp";
		}else {
			return "sessionChk.jsp";
		}
	}

}
