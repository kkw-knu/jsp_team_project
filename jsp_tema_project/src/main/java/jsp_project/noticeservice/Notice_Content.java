package jsp_project.noticeservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp_project.dao.NoticeDao;
import jsp_project.model.Notice;
import jsp_project.service.CommandProcess;

public class Notice_Content implements CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		int notice_num = Integer.parseInt(request.getParameter("notice_num"));
		NoticeDao nd = NoticeDao.getInstance();
		nd.readcoutUpdate(notice_num);
		Notice notice = nd.select(notice_num);
		
		request.setAttribute("notice_num", notice_num); 
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("notice", notice);
		return "notice_content.jsp";
	}

}
