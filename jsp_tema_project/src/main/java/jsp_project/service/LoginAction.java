package jsp_project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp_project.dao.MemberDao;
import jsp_project.model.Member;
public class LoginAction implements CommandProcess {

	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		String user_id = request.getParameter("user_id");
		String user_password = request.getParameter("user_password");
		MemberDao md = MemberDao.getInstance();
		Member member = md.select(user_id);
		int result = 0;
		if(member==null || member.getUser_del().equals("y")) result = -1;
		else {
			if(member.getUser_password().equals(user_password)) {
				HttpSession session = request.getSession();
				session.setAttribute("user_id", user_id); //세션값을 저장
				result = 1;
			}
		}
		request.setAttribute("result", result);
		return "login.jsp";
	}

}