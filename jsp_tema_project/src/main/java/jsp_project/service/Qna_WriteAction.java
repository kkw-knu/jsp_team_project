package jsp_project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp_project.dao.MemberDao;
import jsp_project.dao.QnaDao;
import jsp_project.model.Member;
import jsp_project.model.Qna;

public class Qna_WriteAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();//세션 불러오기
		if(session.getAttribute("user_id")!=null) {
			String user_id = (String)session.getAttribute("user_id");
			MemberDao md = MemberDao.getInstance();
			Member member  = md.select(user_id);
			request.setAttribute("member", member); //member 그대로 이동
			try {
				request.setCharacterEncoding("utf-8");
			} catch (java.io.UnsupportedEncodingException e) {
				System.out.println("에러 qna_writeaction : "+e.getMessage());
			}
			
			Qna qna = new Qna();
			
			int qna_num = Integer.parseInt(request.getParameter("qna_num"));
			int qna_ref = Integer.parseInt(request.getParameter("qna_ref"));
			int qna_re_level = Integer.parseInt(request.getParameter("qna_re_level"));
			int qna_re_step = Integer.parseInt(request.getParameter("qna_re_step"));
			
			String qna_title = request.getParameter("qna_title");
			String qna_writer = request.getParameter("qna_writer");
			String qna_content = request.getParameter("qna_content");
			String pageNum = request.getParameter("pageNum");
			
			qna.setQna_num(qna_num);
			qna.setQna_title(qna_title);
			qna.setQna_writer(qna_writer);
			qna.setQna_content(qna_content);
			qna.setQna_ref(qna_ref);
			qna.setQna_re_level(qna_re_level);
			qna.setQna_re_step(qna_re_step);
			
			QnaDao qd = QnaDao.getInstance();
			int result = qd.insert(qna);
			
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("result", result);
			
			return "qna_write.jsp";
		}else {//세션이 없으면 로그인화면 이동
			return "sessionChk.jsp";
		}
	}

}
