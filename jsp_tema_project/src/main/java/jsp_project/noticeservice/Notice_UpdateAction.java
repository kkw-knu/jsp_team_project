package jsp_project.noticeservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp_project.dao.NoticeDao;
import jsp_project.model.Notice;
import jsp_project.service.CommandProcess;

public class Notice_UpdateAction implements CommandProcess{

	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (java.io.UnsupportedEncodingException e) {
			System.out.println("¿¡·¯ qna_writeaction : "+e.getMessage());
		}
		Notice notice = new Notice();
		int notice_num = Integer.parseInt(request.getParameter("notice_num"));
		String pageNum = request.getParameter("pageNum");
		String notice_title = request.getParameter("notice_title");
		String notice_content = request.getParameter("notice_content");
		notice.setNotice_num(notice_num);
		notice.setNotice_title(notice_title);
		notice.setNotice_content(notice_content);
		NoticeDao nd = NoticeDao.getInstance();
		int result = nd.update(notice);
		
		request.setAttribute("result", result);
		request.setAttribute("pageNum", pageNum);
		return "notice_update.jsp";
	}

}
