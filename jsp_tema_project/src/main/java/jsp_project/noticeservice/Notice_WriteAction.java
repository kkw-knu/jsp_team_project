package jsp_project.noticeservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp_project.dao.MemberDao;
import jsp_project.dao.NoticeDao;
import jsp_project.model.Member;
import jsp_project.model.Notice;
import jsp_project.service.CommandProcess;

public class Notice_WriteAction implements CommandProcess {

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
			
			Notice notice = new Notice();
			
			int	notice_num = Integer.parseInt(request.getParameter("notice_num"));
			
			String notice_title = request.getParameter("notice_title");
			String notice_writer = request.getParameter("notice_writer");
			String notice_content = request.getParameter("notice_content");
			String pageNum = request.getParameter("pageNum");
			
			notice.setNotice_num(notice_num);
			notice.setNotice_title(notice_title);
			notice.setNotice_writer(notice_writer);
			notice.setNotice_content(notice_content);
			
			NoticeDao nd = NoticeDao.getInstance();
			int result = nd.insert(notice);
			
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("result", result);
			
			return "notice_write.jsp";
		}else {//세션이 없으면 로그인화면 이동
			return "sessionChk.jsp";
		}
	}

}
