package jsp_project.travelservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp_project.dao.TravelDao;
import jsp_project.service.CommandProcess;

public class Travel_DeleteAction implements CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		
			int travel_num = Integer.parseInt(request.getParameter("travel_num"));
			String pageNum = request.getParameter("pageNum");
			TravelDao td = TravelDao.getInstance();
			int result = td.delete(travel_num);
			
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("result", result);
	
			return "travel_delete.jsp";
	}
}
