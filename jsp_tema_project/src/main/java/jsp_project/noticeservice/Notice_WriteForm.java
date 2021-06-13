package jsp_project.noticeservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp_project.dao.MemberDao;
import jsp_project.model.Member;
import jsp_project.service.CommandProcess;

public class Notice_WriteForm implements CommandProcess {

	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();//세션 불러오기
		if(session.getAttribute("user_id")!=null) {
			String user_id = (String)session.getAttribute("user_id");
			MemberDao md = MemberDao.getInstance();
			Member member  = md.select(user_id);
			int notice_num = 0;
			String pageNum = request.getParameter("pageNum");
			notice_num = Integer.parseInt(request.getParameter("notice_num"));
			request.setAttribute("member", member); //member 그대로 이동
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("notice_num", notice_num);
			return "notice_writeForm.jsp";
		}else {
			return "sessionChk.jsp";
		}
	}

}
