package jsp_project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp_project.dao.MemberDao;
import jsp_project.model.Member;

public class Confirm implements CommandProcess {

	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		String user_id = request.getParameter("user_id");
		String msg ="";
		MemberDao md = MemberDao.getInstance();
		Member member = md.select(user_id);
		if(member == null) msg ="사용 가능한 아이디입니다.";
		else msg ="사용중인 아이디입니다.";
		request.setAttribute("msg", msg);
		return "confirm.jsp";
	}

}
