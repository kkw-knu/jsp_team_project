package jsp_project.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp_project.dao.MemberDao;
import jsp_project.model.Member;
import jsp_project.service.CommandProcess;

public class Member_UpdateAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
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
		return "member_update.jsp";
	}

}
