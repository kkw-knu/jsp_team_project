package jsp_project.acmdservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp_project.service.CommandProcess;

public class Acmd_WriteForm implements CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		int acmd_num = 0;
		String pageNum = request.getParameter("pageNum");
		acmd_num = Integer.parseInt(request.getParameter("acmd_num"));
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("acmd_num", acmd_num);
		return "acmd_writeForm.jsp";
	}

}
