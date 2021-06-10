package jsp_project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp_project.dao.MemberDao;
import jsp_project.model.Member;

public class UpdateAction implements CommandProcess {

	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		if(session.getAttribute("user_id")!=null) {
			String user_id = request.getParameter("user_id");
			String user_name = request.getParameter("user_name");
			String user_tel  = request.getParameter("user_tel");
			String user_address = request.getParameter("user_address");
			String user_email = request.getParameter("user_email");
			Member member = new Member();
			member.setUser_id(user_id);
			member.setUser_name(user_name);
			member.setUser_tel(user_tel);
			member.setUser_address(user_address);
			member.setUser_email(user_email);
			MemberDao md = MemberDao.getInstance();
			int result = md.update(member);
			request.setAttribute("result", result);
			return "update.jsp";
		}else {
			return "sessionChk.jsp";
		}
	}

}
