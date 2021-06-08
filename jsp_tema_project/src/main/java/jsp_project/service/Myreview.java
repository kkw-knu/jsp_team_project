package jsp_project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp_project.dao.MemberDao;
import jsp_project.model.Member;

public class Myreview implements CommandProcess {

	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		if(session.getAttribute("user_id")!=null) {
			String user_id = (String)session.getAttribute("user_id");
			MemberDao md = MemberDao.getInstance();
			Member member  = md.select(user_id);
			request.setAttribute("member", member);
			return "myreview.jsp";
		}else {
			return "sessionChk.jsp";
		}
	}

}
