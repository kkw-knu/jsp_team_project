package jsp_project.noticeservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp_project.dao.NoticeDao;
import jsp_project.service.CommandProcess;

public class Notice_DeleteAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		if(session.getAttribute("user_id")=="master") {
			int notice_num = Integer.parseInt(request.getParameter("notice_num"));
			String pageNum = request.getParameter("pageNum");
			NoticeDao nd = NoticeDao.getInstance();
			int result = nd.delete(notice_num);
			
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("result", result);
	
			return "notice_delete.jsp";
		}else {
			return "managersessionChk.jsp";
		}
	}

}
