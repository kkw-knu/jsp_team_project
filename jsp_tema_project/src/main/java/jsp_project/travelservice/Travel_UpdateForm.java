package jsp_project.travelservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp_project.dao.TravelDao;
import jsp_project.model.Travel;
import jsp_project.service.CommandProcess;

public class Travel_UpdateForm implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		int travel_num = Integer.parseInt(request.getParameter("travel_num"));
		TravelDao td = TravelDao.getInstance();
		Travel travel = td.select(travel_num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("travel", travel);
		return "travel_updateForm.jsp";
	}

}
