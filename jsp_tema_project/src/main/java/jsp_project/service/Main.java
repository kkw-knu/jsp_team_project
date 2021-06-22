package jsp_project.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp_project.dao.NoticeDao;
import jsp_project.model.Notice;

public class Main implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		NoticeDao nd = NoticeDao.getInstance();
		List<Notice> noticelist = nd.newnotice();
		
		request.setAttribute("noticelist", noticelist);
		return "main.jsp";
	}

}
