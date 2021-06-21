package jsp_project.travelservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp_project.service.CommandProcess;

public class Travel_WriteForm implements CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		int travel_num = 0;
		String pageNum = request.getParameter("pageNum");
		travel_num = Integer.parseInt(request.getParameter("travel_num"));
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("travel_num", travel_num);
		return "travel_writeForm.jsp";
	}

}
