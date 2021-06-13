package jsp_project.noticeservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp_project.dao.MemberDao;
import jsp_project.dao.NoticeDao;
import jsp_project.model.Member;
import jsp_project.model.Notice;
import jsp_project.service.CommandProcess;

public class Notice_UpdateForm implements CommandProcess {

	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		if(session.getAttribute("user_id")!=null) {
			String user_id = (String)session.getAttribute("user_id");
			MemberDao md = MemberDao.getInstance();
			Member member  = md.select(user_id);
			String pageNum = request.getParameter("pageNum");
			int notice_num = Integer.parseInt(request.getParameter("notice_num"));
			NoticeDao nd = NoticeDao.getInstance();
			Notice notice = nd.select(notice_num); //게시글 보는용
			request.setAttribute("member", member);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("notice", notice);
			return "notice_updateForm.jsp";
		}else {
			return "sessionChk.jsp";
		}
	}

}
