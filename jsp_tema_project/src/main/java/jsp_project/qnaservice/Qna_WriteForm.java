package jsp_project.qnaservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp_project.dao.MemberDao;
import jsp_project.dao.QnaDao;
import jsp_project.model.Member;
import jsp_project.model.Qna;
import jsp_project.service.CommandProcess;

public class Qna_WriteForm implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();//세션 불러오기
		if(session.getAttribute("user_id")!=null) {
			String user_id = (String)session.getAttribute("user_id");
			MemberDao md = MemberDao.getInstance();
			Member member  = md.select(user_id);
			int qna_num = 0, qna_ref = 0, qna_re_level = 0, qna_re_step = 0;
			String pageNum = request.getParameter("pageNum");
			qna_num = Integer.parseInt(request.getParameter("qna_num"));
			if(qna_num != 0) {
				QnaDao qd = QnaDao.getInstance();
				Qna qna = qd.select(qna_num);
				qna_ref = qna.getQna_ref();
				qna_re_step = qna.getQna_re_step();
				qna_re_level = qna.getQna_re_level();
			}
			request.setAttribute("member", member); //member 그대로 이동
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("qna_num", qna_num);
			request.setAttribute("qna_ref", qna_ref);
			request.setAttribute("qna_re_level", qna_re_level);
			request.setAttribute("qna_re_step", qna_re_step);
			return "qna_writeForm.jsp";
		}else {//세션이 없으면 로그인화면 이동
			return "sessionChk.jsp";
		}
	}

}
