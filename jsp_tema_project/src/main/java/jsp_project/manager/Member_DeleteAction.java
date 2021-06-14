package jsp_project.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp_project.dao.MemberDao;
import jsp_project.service.CommandProcess;

public class Member_DeleteAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		String user_id = request.getParameter("user_id");
		String pageNum = request.getParameter("pageNum");
		MemberDao md = MemberDao.getInstance();
		int result = md.realdelete(user_id);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("result", result);
		return "member_delete.jsp";
	}

}
