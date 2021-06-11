package jsp_project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp_project.dao.MemberDao;
import jsp_project.model.Member;

public class DeleteAction implements CommandProcess {

	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		if(session.getAttribute("user_id")!=null) {
			String user_id = request.getParameter("user_id");
			String user_password = request.getParameter("user_password");
			MemberDao md = MemberDao.getInstance();
			Member member = md.select(user_id);
			int result = 0;
			if(member.getUser_password().equals(user_password)){//���� ��й�ȣ����ġ�� ��
				result = md.delete(member);//ȸ��Ż��
			}
			if(result > 0) session.invalidate();
			request.setAttribute("result", result);
			return "delete.jsp";
		}else {
			return "sessionChk.jsp";
		}
	}
}